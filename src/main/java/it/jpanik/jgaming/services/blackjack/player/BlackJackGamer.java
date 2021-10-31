package it.jpanik.jgaming.services.blackjack.player;

import java.util.Objects;

/**
 * @author Jacopo Korompay
 */
public class BlackJackGamer extends BlackJackPlayer {

  public static final float DEFAULT_BALANCE = 10;

  private String username;
  private int chair;
  private float balance;

  public BlackJackGamer(String username, int chair) {
    super();
    this.username = username;
    this.chair = chair;
    this.balance = DEFAULT_BALANCE;
  }

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

  public void addMoney(float value) {
    balance += value;
  }

  public boolean gameOver() {
    return balance <= 0;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    BlackJackGamer gamer = (BlackJackGamer) o;
    return chair == gamer.chair && username.equals(gamer.username);
  }

  @Override
  public int hashCode() {
    return Objects.hash(username, chair);
  }
}
