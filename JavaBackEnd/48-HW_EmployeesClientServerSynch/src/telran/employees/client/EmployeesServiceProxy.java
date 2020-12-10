package telran.employees.client;
import static telran.employees.common.EmployeesApiConstants.ADD_EMPLOYEE;
import static telran.employees.common.EmployeesApiConstants.GET_DEPARTMENT_AVG_SALARY_DISTRIBUTION;
import static telran.employees.common.EmployeesApiConstants.GET_EMPLOYEE;
import static telran.employees.common.EmployeesApiConstants.GET_EMPLOYEE_BY_AGES;
import static telran.employees.common.EmployeesApiConstants.GET_EMPLOYEE_BY_DEPARTMENT;
import static telran.employees.common.EmployeesApiConstants.GET_EMPLOYEE_BY_SALARY;
import static telran.employees.common.EmployeesApiConstants.GET_EMPLOYEE_BY_SALARY_INTERVAL;
import static telran.employees.common.EmployeesApiConstants.REMOVE_EMPLOYEE;
import static telran.employees.common.EmployeesApiConstants.UPDATE_EMPLOYEE;

import java.io.Serializable;
import java.util.ArrayList;

import telran.employees.common.EmployeeService;
import telran.employees.common.dto.DepartmentSalary;
import telran.employees.common.dto.Employee;
import telran.employees.common.dto.MinMaxSalaryEmployees;
import telran.employees.common.dto.ReturnCodes;
import telran.net.client.TcpClient;

public class EmployeesServiceProxy extends TcpClient implements EmployeeService {

	protected EmployeesServiceProxy(String hostname, int port) {
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
	public Employee updateEmployee(long id, Employee updatedEmployee) {
	return sendRequest(UPDATE_EMPLOYEE, updatedEmployee);
	}

	@Override
	public ArrayList<Employee> getEmployeesByAge(int ageFrom, int ageTo) {
		return sendRequest(GET_EMPLOYEE_BY_AGES, new Serializable[] {ageFrom, ageTo});

	}

	@Override
	public ArrayList<Employee> getEmployeesByDepartment(String department) {
		return sendRequest(GET_EMPLOYEE_BY_DEPARTMENT, department);
	}

	@Override
	public ArrayList<Employee> getEmployeesBySalary(int salaryFrom, int salaryTo) {
		return sendRequest(GET_EMPLOYEE_BY_SALARY, new Serializable[] {salaryFrom, salaryTo});
	}

	@Override
	public Employee getEmployee(long id) {
		return sendRequest(GET_EMPLOYEE, id);
	}


	@Override
	public MinMaxSalaryEmployees[] getEmployeesBySalariesInterval(int intervals) {
		return sendRequest(GET_EMPLOYEE_BY_SALARY_INTERVAL, intervals);
	}

	@Override
	public DepartmentSalary[] getDepartmentAvgSalaryDistribution() {
		return sendRequest(GET_DEPARTMENT_AVG_SALARY_DISTRIBUTION, "");
	}

}
