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
EmployeeService service;
 Map<String, Function<Serializable, ResponseJava>> mapFunctions;


public EmployeesProtocol(EmployeeService service) {
	super();
	this.service = service;
	//TODO 
	if(mapFunctions == null) {
		mapFunctions = new HashMap<>();
		mapFunctions.put(ADD_EMPLOYEE, this::addEmployee);
		mapFunctions.put(GET_EMPLOYEE, this::getEmployee);
		mapFunctions.put(REMOVE_EMPLOYEE, this::removeEmployee);
		mapFunctions.put(UPDATE_EMPLOYEE, this::updateEmployee);
		mapFunctions.put(GET_EMPLOYEES_BY_AGE, this::getEmplByAge);
		mapFunctions.put(GET_EMPLOYEES_BY_DEPARTMENT, this::getEmplByDept);
		mapFunctions.put(GET_EMPLOYEES_BY_SALARY, this::getEmplBySalary);
		mapFunctions.put(GET_EMPLOYEES_BY_SALARY_INTERVAL, this::getEmplBySalaryInterval);
		mapFunctions.put(GET_DEPT_AVR_SALARY_DISTR, this::getDeptAvrSalaryDistr);
	}
}

@Override
public ResponseJava getResponse(RequestJava request) {

	return mapFunctions.getOrDefault(request.requestType, 
			r -> new ResponseJava(TcpResponseCode.WRONG_REQUEST, "Wrong request type")).apply(request.requestData);
}
	ResponseJava addEmployee(Serializable requestData) {
		try {
			Employee empl = (Employee) requestData;
			ReturnCodes result = service.addEmployee(empl);
			ResponseJava response = new ResponseJava(TcpResponseCode.OK, result);
			return response;
		} catch (Exception e) {
			return new ResponseJava(TcpResponseCode.WRONG_REQUEST, e.getMessage());
		}
	}
	
	ResponseJava getEmployee(Serializable requestData) {
		try {
			Long emplId = (Long) requestData;
			Employee result = service.getEmployee(emplId);
			ResponseJava response = new ResponseJava(TcpResponseCode.OK, result);
			return response;
		} catch (Exception e) {
			return new ResponseJava(TcpResponseCode.WRONG_REQUEST, e.getMessage());
		}
	}
	ResponseJava removeEmployee(Serializable requestData) {
		try {
			Long emplId = (Long) requestData;
			ReturnCodes result = service.removeEmployee(emplId);
			
			ResponseJava response = new ResponseJava(TcpResponseCode.OK, result);
			return response;
		} catch (Exception e) {
			return new ResponseJava(TcpResponseCode.WRONG_REQUEST, e.getMessage());
		}
	}
	ResponseJava updateEmployee(Serializable requestData) {
		try {
			Object []data= (Object[]) requestData;
			Employee result = service.updateEmployee((Long)data[0], (Employee)data[1]);
			
			ResponseJava response = new ResponseJava(TcpResponseCode.OK, result);
			return response;
		} catch (Exception e) {
			return new ResponseJava(TcpResponseCode.WRONG_REQUEST, e.getMessage());
		}
	}
	ResponseJava getEmplByAge(Serializable requestData) {
		try {
			int[] interval = (int[]) requestData;
			Iterable<Employee> result = service.getEmployeesByAge(interval[0], interval[1]);
			ResponseJava response = new ResponseJava(TcpResponseCode.OK, (Serializable) result);
			return response;
		} catch (Exception e) {
			return new ResponseJava(TcpResponseCode.WRONG_REQUEST, e.getMessage());
		}
	}
	ResponseJava getEmplBySalary(Serializable requestData) {
		try {
			int[] interval = (int[]) requestData;
			Iterable<Employee> result = service.getEmployeesBySalary(interval[0], interval[1]);
			ResponseJava response = new ResponseJava(TcpResponseCode.OK, (Serializable) result);
			return response;
		} catch (Exception e) {
			return new ResponseJava(TcpResponseCode.WRONG_REQUEST, e.getMessage());
		}
	}
	ResponseJava getEmplByDept(Serializable requestData) {
		try {
			String department = (String) requestData;
			Iterable<Employee> result = service.getEmployeesByDepartment(department);
			ResponseJava response = new ResponseJava(TcpResponseCode.OK, (Serializable) result);
			return response;
		} catch (Exception e) {
			return new ResponseJava(TcpResponseCode.WRONG_REQUEST, e.getMessage());
		}
	}
	ResponseJava getEmplBySalaryInterval(Serializable requestData) {
		try {
			int interval = (int) requestData;
			MinMaxSalaryEmployees []result = service.getEmployeesBySalariesInterval(interval);
			ResponseJava response = new ResponseJava(TcpResponseCode.OK, result);
			return response;
		} catch (Exception e) {
			return new ResponseJava(TcpResponseCode.WRONG_REQUEST, e.getMessage());
		}
	}
	ResponseJava getDeptAvrSalaryDistr(Serializable requestData) {
		try {
			DepartmentSalary []result = service.getDepartmentAvgSalaryDistribution();
			ResponseJava response = new ResponseJava(TcpResponseCode.OK, result);
			return response;
		} catch (Exception e) {
			return new ResponseJava(TcpResponseCode.WRONG_REQUEST, e.getMessage());
		}
	}
}
