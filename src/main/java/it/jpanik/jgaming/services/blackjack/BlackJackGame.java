package it.jpanik.jgaming.services.blackjack;

import it.jpanik.jgaming.dtos.blackjack.BlackJackGameDto;
import it.jpanik.jgaming.entities.User;
import it.jpanik.jgaming.enums.GameType;
import it.jpanik.jgaming.enums.blackjack.BlackJackGamePhase;
import it.jpanik.jgaming.exceptions.ServiceException;
import it.jpanik.jgaming.exceptions.blackjack.BlackJackDealerLoseHandException;
import it.jpanik.jgaming.mappers.blackjack.BlackJackGameMapper;
import it.jpanik.jgaming.services.rank.RankServiceImpl;
import it.jpanik.jgaming.services.user.UserServiceImpl;
import it.jpanik.jgaming.services.blackjack.player.BlackJackDealer;
import it.jpanik.jgaming.services.blackjack.player.BlackJackGamer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @author Jacopo Korompay
 */
@Component
@Scope("prototype")
public class BlackJackGame {

    public static final int GAMERS = 7;

    private final BlackJackMessagingTemplate blackJackMessagingTemplate;

    private BlackJackDealer dealer;
    private List<BlackJackGamer> gamers;
    private List<BlackJackGamer> waitingGamers;
    private int inPlay;
    private BlackJackGamePhase gamePhase;
    private BlackJackRound round;
    private int roundNumber;
    private final UserServiceImpl userService;
    private final RankServiceImpl rankService;

    @Autowired
    public BlackJackGame(final UserServiceImpl userService,
                         final RankServiceImpl rankService,
                         final BlackJackMessagingTemplate blackJackMessagingTemplate) {
        this.blackJackMessagingTemplate = blackJackMessagingTemplate;
        this.userService = userService;
        this.rankService = rankService;
        waitingGamers = new ArrayList<>();
        // waitingGamers.add(new Gamer("UserTest", 5));
        setupNewGame();
    }

    /**
     * @return The gamer's chair that playing the hand
     */
    public int getInPlay() {
        return inPlay;
    }

    public void setInPlay(int inPlay) {
        this.inPlay = inPlay;
    }

    public BlackJackDealer getDealer() {
        return dealer;
    }

    public void setDealer(BlackJackDealer dealer) {
        this.dealer = dealer;
    }

    /**
     * @return A list of sitting gamers
     */
    public List<BlackJackGamer> getGamers() {
        return gamers;
    }

    public void setGamers(List<BlackJackGamer> gamers) {
        this.gamers = gamers;
    }

    /**
     * @return Gamers waiting for next round
     */
    public List<BlackJackGamer> getWaitingGamers() {
        return waitingGamers;
    }

    public void setWaitingGamers(List<BlackJackGamer> waitingGamers) {
        this.waitingGamers = waitingGamers;
    }

    public BlackJackGamePhase getGamePhase() {
        return gamePhase;
    }

    public void setGamePhase(BlackJackGamePhase gamePhase) {
        this.gamePhase = gamePhase;
    }

    /**
     * @return The current round
     */
    public BlackJackRound getRound() {
        return round;
    }

    public void setRound(BlackJackRound round) {
        this.round = round;
    }

    public int getRoundNumber() {
        return roundNumber;
    }

    public void setRoundNumber(int roundNumber) {
        this.roundNumber = roundNumber;
    }

    /**
     * setup a new game
     */
    private void setupNewGame() {
        dealer = new BlackJackDealer();
        dealer.setGame(this);
        roundNumber = 0;
        inPlay = -1;
        initGamersArray();
        gamePhase = BlackJackGamePhase.WAITING_GAMERS;
        sendEvent(BlackJackEvent.EventType.NEW_GAME, null, null, null);
    }

