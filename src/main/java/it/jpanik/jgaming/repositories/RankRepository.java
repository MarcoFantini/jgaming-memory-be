package it.jpanik.jgaming.repositories;

import it.jpanik.jgaming.entities.Rank;
import it.jpanik.jgaming.entities.User;
import it.jpanik.jgaming.enums.GameType;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author Jacopo Cervellini
 */
public interface RankRepository extends CrudRepository<Rank,Long> {

    List<Rank> findByGameTypeOrderByScoreDesc(GameType gameType);

    Optional<Rank> findByUserAndGameType(User username, GameType gameType);

}
