package it.jpanik.jgaming.controllers;

import it.jpanik.jgaming.dtos.RankDto;
import it.jpanik.jgaming.dtos.RankUpdateDto;
import it.jpanik.jgaming.enums.GameType;
import it.jpanik.jgaming.exceptions.ServiceException;
import it.jpanik.jgaming.services.rank.RankService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Jacopo Cervellini
 */
@RestController
@RequestMapping("/ranks")
public class RankController {
    private static final Logger LOGGER = LoggerFactory.getLogger(RankController.class);

    private final RankService rankService;

    @Autowired
    public RankController(final RankService rankService) {
        this.rankService = rankService;
    }

    @PostMapping
    public RankDto save(@RequestBody RankUpdateDto rankUpdateDto) throws ServiceException {
        LOGGER.info("Called POST /ranks");
        return rankService.save(rankUpdateDto);
    }

    @GetMapping("/{gameType}")
    public List<RankDto> getRank(@PathVariable GameType gameType) {
        LOGGER.info("Called GET /ranks/" + gameType.toString());
        return rankService.getRank(gameType);
    }

}
