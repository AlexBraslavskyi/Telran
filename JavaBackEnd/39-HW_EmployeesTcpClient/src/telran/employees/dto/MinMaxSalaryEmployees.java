package telran.employees.dto;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

public class MinMaxSalaryEmployees implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int minSalary;
	int maxSalary;
	List<Employee> employees;
	public MinMaxSalaryEmployees(int minSalary, int maxSalary, List<Employee> employees) {
		super();
		this.minSalary = minSalary;
		this.maxSalary = maxSalary;
		this.employees = employees;
	}
	public int getMinSalary() {
		return minSalary;
	}
	public int getMaxSalary() {
		return maxSalary;
	}
	@Override
	public String toString() {
		return "MinMaxSalaryEmployees [minSalary=" + minSalary + ", maxSalary=" + maxSalary + ", employees=" + employees
				+ "]";
	}
	public List<Employee> getEmployees() {
		return employees.stream().collect(Collectors.toList());
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((employees == null) ? 0 : employees.hashCode());
		result = prime * result + maxSalary;
		result = prime * result + minSalary;
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
		MinMaxSalaryEmployees other = (MinMaxSalaryEmployees) obj;
		if (employees == null) {
			if (other.employees != null)
				return false;
		} else if (!employees.equals(other.employees))
			return false;
		if (maxSalary != other.maxSalary)
			return false;
		if (minSalary != other.minSalary)
			return false;
		return true;
	}
	
	
}
