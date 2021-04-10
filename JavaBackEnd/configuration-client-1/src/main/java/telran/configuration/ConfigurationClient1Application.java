package telran.configuration;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@SpringBootApplication
public class ConfigurationClient1Application {
	@Value("${app-config-common}")
String strCommon;
	@Value("${app-config}")
String str1;
	public static void main(String[] args) {
		SpringApplication.run(ConfigurationClient1Application.class, args);
		
	}
@PostConstruct
void displayprops() {
	System.out.printf("strCommon : %s; str1: %s", strCommon, str1);
}
}
