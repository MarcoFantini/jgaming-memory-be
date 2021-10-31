package it.jpanik.jgaming.dtos.blackjack;

import it.jpanik.jgaming.enums.blackjack.BlackJackGamePhase;
import it.jpanik.jgaming.services.blackjack.BlackJackGame;

import java.util.List;

/**
 * @author Jacopo Korompay
 */
public class BlackJackGameDto {

  private BlackJackDealerDto dealer;
  private List<BlackJackGamerDto> waitingGamers;
  private List<BlackJackGamerDto> gamers;
  private BlackJackGamePhase gamePhase;
  private BlackJackRoundDto round;
  private int inPlay;

  public BlackJackDealerDto getDealer() {
    return dealer;
  }

  public void setDealer(BlackJackDealerDto dealer) {
    this.dealer = dealer;
  }

  public List<BlackJackGamerDto> getWaitingGamers() {
    return waitingGamers;
  }

  public void setWaitingGamers(List<BlackJackGamerDto> waitingGamers) {
    this.waitingGamers = waitingGamers;
  }

  public List<BlackJackGamerDto> getGamers() {
    return gamers;
  }

  public void setGamers(List<BlackJackGamerDto> gamers) {
    this.gamers = gamers;
  }

  public BlackJackGamePhase getGamePhase() {
    return gamePhase;
  }

  public void setGamePhase(BlackJackGamePhase gamePhase) {
    this.gamePhase = gamePhase;
  }

  public BlackJackRoundDto getRound() {
    return round;
  }

  public void setRound(BlackJackRoundDto round) {
    this.round = round;
  }

  public int getInPlay() {
    return inPlay;
  }

  public void setInPlay(int inPlay) {
    this.inPlay = inPlay;
  }
}
