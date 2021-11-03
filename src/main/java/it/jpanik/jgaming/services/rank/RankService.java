package it.jpanik.jgaming.services.rank;

import it.jpanik.jgaming.dtos.RankDto;
import it.jpanik.jgaming.dtos.RankUpdateDto;
import it.jpanik.jgaming.entities.Rank;
import it.jpanik.jgaming.entities.User;
import it.jpanik.jgaming.enums.GameType;
import it.jpanik.jgaming.exceptions.ServiceException;

import java.util.List;

/**
 * @author Jacopo Cervellini
 */
public interface RankService {

    RankDto save(RankUpdateDto rankUpdateDto) throws ServiceException;

    List<RankDto> getRank(GameType gameType);

    Rank getByUserAndGameType(User user, GameType gameType) throws ServiceException;

}
