package it.jpanik.jgaming.services.blackjack;

/**
 * @author Jacopo Korompay
 */
public class BlackJackEvent {

  public enum EventType {
    // @formatter:off
                          // args

    NEW_GAME,             //
    NEW_ROUND,            //
    PLAYER_RECEIVE_CARD,  // chair, card
    PLAYER_PLAY_HAND,     // chair
    PLAYER_SET_BET,       // chair, amount
    PLAYER_CALL_CARD,     // chair, card
    PLAYER_HAS_BLACKJACK, // chair
    PLAYER_CHECK,         // chair
    PLAYER_WIN_HAND,      // chair
    PLAYER_LOSE_HAND,     // chair, amount
    PLAYER_DRAFT_HAND,    // chair
    PLAYER_LOSE_GAME,     // chair
    GAMER_LEAVE_GAME,     // chair
    DEALER_PLAY,          //
    COUNTING_CARDS,       //
    COUNTDOWN_TIME,       // amount
    END_ROUND             //

    // @formatter:on
  }

  private EventType eventType;
  private int chair;
  private float amount;
  private BlackJackCard card;

  public BlackJackEvent() {}

  public BlackJackEvent(EventType eventType) {
    this.eventType = eventType;
  }

  public EventType getEventType() {
    return eventType;
  }

  public void setEventType(EventType eventType) {
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
