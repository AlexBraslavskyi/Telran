package telran.emploeeys.services.interfaces;

import telran.emploeeys.dto.Employee;
import telran.emploeeys.dto.ReturnCodes;

public interface EmployeeService {

	ReturnCodes addEmployee(Employee empl);
	ReturnCodes removeEmployee(long id);
	Employee updateEmployee(long id, Employee newEmployee);
	Iterable<Employee> getEmployeesByAge(int ageFrom, int ageTo);
	Iterable<Employee> getEmployeesByDepartment(String department);
	Iterable<Employee> getEmployeesBySalary(int salaryFrom, int salaryTo);
	Employee getEmployee(long id);
	}
