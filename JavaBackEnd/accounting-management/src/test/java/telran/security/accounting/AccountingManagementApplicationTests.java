package telran.security.accounting;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static telran.security.accounting.api.ApiConstants.ADD_ACCOUNT;
import static telran.security.accounting.api.ApiConstants.ADD_ROLE;
import static telran.security.accounting.api.ApiConstants.GET_ACCOUNT;
import static telran.security.accounting.api.ApiConstants.REMOVE_ROLE;
import static telran.security.accounting.api.ApiConstants.UPDATE_PASSWORD;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import telran.security.accounting.dto.AccountPassword;
import telran.security.accounting.dto.AccountRequest;
import telran.security.accounting.dto.AccountResponse;
import telran.security.accounting.dto.AccountRole;

@SpringBootTest
@AutoConfigureWebTestClient
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
//@TestPropertySource()
class AccountingManagementApplicationTests {
private static final  String USER_MOSHE = "Moshe";
private static final  String ASTERICS8 = "*".repeat(8);
private static final  String PASSWORD_MOSHE = "12345.com";
private static final  String EXPECTED_PASSWORD_MOSHE = "{noop}12345.com";
private static final  String[] ROLES_MOSHE = {"USER","ADMIN"};
private static final long EXPIRATION_MOSHE = 1;
@Value("${app-security-enable:true}")
	boolean securityEnable;
@Autowired
WebTestClient testClient;
AccountRequest accountRequestMoshe = 
new AccountRequest(USER_MOSHE, PASSWORD_MOSHE, ROLES_MOSHE, EXPIRATION_MOSHE);
AccountResponse expectedMoshe = new AccountResponse
(USER_MOSHE, "{noop}" + PASSWORD_MOSHE, ROLES_MOSHE,
		System.currentTimeMillis() / 1000 + EXPIRATION_MOSHE * 60); 
AccountResponse expectedResponseRoleRemove;	
	
	@Test
	@Order(1)
	void addAccount() {
		testClient.post().uri(ADD_ACCOUNT)
		.bodyValue(accountRequestMoshe)
		.exchange().expectStatus().isOk().expectBody(AccountResponse.class).isEqualTo(expectedMoshe);
		
	}
	@Test
	@Order(2)
	void getAccount() {
		AccountResponse response = getMosheAccount();
		assertEquals(expectedMoshe,response);
		assertEquals(EXPECTED_PASSWORD_MOSHE, response.password);
		
	}
	private AccountResponse getMosheAccount() {
		return testClient.get().uri(GET_ACCOUNT + "?username=Moshe")
		.exchange().expectStatus().isOk().returnResult(AccountResponse.class).getResponseBody().blockFirst();
	}
	@Test
	@Order(4)
	void updatePassword() {
		AccountPassword accountPassword = getAccountPassword(PASSWORD_MOSHE + "new");
		AccountResponse response = testClient.put().uri(UPDATE_PASSWORD)
		.bodyValue(accountPassword)
		.exchange().expectStatus().isOk().returnResult(AccountResponse.class).getResponseBody().blockFirst();
		AccountResponse responseUpdated = getMosheAccount();
		assertEquals(ASTERICS8,response.password);
		assertEquals("{noop}"+PASSWORD_MOSHE + "new",responseUpdated.password);
		
		
	}
	private AccountPassword getAccountPassword(String password) {
		AccountPassword accountPassword = new AccountPassword();
		accountPassword.password = password;
		accountPassword.username = USER_MOSHE;
		return accountPassword;
	}
	@Test
	@Order(3)
	void updateSamePassword() {
		//FIXME should be updated after exception handling to isBadRequest()
		
		AccountPassword accountPassword = getAccountPassword(PASSWORD_MOSHE);
		testClient.put().uri(UPDATE_PASSWORD)
		.bodyValue(accountPassword)
		.exchange().expectStatus().is5xxServerError();
	}
	@Test
	@Order(5)
	void addRole() {
		AccountRole accountRole = getAccountRole("STAT");
		expectedResponseRoleRemove = testClient.put().uri(ADD_ROLE)
				.bodyValue(accountRole)
				.exchange().expectStatus().isOk().returnResult(AccountResponse.class)
				.getResponseBody().blockFirst();
		assertEquals(expectedResponseRoleRemove, expectedMoshe);
		assertEquals(ASTERICS8, expectedResponseRoleRemove.password);
		AccountResponse response = getMosheAccount();
		String expectedRoles[] = {"USER","ADMIN","STAT"};
		assertArrayEquals(expectedRoles, response.roles);
	}
	@Test
	@Order(6)
	void removeRole() {
		AccountRole accountRole = getAccountRole("STAT");
		AccountResponse response = testClient.put().uri(REMOVE_ROLE)
				.bodyValue(accountRole)
				.exchange().expectStatus().isOk().returnResult(AccountResponse.class)
				.getResponseBody().blockFirst();
		
		assertEquals(ASTERICS8, response.password);
		response = getMosheAccount();
		
		assertEquals(expectedMoshe, response);
	}
	private AccountRole getAccountRole(String role) {
		AccountRole res = new AccountRole();
		res.username = USER_MOSHE;
		res.role = role;
		return res;
	}
	

}
