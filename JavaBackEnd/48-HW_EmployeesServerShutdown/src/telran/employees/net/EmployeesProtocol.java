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

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import telran.employees.dto.DepartmentSalary;
import telran.employees.dto.Employee;
import telran.employees.dto.MinMaxSalaryEmployees;
import telran.employees.dto.ReturnCodes;
import telran.employees.services.interfaces.EmployeeService;
import telran.net.RequestJava;
import telran.net.ResponseJava;
import telran.net.TcpResponseCode;
import telran.net.server.ProtocolJava;
public class EmployeesProtocol implements ProtocolJava {
	 Map<String, Function<Serializable, ResponseJava>> mapFunctions;
	
EmployeeService service;

	public EmployeesProtocol(EmployeeService service) {
	super();
	this.service = service;
	if (mapFunctions == null) {
		mapFunctions = new HashMap<>();
		mapFunctions.put(ADD_EMPLOYEE, this::addEmployee);
		mapFunctions.put(GET_EMPLOYEE, this::getEmployee);
		mapFunctions.put(REMOVE_EMPLOYEE, this::removeEmployee);
		mapFunctions.put(UPDATE_EMPLOYEE, this::updateEmployee);
		mapFunctions.put(GET_EMPLOYEES_AGE, this::getEmployeesAge);
		mapFunctions.put(GET_EMPLOYEES_SALARY, this::getEmployeesSalary);
		mapFunctions.put(GET_EMPLOYEES_DEPARTMENT, this::getEmployeesDepartment);
		mapFunctions.put(GET_EMPLOYEES_SALARY_INTERVAL, this::getEmployeesSalariesInterval);
		mapFunctions.put(GET_DEPARTMENT_AVG_SALARY_DISTRIBUTION, this::getDepartmentsSalaries);
		mapFunctions.put(EXIT, this::stopServer);
	}
}
	@Override
	public ResponseJava getResponse(RequestJava request) {
		
		return mapFunctions.getOrDefault(request.requestType,
				r -> new ResponseJava(TcpResponseCode.WRONG_REQUEST, "Wrong request type"))
				.apply(request.requestData);
	}
	ResponseJava addEmployee(Serializable requestData) {
		try {
			Employee empl = (Employee) requestData;
			ReturnCodes res = service.addEmployee(empl);
			ResponseJava response = new ResponseJava(TcpResponseCode.OK,
					res);
			return response;
		} catch (Exception e) {
			return new ResponseJava(TcpResponseCode.WRONG_REQUEST, e.getMessage());
		}
	}
	ResponseJava getEmployee(Serializable requestData) {
		try {
			long emplId = (long) requestData;
			Employee res = service.getEmployee(emplId);
			ResponseJava response = new ResponseJava(TcpResponseCode.OK,
					res);
			return response;
		} catch (Exception e) {
			return new ResponseJava(TcpResponseCode.WRONG_REQUEST, e.getMessage());
		}
	}
	ResponseJava getEmployeesAge(Serializable requestData) {
		try {
			int ages[] = (int[]) requestData;
			Iterable<Employee> res = service.getEmployeesByAge(ages[0], ages[1]);
			ResponseJava response = new ResponseJava(TcpResponseCode.OK,
					(Serializable)res);
			return response;
		} catch (Exception e) {
			return new ResponseJava(TcpResponseCode.WRONG_REQUEST, e.getMessage());
		}
	}
	ResponseJava getEmployeesSalary(Serializable requestData) {
		try {
			int salaries[] = (int[]) requestData;
			Iterable<Employee> res = service.getEmployeesBySalary(salaries[0], salaries[1]);
			ResponseJava response = new ResponseJava(TcpResponseCode.OK,
					(Serializable)res);
			return response;
		} catch (Exception e) {
			return new ResponseJava(TcpResponseCode.WRONG_REQUEST, e.getMessage());
		}
	}
	ResponseJava getEmployeesDepartment(Serializable requestData) {
		try {
			String department = (String) requestData;
			Iterable<Employee> res = service.getEmployeesByDepartment(department);
			ResponseJava response = new ResponseJava(TcpResponseCode.OK,
					(Serializable)res);
			return response;
		} catch (Exception e) {
			return new ResponseJava(TcpResponseCode.WRONG_REQUEST, e.getMessage());
		}
	}
	ResponseJava removeEmployee(Serializable requestData) {
		try {
			long id = (long) requestData;
			ReturnCodes res = service.removeEmployee(id);
			ResponseJava response = new ResponseJava(TcpResponseCode.OK,
					res);
			return response;
		} catch (Exception e) {
			return new ResponseJava(TcpResponseCode.WRONG_REQUEST, e.getMessage());
		}
	}
	ResponseJava updateEmployee(Serializable requestData) {
		try {
			Employee newEmployee= (Employee) requestData;
			Employee res = service.updateEmployee(newEmployee.getId(), newEmployee);
			ResponseJava response = new ResponseJava(TcpResponseCode.OK,
					res);
			return response;
		} catch (Exception e) {
			return new ResponseJava(TcpResponseCode.WRONG_REQUEST, e.getMessage());
		}
	}	
	ResponseJava getEmployeesSalariesInterval(Serializable requestData) {
		try {
			int interval = (int) requestData;
			MinMaxSalaryEmployees[] res = service.getEmployeesBySalariesInterval(interval);
			ResponseJava response = new ResponseJava(TcpResponseCode.OK,
					res);
			return response;
		} catch (Exception e) {
			return new ResponseJava(TcpResponseCode.WRONG_REQUEST, e.getMessage());
		}
	}
	ResponseJava getDepartmentsSalaries(Serializable requestData) {
		try {
			
			DepartmentSalary[] res = service.getDepartmentAvgSalaryDistribution();
			ResponseJava response = new ResponseJava(TcpResponseCode.OK,
					res);
			return response;
		} catch (Exception e) {
			return new ResponseJava(TcpResponseCode.WRONG_REQUEST, e.getMessage());
		}
	}
	ResponseJava stopServer(Serializable requestData) {
		try {
			ResponseJava response = new ResponseJava(TcpResponseCode.SERVER_NOT_AVAILABLE,
					requestData);
			return response;
		} catch (Exception e) {
			return new ResponseJava(TcpResponseCode.WRONG_REQUEST, e.getMessage());
		}
	}
}
