package it.jpanik.jgaming.controllers;

import it.jpanik.jgaming.dtos.LoginDto;
import it.jpanik.jgaming.dtos.UserDto;
import it.jpanik.jgaming.exceptions.ServiceException;
import it.jpanik.jgaming.services.contact.ContactService;
import it.jpanik.jgaming.services.user.UserService;
import it.jpanik.jgaming.utils.JwtTokenProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.MessagingException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Jacopo Korompay
 */
@RestController
@RequestMapping("/user")
public class UserController {
  private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

  private final UserService userService;
  private final ContactService contactService;
  private final AuthenticationManager authenticationManager;
  private final SpringTemplateEngine thymeleafTemplateEngine;
  private final JwtTokenProvider jwtTokenUtil;

  @Autowired
  public UserController(
          final UserService userService,
          ContactService contactService, final AuthenticationManager authenticationManager,
          SpringTemplateEngine thymeleafTemplateEngine, final JwtTokenProvider jwtTokenUtil
  ) {
    this.userService = userService;
    this.contactService = contactService;
    this.authenticationManager = authenticationManager;
    this.thymeleafTemplateEngine = thymeleafTemplateEngine;
    this.jwtTokenUtil = jwtTokenUtil;
  }

  @PostMapping
  public UserDto save(@RequestBody UserDto userDto) throws MessagingException, ServiceException {
    LOGGER.info("Called POST /user");
    contactService.sendRegistrationConfirmMail(userDto);
    return userService.save(userDto);
  }

  @PostMapping("/login")
  public UserDto login(@RequestBody LoginDto loginDto) {
    LOGGER.info("Called POST /user/login");

    Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                    loginDto.getUsername(),
                    loginDto.getPassword()
            )
    );

    SecurityContextHolder.getContext().setAuthentication(authentication);

    UserDto response = (UserDto) authentication.getPrincipal();
    String jwt = jwtTokenUtil.generateToken(response.getId());
    response.setToken(jwt);

    return response;
  }

  @GetMapping("/activate/{username}")
  public String activateUser(@PathVariable String username) {
    LOGGER.info("Called GET - USER ACTIVATE");
    UserDto userDto = userService.loadUserByUsername(username);
    userDto.enableUser();
    userService.update(userDto);
    Map<String, Object> templateModel = new HashMap<>();
    templateModel.put("username", username);
    Context thymeleafContext = new Context();
    thymeleafContext.setVariables(templateModel);
    return thymeleafTemplateEngine.process("active_user.html", thymeleafContext);
  }
}
