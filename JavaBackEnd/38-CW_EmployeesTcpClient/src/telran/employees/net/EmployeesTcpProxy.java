package telran.employees.net;

import static telran.employees.api.ApiConstants.ADD_EMPLOYEE;
import static telran.employees.api.ApiConstants.GET_DEPT_AVR_SALARY_DISTR;
import static telran.employees.api.ApiConstants.GET_EMPLOYEE;
import static telran.employees.api.ApiConstants.GET_EMPLOYEES_BY_AGE;
import static telran.employees.api.ApiConstants.GET_EMPLOYEES_BY_DEPARTMENT;
import static telran.employees.api.ApiConstants.GET_EMPLOYEES_BY_SALARY;
import static telran.employees.api.ApiConstants.GET_EMPLOYEES_BY_SALARY_INTERVAL;
import static telran.employees.api.ApiConstants.REMOVE_EMPLOYEE;
import static telran.employees.api.ApiConstants.UPDATE_EMPLOYEE;

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
	public Employee getEmployee(long id) {
	
		return sendRequest(GET_EMPLOYEE, id);
	}

	@Override
	public ReturnCodes removeEmployee(long id) {
		return sendRequest(REMOVE_EMPLOYEE, id);
	}

	@Override
	public Employee updateEmployee(long id, Employee newEmployee) {
		Object []data = {id,newEmployee};
		return sendRequest(UPDATE_EMPLOYEE, data);
	}

	@Override
	public Iterable<Employee> getEmployeesByAge(int ageFrom, int ageTo) {
		int []interval = {ageFrom,ageTo};
		return sendRequest(GET_EMPLOYEES_BY_AGE, interval);
	}

	@Override
	public Iterable<Employee> getEmployeesByDepartment(String department) {
		return sendRequest(GET_EMPLOYEES_BY_DEPARTMENT, department);
	}

	@Override
	public Iterable<Employee> getEmployeesBySalary(int salaryFrom, int salaryTo) {
		
		int []interval = {salaryFrom,salaryTo};
		return sendRequest(GET_EMPLOYEES_BY_SALARY, interval);
	}



	@Override
	public MinMaxSalaryEmployees[] getEmployeesBySalariesInterval(int interval) {
		return sendRequest(GET_EMPLOYEES_BY_SALARY_INTERVAL, interval);
	}

	@Override
	public DepartmentSalary[] getDepartmentAvgSalaryDistribution() {
		return sendRequest(GET_DEPT_AVR_SALARY_DISTR,null);
	}

}
