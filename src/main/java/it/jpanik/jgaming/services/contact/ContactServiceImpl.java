package it.jpanik.jgaming.services.contact;

import it.jpanik.jgaming.dtos.ContactDto;
import it.jpanik.jgaming.dtos.UserDto;
import it.jpanik.jgaming.enums.ErrorMessage;
import it.jpanik.jgaming.exceptions.ServiceException;
import it.jpanik.jgaming.services.CustomService;
import it.jpanik.jgaming.utils.EmailUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Marco Fantini
 */
@Service
public class ContactServiceImpl extends CustomService implements ContactService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ContactServiceImpl.class);

    private final EmailUtils emailUtils;

    private final SpringTemplateEngine thymeleafTemplateEngine;

    public ContactServiceImpl(EmailUtils emailUtils, SpringTemplateEngine thymeleafTemplateEngine) {
        this.emailUtils = emailUtils;
        this.thymeleafTemplateEngine = thymeleafTemplateEngine;
    }

    @Override
    public void sendHtmlMessage(ContactDto contactDto, String htmlBody) throws MessagingException, ServiceException {
        MimeMessage message = emailUtils.getMailSender().createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
        helper.setFrom(emailUtils.getSenderMailAddress());
        helper.setTo(contactDto.getEmail());
        helper.setSubject(contactDto.getObject());
        helper.setText(htmlBody, true);

        try {
            emailUtils.getMailSender().send(message);
            LOGGER.info("E-mail sent");
        } catch (MailException e) {
            LOGGER.error("Error while sending e-email", e);
            throw new ServiceException(ErrorMessage.ERROR_SANDING_MAIL);
        }
    }

    @Override
    public void sendRegistrationConfirmMail(UserDto userDto) throws MessagingException, ServiceException {
        // create template model for thymeleaf html
        Map<String, Object> templateModel = new HashMap<>();;
        templateModel.put("recipientName", userDto.getUsername());
        templateModel.put("location", "Via Gianluigi Bonelli 40, Rome.");
        templateModel.put("logo", "logo.jpg");
        // create thymeleaf context for template model
        Context thymeleafContext = new Context();
        thymeleafContext.setVariables(templateModel);
        // thymeleaf template engine can process the html into context
        String htmlBody = thymeleafTemplateEngine.process("registration_confirm.html", thymeleafContext);
        // add reminder image to resource array list

        ContactDto contactDto = new ContactDto();
        contactDto.setEmail(userDto.getEmail());
        contactDto.setObject("Registration Confirm");

        sendHtmlMessage(contactDto, htmlBody);
    }

}
