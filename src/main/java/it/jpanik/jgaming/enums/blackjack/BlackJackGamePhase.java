package it.jpanik.jgaming.enums.blackjack;

public enum BlackJackGamePhase {

    WAITING_GAMERS, // Wait a gamer that sitting and plays
    SETTING_BETS, // Gamers set their bets
    SERVE_CARDS, // Dealer distributes cards to gamers
    GAMERS_PLAY, // Gamers play their hand (callCard/check)
    DEALER_PLAY, // Dealer play its hand
    COUNTING_CARDS, // Winners and losers
    WAITING_NEXT_ROUND, // Waiting next round

}