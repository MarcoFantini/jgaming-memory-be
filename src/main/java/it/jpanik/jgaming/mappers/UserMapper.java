package it.jpanik.jgaming.mappers;

import it.jpanik.jgaming.dtos.UserDto;
import it.jpanik.jgaming.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

/**
 * @author Jacopo Korompay
 */
@Mapper
public interface UserMapper {

  UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

  UserDto userToUserDto(User user);

  User userDtoToUser(UserDto userDto);

  User updateUserFromUserDto(UserDto userDto, @MappingTarget User user);
}
