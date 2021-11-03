package it.jpanik.jgaming.services.user;

import it.jpanik.jgaming.dtos.UserDto;
import it.jpanik.jgaming.entities.User;
import it.jpanik.jgaming.exceptions.ServiceException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * @author Jacopo Korompay
 */
public interface UserService extends UserDetailsService {

    UserDto findById(Long id) throws ServiceException;

    User findByUsername(String username);

    UserDto save(UserDto userDto);

    UserDto loadUserByUsername(String username) throws UsernameNotFoundException;
}
