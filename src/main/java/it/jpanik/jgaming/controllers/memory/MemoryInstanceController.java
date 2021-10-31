package it.jpanik.jgaming.controllers.memory;

import it.jpanik.jgaming.dtos.AckDto;
import it.jpanik.jgaming.dtos.memory.MemoryInstanceDto;
import it.jpanik.jgaming.exceptions.DuplicateException;
import it.jpanik.jgaming.exceptions.ServiceException;
import it.jpanik.jgaming.services.memory.MemoryInstanceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Marco Fantini
 */
@RestController
@RequestMapping("/memory-instances")
public class MemoryInstanceController {
    private static final Logger LOGGER = LoggerFactory.getLogger(MemoryInstanceController.class);

    private final MemoryInstanceService memoryInstanceService;

    @Autowired
    public MemoryInstanceController(final MemoryInstanceService memoryService) {
        this.memoryInstanceService = memoryService;
    }

    @PostMapping
    public AckDto save(@RequestBody MemoryInstanceDto instanceDto) throws DuplicateException {
        LOGGER.info("Called POST /memory-instances");

        return memoryInstanceService.save(instanceDto);
    }

    @GetMapping
    public List<MemoryInstanceDto> getMemoryInstances() {
        LOGGER.info("Called GET /memory-instances");

        return memoryInstanceService.getAllMemoryInstanceDto();
    }

    @DeleteMapping("/{id}")
    public MemoryInstanceDto deleteInstance(@PathVariable Long id) throws ServiceException {
        LOGGER.info("Called DELETE /memory-instances/" + id);

        return memoryInstanceService.delete(id);
    }

    @PutMapping
    public AckDto updateInstance(@RequestBody MemoryInstanceDto instanceDto) throws DuplicateException {
        LOGGER.info("Called PUT /memory-instances");

        return memoryInstanceService.edit(instanceDto);
    }

}
