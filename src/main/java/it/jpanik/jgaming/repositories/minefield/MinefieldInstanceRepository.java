package it.jpanik.jgaming.repositories.minefield;

import it.jpanik.jgaming.entities.minefield.MinefieldInstance;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Jacopo Cervellini
 */
public interface MinefieldInstanceRepository extends CrudRepository<MinefieldInstance,Long> {

MinefieldInstance findByName(String name);

}
