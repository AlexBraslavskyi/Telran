package telran.person.dto;

import java.time.LocalDate;

public class Employee extends Person {
	String company;
	String title;
	int salary;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Employee() {

	}

	public Employee(int id, Address adress, String name, LocalDate birthDate, String company, String title,
			int salary) {
		super(id, adress, name, birthDate);
		this.company = company;
		this.title = title;
		this.salary = salary;
	}

	public String getCompany() {
		return company;
	}

	public String getTitle() {
		return title;
	}

	public int getSalary() {
		return salary;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	@Override
	public String toString() {
		return "Employee [company=" + company + ", title=" + title + ", salary=" + salary + ", id=" + id + ", adress="
				+ adress + ", name=" + name + ", birthDate=" + birthDate + "]" + "\n";
	}

}
