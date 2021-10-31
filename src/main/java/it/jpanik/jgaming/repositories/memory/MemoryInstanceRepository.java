package it.jpanik.jgaming.repositories.memory;

import it.jpanik.jgaming.entities.memory.MemoryInstance;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @author Marco Fantini
 */
public interface MemoryInstanceRepository extends CrudRepository<MemoryInstance,Long> {

    MemoryInstance findByName(String name);

    MemoryInstance findByIdAndName(Long id, String name);
}
