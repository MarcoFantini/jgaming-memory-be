package it.jpanik.jgaming.services;

import it.jpanik.jgaming.constants.Messages;
import it.jpanik.jgaming.dtos.AckDto;
import it.jpanik.jgaming.dtos.ContactDto;
import it.jpanik.jgaming.utils.EmailUtils;
import it.jpanik.jgaming.utils.MessageUtils;
import org.hibernate.service.spi.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamSource;
import org.springframework.core.io.Resource;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.activation.MimetypesFileTypeMap;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author Marco Fantini
 */
@Service
public class ContactServiceImpl extends CustomService implements ContactService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ContactServiceImpl.class);

    @Value("${spring.mail.templates.image.logo.path}")
    private Resource logoImageResource;

    private final EmailUtils emailUtils;

    private final SpringTemplateEngine thymeleafTemplateEngine;

    public ContactServiceImpl(EmailUtils emailUtils, SpringTemplateEngine thymeleafTemplateEngine) {
        this.emailUtils = emailUtils;
        this.thymeleafTemplateEngine = thymeleafTemplateEngine;
    }

    @Override
    public AckDto sendHtmlMessage(ContactDto contactDto, String htmlBody, ArrayList<Resource> resources) throws MessagingException {
        MimeMessage message = emailUtils.getMailSender().createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
        helper.setFrom(emailUtils.getSenderMailAddress());
        helper.setTo(contactDto.getEmail());
        helper.setSubject(contactDto.getObject());
        helper.setText(htmlBody, true);

        resources.add(logoImageResource);

        for (Resource resource: resources) {
            try {
                final InputStreamSource imageSource = new ByteArrayResource(resource.getInputStream().readAllBytes());
                helper.addInline(Objects.requireNonNull(resource.getFilename()), imageSource,
                        new MimetypesFileTypeMap().getContentType(resource.getFile()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try{
            emailUtils.getMailSender().send(message);
            LOGGER.info("E-mail sent");
            return ackOK(MessageUtils.INSTANCE.getMessage(Messages.ACK_CONTACT_OK));
        } catch (MailException e) {
            LOGGER.error("Error while sending e-email", e);
            throw new ServiceException(MessageUtils.INSTANCE.getMessage(Messages.ERROR_SERVICE_CONTACT_SENDING));
        }
    }

    @Override
    public AckDto sendGenericNotificationMail(ContactDto contactDto) throws MessagingException {
        Map<String, Object> templateModel = new HashMap<>();

        templateModel.put("recipientName", contactDto.getEmail());
        templateModel.put("notification_object", contactDto.getObject());
        templateModel.put("notification_message", contactDto.getMessage());
        templateModel.put("location", "Via Gianluigi Bonelli 40, Rome.");
        templateModel.put("logo", "logo.jpg");

        Context thymeleafContext = new Context();

        thymeleafContext.setVariables(templateModel);
        String htmlBody = thymeleafTemplateEngine.process("notification.html", thymeleafContext);

        return sendHtmlMessage(contactDto, htmlBody, new ArrayList<>());
    }
}
