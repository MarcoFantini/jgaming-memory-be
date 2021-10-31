package it.jpanik.jgaming.dtos.blackjack;

/**
 * @author Jacopo Korompay
 */
public class BlackJackCardDto {

  private int value;
  private String suit;

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
