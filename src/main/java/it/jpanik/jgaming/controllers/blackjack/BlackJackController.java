package it.jpanik.jgaming.controllers.blackjack;

import it.jpanik.jgaming.dtos.blackjack.BlackJackGameDto;
import it.jpanik.jgaming.mappers.blackjack.BlackJackEventMapper;
import it.jpanik.jgaming.services.BlackJackService;
import it.jpanik.jgaming.services.blackjack.BlackJackEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

/**
 * @author Jacopo Korompay
 */
@Controller
public class BlackJackController {

    private final SimpMessagingTemplate simpMessagingTemplate;
    private final BlackJackService blackJackService;

    @Autowired
    public BlackJackController(final SimpMessagingTemplate simpMessagingTemplate,
                               final BlackJackService blackJackService) {
        this.simpMessagingTemplate = simpMessagingTemplate;
        this.blackJackService = blackJackService;
    }

    @MessageMapping("/getGame/{username}")
    public void getGame(@DestinationVariable String username) {
        simpMessagingTemplate.convertAndSend("/blackjack/getGame/" + username, blackJackService.getGame());
    }

    @MessageMapping("/sitAndPlay/{username}-{chair}")
    public void sitAndPlay(@DestinationVariable String username, @DestinationVariable int chair) {
        blackJackService.addGamer(username, chair);
    }

    @MessageMapping("/setBet/{chair}-{value}")
    public void setBet(@DestinationVariable int chair, @DestinationVariable float value) {
        blackJackService.setBet(chair, value);
    }

    @MessageMapping("/callCard/{chair}")
    public void callCard(@DestinationVariable int chair) {
        blackJackService.callCard(chair);
    }

    @MessageMapping("/check/{chair}")
    public void check(@DestinationVariable int chair) {
        blackJackService.check(chair);
    }

    @MessageMapping("/leaveGame/{chair}")
    public void leaveGame(@DestinationVariable int chair) {
        blackJackService.leaveGame(chair);
    }

    @MessageMapping("/resetGame")
    public void resetGame() {
        blackJackService.resetGame();
    }

}
