package it.jpanik.jgaming.services.minefield;

import it.jpanik.jgaming.dtos.minefield.MinefieldGameDto;

/**
 * @author Jacopo Cervellini
 */
public interface MinefieldGameService {

    MinefieldGameDto getMinefieldGame(Long id);

}
