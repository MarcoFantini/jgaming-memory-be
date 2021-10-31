package it.jpanik.jgaming.services.blackjack;


import it.jpanik.jgaming.services.blackjack.player.BlackJackGamer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jacopo Korompay
 */
public class BlackJackRound {

  private BlackJackGame game;
  private List<BlackJackGamer> roundGamers;
  private List<BlackJackBet> pot;
  private int roundNumber;

  public BlackJackRound() {
    this.roundGamers = new ArrayList<>(7);
    this.pot = new ArrayList<>();
  }

  public BlackJackRound(BlackJackGame game, List<BlackJackGamer> roundGamers) {
    this();
    this.game = game;
    this.roundGamers.addAll(roundGamers);
  }

  public BlackJackGame getGame() {
    return game;
  }

  public void setGame(BlackJackGame game) {
    this.game = game;
  }

  /**
   * Returns a list of gamers courrent round.
   *
   * <p>E' diversa dalla lista ritornata da GAME la quale contiene sempre e comunque tutti i
   * giocatori della stanza.
   *
   * @return
   */
  public List<BlackJackGamer> getRoundGamers() {
    return roundGamers;
  }

  public void setRoundGamers(List<BlackJackGamer> roundGamers) {
    this.roundGamers = roundGamers;
  }

  public List<BlackJackBet> getPot() {
    return pot;
  }

  public void setPot(List<BlackJackBet> pot) {
    this.pot = pot;
  }

  public int getRoundNumber() {
    return roundNumber;
  }

  public void setRoundNumber(int roundNumber) {
    this.roundNumber = roundNumber;
  }

  /**
   * When a gamer lost its hand, is removed from the gamers round list.
   *
   * @param gamer
   */
  public void removeGamerFromRound(BlackJackGamer gamer) {
    roundGamers.remove(gamer);
  }

  public BlackJackBet getBetByGamer(BlackJackGamer gamer) {
    return pot.stream()
        .filter(bet -> bet.getGamer().equals(gamer))
        .findAny()
        .orElseThrow(() -> new RuntimeException("Bet not found."));
  }

  /**
   * Adds a gamer's bet into pot.
   *
   * @param bet
   */
  public void addBet(BlackJackBet bet) {
    bet.getGamer().addMoney(-bet.getValue());
    pot.add(bet);
  }

  public float computePot() {
    float sum = 0;
    for (BlackJackBet bet : pot) sum += bet.getValue();
    return sum;
  }
}
