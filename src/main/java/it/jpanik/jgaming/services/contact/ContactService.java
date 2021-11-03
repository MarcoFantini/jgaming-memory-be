package it.jpanik.jgaming.services.contact;

import it.jpanik.jgaming.dtos.AckDto;
import it.jpanik.jgaming.dtos.ContactDto;
import it.jpanik.jgaming.dtos.UserDto;
import org.hibernate.service.spi.ServiceException;
import org.springframework.core.io.Resource;

import javax.mail.MessagingException;
import java.util.ArrayList;

/**
 * @author Marco Fantini
 */
public interface ContactService {

    void sendHtmlMessage(ContactDto contactDto, String htmlBody)
            throws MessagingException, ServiceException, it.jpanik.jgaming.exceptions.ServiceException;

    void sendRegistrationConfirmMail(UserDto userDto) throws MessagingException, ServiceException, it.jpanik.jgaming.exceptions.ServiceException;
}
