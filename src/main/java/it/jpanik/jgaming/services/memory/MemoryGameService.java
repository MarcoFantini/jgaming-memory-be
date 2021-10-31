package it.jpanik.jgaming.services.memory;

import it.jpanik.jgaming.dtos.memory.MemoryGameDto;

/**
 * @author Marco Fantini
 */
public interface MemoryGameService {

    MemoryGameDto getMemoryGame(Long id);

}
