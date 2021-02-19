package telran.logs.bugs;

import java.util.function.Consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import telran.logs.bugs.client.EmailProviderClient;
import telran.logs.bugs.dto.LogDto;

@SpringBootApplication
public class EmailNotifierAppl {
	@Autowired
	EmailProviderClient emailClient;
	static Logger LOG = LoggerFactory.getLogger(EmailNotifierAppl.class);
	@Value("${app-subject}")
	String subject;
	String employee;
	@Autowired
	JavaMailSender mailSender;

	public static void main(String[] args) {
		SpringApplication.run(EmailNotifierAppl.class, args);

	}

	@Bean
	Consumer<LogDto> getExceptionsConsumer() {
		return this::takeLogAndSendMail;
	}

	void takeLogAndSendMail(LogDto logDto) {
		String email = emailClient.getEmailByArtifact(logDto.artifact);
		employee = "programmer";
		if (email == null || email.equals("")) {
			employee = "Opened Bugs Assigner";
			email = emailClient.getAssignerMail();
		}
		if (email == null || email.equals("")) {
			LOG.error("Email to has received neither from logs-bugs-email-provider "
					+ "nor from logs-bugs-assigner-mail-provider!");
		} else {
			sendMail(logDto, email);
		}

	}

	private void sendMail(LogDto logDto, String email) {
		String text = "Hello, " + employee + "\n" + "Exception has been received\n" + "Date: " + logDto.dateTime + "\n"
				+ "Exception type: " + logDto.logType + "\n" + "Artifact: " + logDto.artifact + "\n" + "Explanation: "
				+ logDto.result + "\n";
		SimpleMailMessage message = new SimpleMailMessage();
		message.setSubject(subject);
		message.setTo(email);
		message.setText(text);
		mailSender.send(message);

	}

}
