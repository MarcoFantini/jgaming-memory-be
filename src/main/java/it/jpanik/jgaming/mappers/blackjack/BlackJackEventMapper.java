package it.jpanik.jgaming.mappers.blackjack;

import it.jpanik.jgaming.dtos.blackjack.BlackJackEventDto;
import it.jpanik.jgaming.services.blackjack.BlackJackEvent;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

/**
 * @author Jacopo Korompay
 */
@Mapper
public interface BlackJackEventMapper {

  BlackJackEventMapper INSTANCE = Mappers.getMapper(BlackJackEventMapper.class);

  BlackJackEventDto eventToEventDto(BlackJackEvent event);

  BlackJackEvent eventDtoToEvent(BlackJackEventDto eventDto);

  @InheritConfiguration(name = "eventDtoToEvent")
  BlackJackEvent updateEventFromEventDTo(BlackJackEventDto eventDto, @MappingTarget BlackJackEvent event);
}
