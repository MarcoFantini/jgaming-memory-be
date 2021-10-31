package it.jpanik.jgaming.dtos.blackjack;

import java.util.List;

/**
 * @author Jacopo Korompay
 */
public class BlackJackGamerDto {

  private String username;
  private int chair;
  private float balance;
  private List<BlackJackCardDto> cards;

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public int getChair() {
    return chair;
  }

  public void setChair(int chair) {
    this.chair = chair;
  }

  public float getBalance() {
    return balance;
  }

  public void setBalance(float balance) {
    this.balance = balance;
  }

  public List<BlackJackCardDto> getCards() {
    return cards;
  }

  public void setCards(List<BlackJackCardDto> cards) {
    this.cards = cards;
  }
}
