package it.jpanik.jgaming.controllers.minefield;

import it.jpanik.jgaming.dtos.AckDto;
import it.jpanik.jgaming.dtos.minefield.MinefieldInstanceDto;
import it.jpanik.jgaming.exceptions.DuplicateException;
import it.jpanik.jgaming.exceptions.ServiceException;
import it.jpanik.jgaming.services.minefield.MinefieldInstanceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Jacopo Cervellini
 */
@RestController
@RequestMapping("/minefield-instances")
public class MinefieldInstanceController {
    private static final Logger LOGGER = LoggerFactory.getLogger(MinefieldInstanceController.class);

    private final MinefieldInstanceService minefieldInstanceService;

    @Autowired
    public MinefieldInstanceController(final MinefieldInstanceService minefieldService) {
        this.minefieldInstanceService = minefieldService;
    }

    @PostMapping
    public AckDto save(@RequestBody MinefieldInstanceDto instanceDto) throws DuplicateException {
        LOGGER.info("Called POST /minefield-instances");

        return minefieldInstanceService.save(instanceDto);
    }

    @GetMapping
    public List<MinefieldInstanceDto> getMinefieldInstances() {
        LOGGER.info("Called GET /minefield-instances");

        return minefieldInstanceService.get();
    }

    @DeleteMapping("/{id}")
    public MinefieldInstanceDto deleteInstance(@PathVariable Long id) throws ServiceException {
        LOGGER.info("Called DELETE /minefield-instances/" + id);

        return minefieldInstanceService.delete(id);
    }

    @PutMapping
    public MinefieldInstanceDto updateInstance(@RequestBody MinefieldInstanceDto instanceDto) throws ServiceException {
        LOGGER.info("Called PUT /minefield-instances");

        return minefieldInstanceService.edit(instanceDto);
    }
}
