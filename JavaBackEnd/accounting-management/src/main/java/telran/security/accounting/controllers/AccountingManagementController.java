package telran.security.accounting.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import static telran.security.accounting.api.ApiConstants.*;

import telran.security.accounting.dto.*;
import telran.security.accounting.service.AccountingManagement;

@RestController
public class AccountingManagementController {
@Autowired
AccountingManagement accountingService;
@GetMapping(value = GET_ACCOUNT)
AccountResponse getAccount(@RequestParam(name = USERNAME_PARAM) String username) {
	return accountingService.getAccount(username);
}
@PostMapping(value = ADD_ACCOUNT)
AccountResponse addAccount(@RequestBody AccountRequest account) {
	return accountingService.addAccount(account);
}
@DeleteMapping(value = REMOVE_ACCOUNT)
void deleteAccount(@RequestParam(name = USERNAME_PARAM)String username) {
	accountingService.deleteAccount(username);
}
@PutMapping(value = ADD_ROLE)
AccountResponse addRole(@RequestBody AccountRole accountRole) {
	return accountingService.addRole(accountRole.username, accountRole.role);
}
@PutMapping(value = REMOVE_ROLE)
AccountResponse removeRole(@RequestBody AccountRole accountRole) {
	return accountingService.removeRole(accountRole.username, accountRole.role);
}
@PutMapping(value = UPDATE_PASSWORD)
AccountResponse updatePassword(@RequestBody AccountPassword accountPassword) {
	return accountingService.updatePassword(accountPassword.username,
			accountPassword.password);
}
}
