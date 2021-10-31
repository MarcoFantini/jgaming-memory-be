package it.jpanik.jgaming.controllers.memory;

import it.jpanik.jgaming.dtos.memory.MemoryGameDto;
import it.jpanik.jgaming.services.memory.MemoryGameService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Marco Fantini
 */
@RestController
@RequestMapping("/memory-game")
public class MemoryGameController {
    private static final Logger LOGGER = LoggerFactory.getLogger(MemoryGameController.class);

    private final MemoryGameService memoryGameService;

    @Autowired
    public MemoryGameController(final MemoryGameService memoryGameService) {
        this.memoryGameService = memoryGameService;
    }

    @GetMapping("/{id}")
    public MemoryGameDto findById(@PathVariable Long id) {
        LOGGER.info("Called GET /memory-game/" + id);

        return memoryGameService.getMemoryGame(id);
    }

}
