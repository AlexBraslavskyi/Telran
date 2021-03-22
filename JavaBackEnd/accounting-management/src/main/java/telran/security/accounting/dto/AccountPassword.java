package telran.security.accounting.dto;
import javax.validation.constraints.*;
public class AccountPassword {
	@NotEmpty
	public String username;
	@Size(min=8)
	public String password;
	
}
