package it.jpanik.jgaming.services.blackjack.player;

import it.jpanik.jgaming.exceptions.blackjack.BlackJackDealerLoseHandException;
import it.jpanik.jgaming.services.blackjack.BlackJackBet;
import it.jpanik.jgaming.services.blackjack.BlackJackCard;
import it.jpanik.jgaming.services.blackjack.BlackJackDeck;
import it.jpanik.jgaming.services.blackjack.BlackJackEvent;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jacopo Korompay
 */
public class BlackJackDealer extends BlackJackPlayer {

  private BlackJackDeck deck;

  public BlackJackDealer() {
    super();
    newDeck();
  }

  public BlackJackDeck getDeck() {
    return deck;
  }

  public void setDeck(BlackJackDeck deck) {
    this.deck = deck;
  }

  /** Dealer gets a new deck and mixes it */
  public void newDeck() {
    deck = new BlackJackDeck();
    mixDecks();
  }

  /** Dealer mixes deck */
  private void mixDecks() {
    deck.mixDeck();
  }

  /**
   * 1. The dealer deals a first round of face up cards to each rounds player.
   *
   * <p>2. The dealer gives himself a face up card.
   *
   * <p>3. The dealer deals a second round of face up cards to each player. 3.1 A gamer can receive
   * a loser hand.
   */
  public void serveAllGamers() {
    for (BlackJackGamer gamer : getGame().getRound().getRoundGamers()) {
      if (gamer != null) {
        BlackJackCard card1 = deck.popCard();
        gamer.addCard(card1);
        getGame().sendEvent(BlackJackEvent.EventType.PLAYER_RECEIVE_CARD, gamer.getChair(), null, card1);
      }
    }
    BlackJackCard card = deck.popCard();
    getCards().add(card);
    getGame().sendEvent(BlackJackEvent.EventType.PLAYER_RECEIVE_CARD, - 1, null, card);
    for (BlackJackGamer gamer : getGame().getRound().getRoundGamers()) {
      if (gamer != null) {
        BlackJackCard card2 = deck.popCard();
        gamer.addCard(card2);
        getGame().sendEvent(BlackJackEvent.EventType.PLAYER_RECEIVE_CARD, gamer.getChair(), null, card2);
        if (gamer.computePoints() == 21) {
          getGame().sendEvent(BlackJackEvent.EventType.PLAYER_HAS_BLACKJACK, gamer.getChair(), null, null);
        }
      }
    }
  }

  /**
   * Dealer serves card to indicated gamer
   *
   * @param gamer The gamer who called the card
   */
  public void serveCardToGamer(BlackJackGamer gamer) {
    BlackJackCard card = deck.popCard();
    gamer.addCard(card);
    getGame().sendEvent(BlackJackEvent.EventType.PLAYER_CALL_CARD, gamer.getChair(), null, card);
    int gamerPoints = gamer.computePoints();
    if (gamerPoints == 21) {
      getGame().sendEvent(BlackJackEvent.EventType.PLAYER_HAS_BLACKJACK, gamer.getChair(), null, null);
    } else if (gamerPoints > 21) {
      // Gamer loses hand
      getGame().gamerLosesHand(gamer);
      getGame().getRound().removeGamerFromRound(gamer);
    }
  }

  /** Delaer plays his hand */
  public void play() throws BlackJackDealerLoseHandException {
    while (computePoints() < 17) {
      BlackJackCard card = deck.popCard();
      getCards().add(card);
      getGame().sendEvent(BlackJackEvent.EventType.PLAYER_CALL_CARD, -1, null, card);
      getGame().waitFor(2L);
    }
    if (computePoints() == 21)
      getGame().sendEvent(BlackJackEvent.EventType.PLAYER_HAS_BLACKJACK, -1, null, null);
    if (computePoints() > 21) throw new BlackJackDealerLoseHandException();
  }

  public void countingCards() {
    int dealerPoints = computePoints();
    // Loser gamers
    List<BlackJackGamer> loserGamer = new ArrayList<>();
    for (BlackJackGamer g : getGame().getRound().getRoundGamers()) {
      if (g != null) {
        if (g.computePoints() < dealerPoints) {
          // Gamer lose versus dealer
          getGame().gamerLosesHand(g);
          loserGamer.add(g);
        }
      }
    }
    loserGamer.forEach(gamer -> getGame().getRound().removeGamerFromRound(gamer));
    // Dealer distributes winning
    for (BlackJackGamer g : getGame().getRound().getRoundGamers()) {
      if (g != null) {
        int gamerPoints = g.computePoints();
        BlackJackBet bet = getGame().getRound().getBetByGamer(g); // Gamer's bet
        if (gamerPoints == dealerPoints) {
          // Gamer draw versus dealer
          getGame()
              .sendEvent(BlackJackEvent.EventType.PLAYER_DRAFT_HAND, g.getChair(), bet.getValue(), null);
          payGamer(bet, 1f);
        } else if (gamerPoints == 21) {
          // Gamer does blackjack
          getGame().sendEvent(BlackJackEvent.EventType.PLAYER_WIN_HAND, g.getChair(), bet.getValue(), null);
          payGamer(bet, 2.5f);
        } else if (gamerPoints > dealerPoints) {
          // Gamer win versus dealer
          getGame().sendEvent(BlackJackEvent.EventType.PLAYER_WIN_HAND, g.getChair(), bet.getValue(), null);
          payGamer(bet, 2f);
        }
      }
    }
  }

  /** When dealer lose its hand he pays all remains player */
  public void dealerLoseHand() {
    // Dealer distributes winning
    for (BlackJackGamer g : getGame().getRound().getRoundGamers()) {
      if (g != null) {
        int gamerPoints = g.computePoints();
        BlackJackBet bet = getGame().getRound().getBetByGamer(g); // Gamer's bet
        getGame().sendEvent(BlackJackEvent.EventType.PLAYER_WIN_HAND, g.getChair(), bet.getValue(), null);
        if (gamerPoints == 21) {
          // Gamer does blackjack
          payGamer(bet, 2.5f);
        } else {
          // Gamer win versus dealer
          payGamer(bet, 2f);
        }
      }
    }
  }

  /** A gamer win versus dealer and dealer pay it */
  private void payGamer(BlackJackBet bet, float amount) {
    // Gamer collects the winning
    bet.getGamer().addMoney(bet.getValue() * amount);
    getGame().getRound().getPot().remove(bet);
  }
}
