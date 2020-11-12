
import java.io.Serializable;

import telran.validation.constraints.Email;
import telran.validation.constraints.IpV4;
import telran.validation.constraints.Max;
import telran.validation.constraints.Min;
import telran.validation.constraints.NotEmpty;
import telran.validation.constraints.NotNull;
import telran.validation.constraints.Past;
import telran.validation.constraints.Valid;

public class Employee implements Serializable {
//notEmpty (String), future,past date, email, ip(4 cw hw)

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int MIN_SALARY = 10000;
	private static final int MAX_SALARY = 30000;
	private static final String FUTURE_DATE = "2020-01-01";
	private static final String PAST_DATE = "2020-01-01";
	private long id;
	@NotNull(message = "name can't be null")
	private String name;
	@NotNull(message = "birthdate can't be null")
	@Past(message = "date must by before " + PAST_DATE, value = PAST_DATE)
//	@Future(message = "date must by after " + FUTURE_DATE, value = FUTURE_DATE)
	private String birthDate;
	@NotNull(message = "department can't be null")
	private String department;
	@Min(value = MIN_SALARY, message = "salary can't be less than " + MIN_SALARY)
	@Max(value = MAX_SALARY, message = "salary can't be greater than " + MAX_SALARY)
	private int salary;
	@Valid
	private Address address;
	@NotEmpty(message = "Email field can't be empty")
	@Email(message = "Email must be in correct format")
	private String email;
	@NotNull(message = "department can't be null")
	@IpV4(message = "IP address must be in correct format (IpV4)")
	private String ipv4;

	public Employee() {

	}

	public Employee(long id, String name, String birthDate, String department, int salary, String email, String ipv4,
			Address address) {
		super();
		this.id = id;
		this.name = name;
		this.birthDate = birthDate;
		this.department = department;
		this.salary = salary;
		this.email = email;
		this.ipv4 = ipv4;
		this.address = address;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", birthDate=" + birthDate + ", department=" + department
				+ ", salary=" + salary + ", address=" + address + ", email=" + email + ", ipv4=" + ipv4 + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
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
		Employee other = (Employee) obj;
		if (id != other.id)
			return false;
		return true;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getIpv4() {
		return ipv4;
	}

	public void setIpv4(String ipv4) {
		this.ipv4 = ipv4;
	}

}
