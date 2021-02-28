package telran.logs.bugs;
import telran.logs.bugs.dto.EmailBugsCount;

public class EmailBugCountTest implements EmailBugsCount {
	String email;
	long count;
			public EmailBugCountTest() {
		
	}


			public EmailBugCountTest(String email, long count) {
		this.email = email;
		this.count = count;
	}


			@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (count ^ (count >>> 32));
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EmailBugCountTest other = (EmailBugCountTest) obj;
		if (count != other.count)
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		return true;
	}


			@Override
	public String toString() {
		return "EmailBugCountTest [email=" + email + ", count=" + count + "]";
	}


			@Override
			public String getEmail() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public long getCount() {
				// TODO Auto-generated method stub
				return 0;
			}
			
			
		}
