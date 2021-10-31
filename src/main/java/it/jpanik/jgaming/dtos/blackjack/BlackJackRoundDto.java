package it.jpanik.jgaming.dtos.blackjack;

/**
 * @author Jacopo Korompay
 */
public class BlackJackRoundDto {

  private int roundNumber;
  private float pot;

  public int getRoundNumber() {
    return roundNumber;
  }

  public void setRoundNumber(int roundNumber) {
    this.roundNumber = roundNumber;
  }

  public float getPot() {
    return pot;
  }

  public void setPot(float pot) {
    this.pot = pot;
  }
}
