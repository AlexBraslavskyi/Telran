package telran.security.accounting.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class AccountResponse extends AccountDto {
public AccountResponse(@NotEmpty String username, @Size(min = 8) String password, @NotNull String[] roles,
			long expirationTimestampSec) {
		super(username, password, roles);
		this.expirationTimestampSec = expirationTimestampSec;
	}

public long expirationTimestampSec;

@Override
public int hashCode() {
	int result = super.hashCode();
	return result;
}

@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (!super.equals(obj))
		return false;
	if (getClass() != obj.getClass())
		return false;
	AccountResponse other = (AccountResponse) obj;
	return Math.abs(expirationTimestampSec - other.expirationTimestampSec) < 60;
}


}
