package telran.security.accounting.dto;

import java.util.Arrays;
import java.util.Objects;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class AccountResponse extends AccountDto {
public AccountResponse(@NotEmpty String username, @Size(min = 8) String password, @NotNull String[] roles,
			long exprationTimestampSec) {
		super(username, password, roles);
		this.exprationTimestampSec = exprationTimestampSec;
	}

public long exprationTimestampSec;

@Override
public int hashCode() {
	int result = super.hashCode();
	return result;
	
}

@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	
	return super.equals(obj);
}

@Override
public String toString() {
	return "AccountResponse [exprationTimestampSec=" + exprationTimestampSec + ", username=" + username + ", password="
			+ password + ", roles=" + Arrays.toString(roles) + "]";
}
public AccountResponse() {
}

}
