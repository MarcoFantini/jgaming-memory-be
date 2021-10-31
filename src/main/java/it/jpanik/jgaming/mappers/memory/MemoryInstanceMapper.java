package it.jpanik.jgaming.mappers.memory;

import it.jpanik.jgaming.dtos.memory.MemoryInstanceDto;
import it.jpanik.jgaming.entities.memory.MemoryInstance;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author Marco Fantini
 */
@Mapper
public interface MemoryInstanceMapper {

    MemoryInstanceMapper INSTANCE = Mappers.getMapper(MemoryInstanceMapper.class);

    MemoryInstanceDto memoryInstanceToMemoryInstanceDto(MemoryInstance memoryInstance);

    MemoryInstance memoryInstanceDtoToMemoryInstance(MemoryInstanceDto memoryInstanceDto);

    @InheritConfiguration(name = "memoryInstanceDtoToMemoryInstance")
    MemoryInstance updateMemoryInstanceFromMemoryInstanceDTo(MemoryInstanceDto memoryInstanceDto, @MappingTarget MemoryInstance memoryInstance);

    List<MemoryInstanceDto> memoryInstanceListToMemoryInstanceListDto(List<MemoryInstance> memoryInstanceList);

    List<MemoryInstance> memoryInstanceDtoListToMemoryInstanceList(List<MemoryInstanceDto> memoryInstanceDtos);

}
