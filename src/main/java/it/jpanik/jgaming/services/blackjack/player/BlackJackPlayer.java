package it.jpanik.jgaming.services.blackjack.player;

import it.jpanik.jgaming.services.blackjack.BlackJackCard;
import it.jpanik.jgaming.services.blackjack.BlackJackGame;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Jacopo Korompay
 */
public abstract class BlackJackPlayer {

  private BlackJackGame game;
  private List<BlackJackCard> cards;

  public BlackJackPlayer() {
    cards = new ArrayList<>();
  }

  public BlackJackGame getGame() {
    return game;
  }

  public void setGame(BlackJackGame game) {
    this.game = game;
  }

  public void setCards(List<BlackJackCard> cards) {
    this.cards = cards;
  }

  public List<BlackJackCard> getCards() {
    return cards;
  }

  public void clearHand() {
    cards.clear();
  }

  public void addCard(BlackJackCard card) {
    cards.add(card);
  }

  /**
   * Counting hand points
   *
   * @return Hand points
   */
  public int computePoints() {
    int points = 0;
    List<BlackJackCard> aces =
        new ArrayList<>(
            cards.stream().filter(card -> card.getValue() == 1).collect(Collectors.toList()));
    for (BlackJackCard card : cards) {
      if (card.getValue() != 1) points += card.getValue() >= 11 ? 10 : card.getValue();
    }
    // Ace value is 11 or 1 depending on whether gamer's points points is > 21 or not.
    for (BlackJackCard ace : aces) {
      if (points + 11 <= 21) points += 11;
      else points += ace.getValue();
    }
    return points;
  }
}
