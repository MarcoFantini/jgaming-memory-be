package it.jpanik.jgaming.mappers.blackjack;

import it.jpanik.jgaming.dtos.blackjack.BlackJackGameDto;
import it.jpanik.jgaming.services.blackjack.BlackJackGame;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

/**
 * @author Jacopo Korompay
 */
@Mapper
public interface BlackJackGameMapper {

  BlackJackGameMapper INSTANCE = Mappers.getMapper(BlackJackGameMapper.class);

  @Mapping(source = "round.pot", target = "round.pot", ignore = true)
  BlackJackGameDto gameToGameDto(BlackJackGame game);

  @Mapping(source = "round.pot", target = "round.pot", ignore = true)
  BlackJackGame gameDtoToGame(BlackJackGameDto gameDto);

  @InheritConfiguration(name = "gameDtoToGame")
  BlackJackGame updateGameFromGameDTo(BlackJackGameDto gameDto, @MappingTarget BlackJackGame game);
}
