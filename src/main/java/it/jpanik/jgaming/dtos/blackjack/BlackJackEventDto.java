package it.jpanik.jgaming.dtos.blackjack;

import it.jpanik.jgaming.services.blackjack.BlackJackCard;
import it.jpanik.jgaming.services.blackjack.BlackJackEvent;

/**
 * @author Jacopo Korompay
 */
public class BlackJackEventDto {

  private BlackJackEvent.EventType eventType;
  private int chair;
  private float amount;
  private BlackJackCard card;

  public BlackJackEvent.EventType getEventType() {
    return eventType;
  }

  public void setEventType(BlackJackEvent.EventType eventType) {
    this.eventType = eventType;
  }

  public int getChair() {
    return chair;
  }

  public void setChair(int chair) {
    this.chair = chair;
  }

  public float getAmount() {
    return amount;
  }

  public void setAmount(float amount) {
    this.amount = amount;
  }

  public BlackJackCard getCard() {
    return card;
  }

  public void setCard(BlackJackCard card) {
    this.card = card;
  }
}
