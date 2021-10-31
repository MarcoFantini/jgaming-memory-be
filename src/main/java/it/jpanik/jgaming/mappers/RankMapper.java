package it.jpanik.jgaming.mappers;

import it.jpanik.jgaming.dtos.RankDto;
import it.jpanik.jgaming.entities.Rank;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author Jacopo Cervellini
 */
@Mapper
public interface RankMapper {

    RankMapper INSTANCE = Mappers.getMapper(RankMapper.class);

    @Mapping(target = "username", source = "user.username")
    RankDto rankToRankDto(Rank rank);

    Rank rankDtoToRank(RankDto rankDto);

    @InheritConfiguration(name = "rankDtoToRank")
    Rank updateRankFromRankDTo(RankDto rankDto, @MappingTarget Rank rank);

    List<RankDto> rankListToRankListDto(List<Rank> rankList);

    List<Rank> rankDtoToListToRankList(List<RankDto> rankDtos);
}
