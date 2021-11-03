package it.jpanik.jgaming.services.blackjack.services;

import it.jpanik.jgaming.dtos.blackjack.BlackJackGameDto;
import it.jpanik.jgaming.services.blackjack.BlackJackGame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Jacopo Korompay
 */
@Service
public class BlackJackServiceImpl implements BlackJackService {

    private final BlackJackGame game;

    @Autowired
    public BlackJackServiceImpl(final BlackJackGame game) {
        this.game = game;
    }

    @Override
    public void addGamer(String username, int chair) {
        game.addGamer(username, chair);
    }

    @Override
    public void setBet(int chair, float value) {
        game.setBet(chair, value);
    }

    @Override
    public void callCard(int chair) {
        game.callCard(chair);
    }

    @Override
    public void check(int chair) {
        game.check(chair);
    }

    @Override
    public void leaveGame(int chair) {
        game.leaveGame(chair);
    }

    @Override
    public void resetGame() {
        game.resetGame();
    }

    @Override
    public BlackJackGameDto getGame() {
        return game.getGame();
    }

}
