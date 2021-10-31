package it.jpanik.jgaming.exceptions.blackjack;

/**
 * @author Jacopo Korompay
 */
public class BlackJackDealerLoseHandException extends Throwable {

  public BlackJackDealerLoseHandException() {
    super("Il banco perde la mano.");
  }
}
