package it.jpanik.jgaming.dtos.blackjack;

import java.util.List;

/**
 * @author Jacopo Korompay
 */
public class BlackJackDealerDto {

  private List<BlackJackCardDto> cards;

  public List<BlackJackCardDto> getCards() {
    return cards;
  }

  public void setCards(List<BlackJackCardDto> cards) {
    this.cards = cards;
  }
}
