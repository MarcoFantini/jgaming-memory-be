package it.jpanik.jgaming.services.user;

import it.jpanik.jgaming.dtos.TestDto;
import it.jpanik.jgaming.dtos.UserDto;
import it.jpanik.jgaming.entities.Test;
import it.jpanik.jgaming.entities.User;
import it.jpanik.jgaming.enums.UserRole;
import it.jpanik.jgaming.exceptions.ServiceException;
import it.jpanik.jgaming.mappers.TestMapper;
import it.jpanik.jgaming.mappers.UserMapper;
import it.jpanik.jgaming.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author Jacopo Korompay
 */
@Service
public class UserServiceImpl implements UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(
            @Lazy final PasswordEncoder passwordEncoder,
            final UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @Override
    public UserDto findById(Long id) throws ServiceException {
        User user = userRepository.findById(id).orElseThrow(() -> new ServiceException("User not found"));
        return UserMapper.INSTANCE.userToUserDto(user);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new SecurityException("User not found"));
    }

    @Override
    public UserDto loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return UserMapper.INSTANCE.userToUserDto(user);
    }

    @Override
    public UserDto save(UserDto userDto) {
        User user = UserMapper.INSTANCE.userDtoToUser(userDto);
        user.setEmail(user.getEmail().toLowerCase());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(UserRole.GAMER);
        user = userRepository.save(user);

        return UserMapper.INSTANCE.userToUserDto(user);
    }

    @Override
    public UserDto update(UserDto userDto) {
        User user = userRepository.findById(userDto.getId()).orElseThrow(null);
        user = UserMapper.INSTANCE.updateUserFromUserDto(userDto, user);
        userRepository.save(user);
        return UserMapper.INSTANCE.userToUserDto(user);
    }
}