    /**
     * Start a new round
     */
    public void startNewRound() {
        // Removing loser gamers
        for (int i = 0; i < gamers.size(); i++) {
            if (gamers.get(i) != null && gamers.get(i).getBalance() <= 0) gamers.set(i, null);
        }

        // Setting waiting gamers
        for (BlackJackGamer w_gamer : waitingGamers) gamers.set(w_gamer.getChair(), w_gamer);
        waitingGamers.clear();

        // Clear gamer's hand
        for (BlackJackGamer gamer : gamers) if (gamer != null) gamer.clearHand();

        dealer.clearHand();
        // If the number of deck cards is below of 37 then dealer takes a new deck
        if (dealer.getDeck().getSize() < 37) dealer.newDeck();
        round = new BlackJackRound(this, gamers);
        round.setRoundNumber(roundNumber++);

        sendEvent(BlackJackEvent.EventType.NEW_ROUND, null, null, null);
        gamePhase = BlackJackGamePhase.SETTING_BETS;
        waitFor(4L);
        sendUpdate();

        nextPlayer();
    }

    /**
     * Return a chair's number of next player
     *
     * @return The chair's number of next player
     */
    public int nextPlayer() {
        int next = -1;
        List<BlackJackGamer> roundGamers = round.getRoundGamers();
        for (int i = inPlay + 1; i < roundGamers.size(); i++) {
            if (roundGamers.get(i) != null && roundGamers.get(i).computePoints() != 21) {
                next = roundGamers.get(i).getChair();
                break;
            }
        }
        inPlay = next;
        sendEvent(BlackJackEvent.EventType.PLAYER_PLAY_HAND, inPlay, null, null);
        return inPlay;
    }

    /**
     * Reset game
     */
    public void resetGame() {
        waitingGamers.clear();
        round = null;
        setupNewGame();
        startNewRound();
    }

