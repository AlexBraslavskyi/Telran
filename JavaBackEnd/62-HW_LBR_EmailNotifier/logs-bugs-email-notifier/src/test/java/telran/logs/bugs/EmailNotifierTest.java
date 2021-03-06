package telran.logs.bugs;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.Date;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mail.MailSenderValidatorAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cloud.stream.binder.test.InputDestination;
import org.springframework.cloud.stream.binder.test.TestChannelBinderConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.messaging.support.GenericMessage;

import com.icegreen.greenmail.configuration.GreenMailConfiguration;
import com.icegreen.greenmail.junit5.GreenMailExtension;
import com.icegreen.greenmail.util.GreenMailUtil;
import com.icegreen.greenmail.util.ServerSetupTest;

import telran.logs.bugs.client.EmailProviderClient;
import telran.logs.bugs.dto.LogDto;
import telran.logs.bugs.dto.LogType;

@SpringBootTest
@Import({ TestChannelBinderConfiguration.class, MailSenderValidatorAutoConfiguration.class })
public class EmailNotifierTest {
	private static final String EMAIL = "moshe@gmail.com";
	private static final String ASSIGNER_EMAIL = "teamlead@gmail.com";

	@RegisterExtension
	GreenMailExtension greenMail = new GreenMailExtension(ServerSetupTest.SMTP)
			.withConfiguration(GreenMailConfiguration.aConfig().withUser("log", "logs-bugs"));

	@MockBean
	EmailProviderClient client;
	@Autowired
	InputDestination input;

	@Test
	void normalFlow() throws MessagingException {

		when(client.getEmailByArtifact(anyString())).thenReturn(EMAIL);
		testMethod("artifact", "programmer", EMAIL);

	}

	@Test
	void normalNotAssigned() throws MessagingException {

		when(client.getEmailByArtifact(anyString())).thenReturn(null);
		when(client.getAssignerMail()).thenReturn(ASSIGNER_EMAIL);
		testMethod("artifact_not_found", "Assigner", ASSIGNER_EMAIL);
	}

	private void testMethod(String artifact, String employee, String email) throws MessagingException {
		LogDto logException = new LogDto(new Date(), LogType.AUTHENTICATION_EXCEPTION, artifact, 0, "");
		input.send(new GenericMessage<LogDto>(logException));
		MimeMessage message = greenMail.getReceivedMessages()[0];
		assertEquals(email, message.getAllRecipients()[0].toString());
		assertEquals("exception", message.getSubject());
		assertTrue(GreenMailUtil.getBody(message).contains(employee));
	}

	@Test
	void errorNotAssigned() {

		when(client.getEmailByArtifact(anyString())).thenReturn(null);
		when(client.getAssignerMail()).thenReturn(null);
		LogDto logException = new LogDto(new Date(), LogType.AUTHENTICATION_EXCEPTION, "", 0, "");
		input.send(new GenericMessage<LogDto>(logException));
		MimeMessage[] messages = greenMail.getReceivedMessages();
		assertEquals(0, messages.length);

	}

}
