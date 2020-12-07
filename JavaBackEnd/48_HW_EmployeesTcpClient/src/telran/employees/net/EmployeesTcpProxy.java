package telran.employees.net;

import static telran.employees.api.ApiConstants.ADD_EMPLOYEE;
import static telran.employees.api.ApiConstants.EXIT;
import static telran.employees.api.ApiConstants.GET_DEPARTMENT_AVG_SALARY_DISTRIBUTION;
import static telran.employees.api.ApiConstants.GET_EMPLOYEE;
import static telran.employees.api.ApiConstants.GET_EMPLOYEES_AGE;
import static telran.employees.api.ApiConstants.GET_EMPLOYEES_DEPARTMENT;
import static telran.employees.api.ApiConstants.GET_EMPLOYEES_SALARY;
import static telran.employees.api.ApiConstants.GET_EMPLOYEES_SALARY_INTERVAL;
import static telran.employees.api.ApiConstants.REMOVE_EMPLOYEE;
import static telran.employees.api.ApiConstants.UPDATE_EMPLOYEE;

import java.util.ArrayList;

import telran.employees.dto.DepartmentSalary;
import telran.employees.dto.Employee;
import telran.employees.dto.MinMaxSalaryEmployees;
import telran.employees.dto.ReturnCodes;
import telran.employees.services.interfaces.EmployeeService;
import telran.net.TcpClientJava;

public class EmployeesTcpProxy extends TcpClientJava implements EmployeeService {

	public EmployeesTcpProxy(String hostname, int port) {
		super(hostname, port);
	
	}

	@Override
	public ReturnCodes addEmployee(Employee empl) {
		
		return sendRequest(ADD_EMPLOYEE, empl);
	}

	@Override
	public ReturnCodes removeEmployee(long id) {
		
		return sendRequest(REMOVE_EMPLOYEE, id);
	}

	@Override
	public Employee updateEmployee(long id, Employee newEmployee) {
		
		return sendRequest(UPDATE_EMPLOYEE, newEmployee);
	}

	@Override
	public ArrayList<Employee> getEmployeesByAge(int ageFrom, int ageTo) {
		
		return sendRequest(GET_EMPLOYEES_AGE, new int[] {ageFrom, ageTo});
	}

	@Override
	public ArrayList<Employee> getEmployeesByDepartment(String department) {
		
		return sendRequest(GET_EMPLOYEES_DEPARTMENT, department);
	}

	@Override
	public ArrayList<Employee> getEmployeesBySalary(int salaryFrom, int salaryTo) {
		return sendRequest(GET_EMPLOYEES_SALARY, new int[] {salaryFrom, salaryTo});
	}

	@Override
	public Employee getEmployee(long id) {
		
		return sendRequest(GET_EMPLOYEE, id);
	}

	@Override
	public MinMaxSalaryEmployees[] getEmployeesBySalariesInterval(int interval) {
		
		return sendRequest(GET_EMPLOYEES_SALARY_INTERVAL, interval);
	}

	@Override
	public DepartmentSalary[] getDepartmentAvgSalaryDistribution() {
		
		return sendRequest(GET_DEPARTMENT_AVG_SALARY_DISTRIBUTION, null);
	}
	public ReturnCodes stopServer(String answer) {
		return sendRequest(EXIT, answer);
	}

}