    /**
     * A gamer starts playing game by clicking on an empty chair. If player is first player of the
     * game the game start, otherwise the player wait for the new round.
     *
     * <p>Players are NOT queued to the list: their sitting order must be reflected into gamers array.
     */
    public void addGamer(String username, int chair) {
        if (waitingGamers.stream().anyMatch(gamer -> gamer.getUsername().equals(username)))
            return;
        if (gamers.stream().anyMatch(gamer -> gamer != null && gamer.getUsername().equals(username)))
            return;
        BlackJackGamer gamer = new BlackJackGamer(username, chair);
        try {
            User user = userService.findByUsername(username);
            gamer.setBalance(rankService.getByUserAndGameType(user, GameType.BLACKJACK).getScore());
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        waitingGamers.add(gamer);
        if (gamers.stream().noneMatch(Objects::nonNull)) {
            setupNewGame();
            startNewRound();
        }
    }

    /**
     * A gamer sets bet. Hand pass to next gamer.
     *
     * @param chair gamer's chair
     * @param value bet's value
     */
    public void setBet(int chair, float value) {
        BlackJackGamer gamer = getGamerByChair(chair);
        sendEvent(BlackJackEvent.EventType.PLAYER_SET_BET, gamer.getChair(), value, null);
        round.addBet(new BlackJackBet(gamer, value));
        if (nextPlayer() == -1) {
            waitFor(2L);
            dealerServeAllGamers();
        }
    }

    /**
     * A gamer do a check.
     *
     * @param chair gamer's chair
     */
    public void check(int chair) {
        BlackJackGamer gamer = getGamerByChair(chair);
        sendEvent(BlackJackEvent.EventType.PLAYER_CHECK, gamer.getChair(), null, null);
        waitFor(2L);
        if (nextPlayer() == -1) {
            gamePhase = BlackJackGamePhase.DEALER_PLAY;
            sendUpdate();
            dealerPlay();
        }
    }

    /**
     * A gamer call card.
     *
     * @param chair gamer's chair
     */
    public void callCard(int chair) {
        BlackJackGamer gamer = getGamerByChair(chair);
        dealer.serveCardToGamer(gamer);
    }

    /**
     * The dealer serve cards to all gamers
     */
    public void dealerServeAllGamers() {
        dealer.serveAllGamers();
        gamePhase = BlackJackGamePhase.GAMERS_PLAY;
        sendUpdate();
        waitFor(2L);
        nextPlayer();
    }

    /**
     * Dealer's turn
     */
    public void dealerPlay() {
        try {
            sendEvent(BlackJackEvent.EventType.DEALER_PLAY, null, null, null);
            waitFor(4L);
            dealer.play(); // Dealer plays its hand
            countingCards();
        } catch (BlackJackDealerLoseHandException e) {
            sendEvent(BlackJackEvent.EventType.PLAYER_LOSE_HAND, -1, null, null);
            dealer.dealerLoseHand();
            waitFor(2L);
            endRound();
        }
    }

    /**
     * Dealer counts gamer's cards
     */
    private void countingCards() {
        if (round.getRoundGamers().stream().allMatch(Objects::isNull)) {
            waitFor(2L);
            endRound();
        } else {
            waitFor(2L);
            sendEvent(BlackJackEvent.EventType.COUNTING_CARDS, null, null, null);
            waitFor(4L);
            dealer.countingCards(); // Dealer counts gamers card
            waitFor(2L);
            endRound();
        }
    }

    private void endRound() {
        gamePhase = BlackJackGamePhase.WAITING_NEXT_ROUND;
        sendUpdate();
        sendEvent(BlackJackEvent.EventType.END_ROUND, null, null, null);
        // In questo modo è garantita la sync tra i client
        final int[] time = {11};
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(
                new TimerTask() {
                    @Override
                    public void run() {
                        if (time[0] > 1) {
                            time[0]--;
                            sendEvent(BlackJackEvent.EventType.COUNTDOWN_TIME, null, (float) time[0], null);
                        } else {
                            sendEvent(BlackJackEvent.EventType.COUNTDOWN_TIME, null, 0F, null);
                            startNewRound();
                            timer.cancel();
                        }
                    }
                },
                0,
                1000);
    }

    /**
     * A method that is used to give a timing to the game
     *
     * @param seconds Waiting time in seconds
     */
    public void waitFor(long seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * A gamer leaves the game
     */
    public void leaveGame(int chair) {
        // removes gamer from waiting gamers if present
        waitingGamers.removeIf(gamer -> gamer.getChair() == chair);
        // decreases gamer's balance if has bet
        round.getPot().removeIf(bet -> bet.getGamer().getChair() == chair);
        // removes gamer from round's gamers
        round.getRoundGamers().removeIf(gamer -> gamer.getChair() == chair);
        // removes gamer from game's gamers
        gamers.removeIf(gamer -> gamer.getChair() == chair);
        // sends event to all gamers
        sendEvent(BlackJackEvent.EventType.GAMER_LEAVE_GAME, chair, null, null);
    }

    /**
     * Fill gamers array with null value
     */
    private void initGamersArray() {
        gamers = new ArrayList<>(GAMERS);
        for (int i = 0; i < GAMERS; i++) gamers.add(null);
    }

    /**
     * @param chair Gamer's chair
     * @return The gamer seated in the specified chair. Throw an runtime exception if chair is empty.
     */
    private BlackJackGamer getGamerByChair(int chair) {
        return gamers.stream()
                .filter(gamer -> gamer != null && gamer.getChair() == chair)
                .findAny()
                .orElseThrow(() -> new RuntimeException("Gamer not found."));
    }


    /**
     * Sends game's status to all users
     */
    public void sendUpdate() {
        BlackJackGameDto gameDto = BlackJackGameMapper.INSTANCE.gameToGameDto(this);
        if (round != null) gameDto.getRound().setPot(round.computePot());
        blackJackMessagingTemplate.sendUpdate(gameDto);
    }

    /**
     * @return The game
     */
    public BlackJackGameDto getGame() {
        BlackJackGameDto gameDto = BlackJackGameMapper.INSTANCE.gameToGameDto(this);
        if (round != null) gameDto.getRound().setPot(round.computePot());
        return gameDto;
    }

    /**
     * Sends game's event to all users
     */
    public void sendEvent(BlackJackEvent.EventType eventType, Integer chair, Float amount, BlackJackCard card) {
        BlackJackEvent event = new BlackJackEvent(eventType);
        if (chair != null) event.setChair(chair);
        if (amount != null) event.setAmount(amount);
        if (card != null) event.setCard(card);
        blackJackMessagingTemplate.sendEvent(event);
    }

    /**
     * Gamer loses his hand
     *
     * @param gamer The gamer who lost hand
     */
    public void gamerLosesHand(BlackJackGamer gamer) {
        BlackJackBet bet = getRound().getBetByGamer(gamer);
        sendEvent(BlackJackEvent.EventType.PLAYER_LOSE_HAND, gamer.getChair(), bet.getValue(), null);
        getRound().getPot().remove(bet);
        if (gamer.gameOver()) sendEvent(BlackJackEvent.EventType.PLAYER_LOSE_GAME, gamer.getChair(), null, null);
    }
}
