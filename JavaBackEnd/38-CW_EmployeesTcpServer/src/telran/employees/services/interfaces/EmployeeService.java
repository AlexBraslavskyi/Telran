package telran.employees.services.interfaces;

import telran.employees.dto.DepartmentSalary;
import telran.employees.dto.Employee;
import telran.employees.dto.MinMaxSalaryEmployees;
import telran.employees.dto.ReturnCodes;

public interface EmployeeService {

	ReturnCodes addEmployee(Employee empl);
	ReturnCodes removeEmployee(long id);
	Employee updateEmployee(long id, Employee newEmployee);
	Iterable<Employee> getEmployeesByAge(int ageFrom, int ageTo);
	Iterable<Employee> getEmployeesByDepartment(String department);
	Iterable<Employee> getEmployeesBySalary(int salaryFrom, int salaryTo);
	Employee getEmployee(long id);
	MinMaxSalaryEmployees[] getEmployeesBySalariesInterval(int interval);
	DepartmentSalary[] getDepartmentAvgSalaryDistribution();
	}
