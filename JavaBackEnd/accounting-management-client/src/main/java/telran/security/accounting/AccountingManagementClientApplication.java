package telran.security.accounting;

import java.util.Base64;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;





@SpringBootApplication
public class AccountingManagementClientApplication {
	
static RestTemplate restTemplate = new RestTemplate();

	public static void main(String[] args) {
		SpringApplication.run(AccountingManagementClientApplication.class, args);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", getAuthToken("user","user"));
		HttpEntity<String> requestEntity = new HttpEntity<>(headers);
//		AccountResponse
		ResponseEntity<String> response = restTemplate.exchange("http://localhost:8080/accounts?username=Alex1", HttpMethod.GET,
				requestEntity, String.class);
		System.out.println(response.getBody());
	}
//AppEnv
	private static String getAuthToken(String username, String password) {
//	token Base64 encoding to ascii string username:password
		String rowText = username + ":" + password;
		String token = "Basic " + Base64.getEncoder().encodeToString(rowText.getBytes());
		return token;
	}
}

