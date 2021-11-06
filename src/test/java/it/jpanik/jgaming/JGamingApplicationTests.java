package it.jpanik.jgaming;

import it.jpanik.jgaming.dtos.ContactDto;
import it.jpanik.jgaming.dtos.UserDto;
import it.jpanik.jgaming.exceptions.ServiceException;
import it.jpanik.jgaming.services.contact.ContactServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.mail.MessagingException;

@SpringBootTest
class JGamingApplicationTests {

	@Autowired
	private ContactServiceImpl contactServiceImpl;

	@Test
	public void testSendMail() throws MessagingException, ServiceException {
		UserDto userDto = new UserDto();
		userDto.setEmail("benefe6164@cbdious.com");
		userDto.setUsername("Benefe6146 - TestUser");
		contactServiceImpl.sendRegistrationConfirmMail(userDto);
	}

}
