package it.jpanik.jgaming.services.blackjack;

import it.jpanik.jgaming.services.blackjack.player.BlackJackGamer;

/**
 * @author Jacopo Korompay
 */
public class BlackJackBet {

    private final BlackJackGamer gamer;
    private final float value;

    public BlackJackBet(BlackJackGamer gamer, float value) {
        this.gamer = gamer;
        this.value = value;
    }

    /**
     * @return The gamer who has setting bet
     */
    public BlackJackGamer getGamer() {
        return gamer;
    }

    public float getValue() {
        return value;
    }
}
