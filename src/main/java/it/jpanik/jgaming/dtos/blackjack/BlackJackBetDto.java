package it.jpanik.jgaming.dtos.blackjack;

import it.jpanik.jgaming.services.blackjack.player.BlackJackGamer;

/**
 * @author Jacopo Korompay
 */
public class BlackJackBetDto {

  private BlackJackGamer gamer;
  private float value;

  public BlackJackGamer getGamer() {
    return gamer;
  }

  public void setGamer(BlackJackGamer gamer) {
    this.gamer = gamer;
  }

  public float getValue() {
    return value;
  }

  public void setValue(float value) {
    this.value = value;
  }
}
