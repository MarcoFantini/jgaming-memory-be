package it.jpanik.jgaming.controllers.minefield;

import it.jpanik.jgaming.dtos.minefield.MinefieldGameDto;
import it.jpanik.jgaming.services.minefield.MinefieldGameService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Jacopo Cervellini
 */
@RestController
@RequestMapping("/minefield-game")
public class MinefieldGameController {
    private static final Logger LOGGER = LoggerFactory.getLogger(MinefieldGameController.class);

    private final MinefieldGameService minefieldGameService;

    @Autowired
    public MinefieldGameController(final MinefieldGameService minefieldGameService) {
        this.minefieldGameService = minefieldGameService;
    }

    @GetMapping("/{id}")
    public MinefieldGameDto findById(@PathVariable Long id) {
        LOGGER.info("Called GET /minefield-game/" + id);

        return minefieldGameService.getMinefieldGame(id);
    }

}
