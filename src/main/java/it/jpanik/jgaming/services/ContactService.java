package it.jpanik.jgaming.services;

import it.jpanik.jgaming.dtos.AckDto;
import it.jpanik.jgaming.dtos.ContactDto;
import org.hibernate.service.spi.ServiceException;
import org.springframework.core.io.Resource;

import javax.mail.MessagingException;
import java.util.ArrayList;

/**
 * @author Marco Fantini
 */
public interface ContactService {

    AckDto sendHtmlMessage(ContactDto contactDto, String htmlBody, ArrayList<Resource> resources) throws MessagingException;

    AckDto sendGenericNotificationMail(ContactDto contactDto) throws MessagingException;
}
