package it.jpanik.jgaming.services.memory;

import it.jpanik.jgaming.dtos.AckDto;
import it.jpanik.jgaming.dtos.memory.MemoryInstanceDto;
import it.jpanik.jgaming.exceptions.DuplicateException;
import it.jpanik.jgaming.exceptions.ServiceException;

import java.util.List;

/**
 * @author Marco Fantini
 */
public interface MemoryInstanceService {

    AckDto save(MemoryInstanceDto instanceDto) throws DuplicateException;

    List<MemoryInstanceDto> getAllMemoryInstanceDto();

    MemoryInstanceDto delete(Long id) throws ServiceException;

    AckDto edit(MemoryInstanceDto instanceDto) throws DuplicateException;

}
