package it.jpanik.jgaming.controllers;

import it.jpanik.jgaming.dtos.AckDto;
import it.jpanik.jgaming.dtos.ContactDto;
import it.jpanik.jgaming.dtos.UserDto;
import it.jpanik.jgaming.exceptions.ServiceException;
import it.jpanik.jgaming.services.contact.ContactService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;

/**
 * @author Marco Fantini
 */
@RestController
@RequestMapping("/contact")
public class ContactController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ContactController.class);

    private final ContactService contactService;

    @Autowired
    public ContactController(final ContactService contactService) {
        this.contactService = contactService;
    }

    @PostMapping
    public void send(@RequestBody UserDto userDto) throws MessagingException, ServiceException {
        LOGGER.debug("Called POST /contact with body {}", userDto);
        contactService.sendRegistrationConfirmMail(userDto);
    }

}
