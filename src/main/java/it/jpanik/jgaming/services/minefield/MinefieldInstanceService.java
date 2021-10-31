package it.jpanik.jgaming.services.minefield;

import it.jpanik.jgaming.dtos.AckDto;
import it.jpanik.jgaming.dtos.minefield.MinefieldInstanceDto;
import it.jpanik.jgaming.exceptions.DuplicateException;
import it.jpanik.jgaming.exceptions.ServiceException;

import java.util.List;

/**
 * @author Jacopo Cervellini
 */
public interface MinefieldInstanceService {

    AckDto save(MinefieldInstanceDto instanceDto) throws DuplicateException;

    List<MinefieldInstanceDto> get();

    MinefieldInstanceDto delete(Long id) throws ServiceException;

    MinefieldInstanceDto edit(MinefieldInstanceDto instanceDto) throws ServiceException;

}
