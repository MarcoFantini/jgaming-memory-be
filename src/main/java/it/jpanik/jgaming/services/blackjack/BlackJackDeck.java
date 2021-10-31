package it.jpanik.jgaming.services.blackjack;

import it.jpanik.jgaming.enums.BlackJackSuit;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Jacopo Korompay
 *
 *     <p>A deck of 104 french cards
 */
public class BlackJackDeck {

  private List<BlackJackCard> cards;

  public BlackJackDeck() {
    initDeck();
  }

  /** Init a new deck */
  private void initDeck() {
    cards = new ArrayList<>(104);
    for (int i = 0; i < 2; i++) {
      for (int j = 0; j < 13; j++) cards.add(new BlackJackCard(j + 1, BlackJackSuit.CLUBS.toString()));
      for (int j = 0; j < 13; j++) cards.add(new BlackJackCard(j + 1, BlackJackSuit.DIAMONDS.toString()));
      for (int j = 0; j < 13; j++) cards.add(new BlackJackCard(j + 1, BlackJackSuit.HEARTS.toString()));
      for (int j = 0; j < 13; j++) cards.add(new BlackJackCard(j + 1, BlackJackSuit.SPADES.toString()));
    }
    mixDeck();
  }

  public List<BlackJackCard> getCards() {
    return cards;
  }

  public void setCards(List<BlackJackCard> cards) {
    this.cards = cards;
  }

  public int getSize() {
    return cards.size();
  }

  /**
   * Returns (removing it) first card of deck
   *
   * @return First card of deck
   */
  public BlackJackCard popCard() {
    return cards.remove(0);
  }

  /** Deck mixes itself */
  public void mixDeck() {
    Collections.shuffle(cards);
  }
}
