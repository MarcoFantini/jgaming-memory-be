package it.jpanik.jgaming.services.blackjack;

import it.jpanik.jgaming.controllers.blackjack.BlackJackController;
import it.jpanik.jgaming.dtos.blackjack.BlackJackGameDto;
import it.jpanik.jgaming.mappers.blackjack.BlackJackEventMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class BlackJackMessagingTemplate {

    private final SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    public BlackJackMessagingTemplate(final SimpMessagingTemplate simpMessagingTemplate) {
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    public void sendUpdate(BlackJackGameDto gameDto) {
        simpMessagingTemplate.convertAndSend("/blackjack", gameDto);
    }

    public void sendEvent(BlackJackEvent event) {
        simpMessagingTemplate.convertAndSend("/blackjack/event", BlackJackEventMapper.INSTANCE.eventToEventDto(event));
    }
}
