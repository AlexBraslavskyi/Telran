package telran.employees.services.interfaces;

import java.util.ArrayList;

import telran.employees.dto.DepartmentSalary;
import telran.employees.dto.Employee;
import telran.employees.dto.MinMaxSalaryEmployees;
import telran.employees.dto.ReturnCodes;

public interface EmployeeService {
ReturnCodes addEmployee(Employee empl);
ReturnCodes removeEmployee(long id);
Employee updateEmployee(long id, Employee newEmployee);
ArrayList<Employee> getEmployeesByAge(int ageFrom, int ageTo);
ArrayList<Employee> getEmployeesByDepartment(String department);
ArrayList<Employee> getEmployeesBySalary(int salaryFrom, int salaryTo);
Employee getEmployee(long id);
/**
 * interval - length of the ranges containing from minSalary to maxSalary values
 * @return array of the MinMaxSalaryEmployees objects sorted by minSalary value
 */
MinMaxSalaryEmployees[] getEmployeesBySalariesInterval(int interval); 
/**
 * 
 * @return array of the DepartmentSalary objects sorted by the descending order of the average salary values.
 */
DepartmentSalary[] getDepartmentAvgSalaryDistribution(); 

}
