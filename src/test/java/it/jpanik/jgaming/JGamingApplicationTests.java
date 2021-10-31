package it.jpanik.jgaming;

import it.jpanik.jgaming.dtos.ContactDto;
import it.jpanik.jgaming.services.ContactServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.mail.MessagingException;
import java.io.IOException;

@SpringBootTest
class JGamingApplicationTests {

	@Autowired
	private ContactServiceImpl contactServiceImpl;

	@Test
	public void testSendMail() throws MessagingException {
		ContactDto contactDto = new ContactDto();
		contactDto.setEmail("slin_marco96@live.it");
		contactDto.setObject("test");
		contactDto.setMessage("test");
		contactServiceImpl.sendGenericNotificationMail(contactDto);
	}

}
