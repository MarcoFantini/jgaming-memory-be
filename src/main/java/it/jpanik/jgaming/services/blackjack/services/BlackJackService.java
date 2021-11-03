package it.jpanik.jgaming.services.blackjack.services;

import it.jpanik.jgaming.dtos.blackjack.BlackJackGameDto;

public interface BlackJackService {

    void addGamer(String username, int chair);

    void setBet(int chair, float value);

    void callCard(int chair);

    void check(int chair);

    void leaveGame(int chair);

    void resetGame();

    BlackJackGameDto getGame();
}
