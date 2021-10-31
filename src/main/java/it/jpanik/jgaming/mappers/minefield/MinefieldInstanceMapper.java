package it.jpanik.jgaming.mappers.minefield;

import it.jpanik.jgaming.dtos.minefield.MinefieldInstanceDto;
import it.jpanik.jgaming.entities.minefield.MinefieldInstance;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author Jacopo Cervellini
 */
@Mapper
public interface MinefieldInstanceMapper {

    MinefieldInstanceMapper INSTANCE = Mappers.getMapper(MinefieldInstanceMapper.class);

    MinefieldInstanceDto minefieldInstanceToMinefieldInstanceDto(MinefieldInstance instance);

    MinefieldInstance minefieldInstanceDtoToMinefieldInstance(MinefieldInstanceDto instanceDto);

    @InheritConfiguration(name = "minefieldInstanceDtoToMinefieldInstance")
    MinefieldInstance updateMinefieldInstanceFromMinefieldInstanceDTo(MinefieldInstanceDto instanceDto, @MappingTarget MinefieldInstance instance);

    List<MinefieldInstanceDto> minefieldInstanceListToMinefieldInstanceListDto(List<MinefieldInstance> instanceList);

    List<MinefieldInstance> minefieldInstanceDtoListToMinefieldInstanceList(List<MinefieldInstanceDto> instanceDtos);
}
