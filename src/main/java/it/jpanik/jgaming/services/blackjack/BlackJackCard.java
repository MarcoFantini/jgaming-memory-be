package it.jpanik.jgaming.services.blackjack;

/**
 * @author Jacopo Korompay
 */
public class BlackJackCard {

  private int value;
  private String suit;

  public BlackJackCard(int value, String suit) {
    this.value = value;
    this.suit = suit;
  }

  public int getValue() {
    return value;
  }

  public void setValue(int value) {
    this.value = value;
  }

  public String getSuit() {
    return suit;
  }

  public void setSuit(String suit) {
    this.suit = suit;
  }
}
