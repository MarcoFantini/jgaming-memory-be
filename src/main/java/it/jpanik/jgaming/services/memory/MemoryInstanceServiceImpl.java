package it.jpanik.jgaming.services.memory;

import it.jpanik.jgaming.dtos.AckDto;
import it.jpanik.jgaming.dtos.memory.MemoryInstanceDto;
import it.jpanik.jgaming.entities.memory.MemoryInstance;
import it.jpanik.jgaming.exceptions.DuplicateException;
import it.jpanik.jgaming.exceptions.ServiceException;
import it.jpanik.jgaming.mappers.memory.MemoryInstanceMapper;
import it.jpanik.jgaming.repositories.memory.MemoryInstanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Marco Fantini
 */
@Service
public class MemoryInstanceServiceImpl implements MemoryInstanceService {

    private final MemoryInstanceRepository memoryInstanceRepository;
    private AckDto ackDto = new AckDto();

    @Autowired
    public MemoryInstanceServiceImpl(final MemoryInstanceRepository memoryInstanceRepository) {
        this.memoryInstanceRepository = memoryInstanceRepository;
    }

    @Override
    public AckDto save(MemoryInstanceDto memoryInstanceDto) throws DuplicateException {
        MemoryInstance memoryInstance = MemoryInstanceMapper.INSTANCE.memoryInstanceDtoToMemoryInstance(memoryInstanceDto);
        MemoryInstance instanceDb = this.memoryInstanceRepository.findByName(memoryInstance.getName());
        if (instanceDb != null) {
            throw new DuplicateException("Duplicate Name");
        } else {
            this.memoryInstanceRepository.save(memoryInstance);
            this.ackDto.setResult(true);
            return this.ackDto;
        }
    }

    @Override
    public MemoryInstanceDto delete(Long id) throws ServiceException {
        Optional<MemoryInstance> instance = this.memoryInstanceRepository.findById(id);
        instance.ifPresent(memoryInstanceRepository::delete);
        return instance.map(MemoryInstanceMapper.INSTANCE::memoryInstanceToMemoryInstanceDto).orElse(null);
    }

    @Override
    public AckDto edit(MemoryInstanceDto instanceDto) throws DuplicateException {
        MemoryInstance instanceDb = this.memoryInstanceRepository.findByName(instanceDto.getName());
        MemoryInstance instance = this.memoryInstanceRepository.findById(instanceDto.getId()).orElseThrow(null);
        instance = MemoryInstanceMapper.INSTANCE.updateMemoryInstanceFromMemoryInstanceDTo(instanceDto, instance);
        if (instanceDb != null) {
            if (instanceDb.getId().equals(instanceDto.getId())) {
                memoryInstanceRepository.save(instance);
                this.ackDto.setResult(true);
                return this.ackDto;
            } else {
                throw new DuplicateException("Duplicate Name");
            }
        } else {
            memoryInstanceRepository.save(instance);
            this.ackDto.setResult(true);
            return this.ackDto;
        }
    }

    @Override
    public List<MemoryInstanceDto> getAllMemoryInstanceDto() {
        List<MemoryInstance> result = new ArrayList<>();
        this.memoryInstanceRepository.findAll().forEach(result::add);
        return MemoryInstanceMapper.INSTANCE.memoryInstanceListToMemoryInstanceListDto(result);
    }
}
