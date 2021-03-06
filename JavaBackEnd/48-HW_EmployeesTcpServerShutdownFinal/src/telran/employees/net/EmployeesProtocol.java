package telran.employees.net;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.*;
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

	public EmployeesProtocol(EmployeeService service) {
	super();
	this.service = service;
	
}
	@Override
	public ResponseJava getResponse(RequestJava request) {
		
		try {
			Class<?> clazz = getClass();
			Method method = clazz.getDeclaredMethod(request.requestType, Serializable.class);
			method.setAccessible(true);
			return (ResponseJava) method.invoke(this, request.requestData);
		}catch(Exception e) {
			return new ResponseJava(TcpResponseCode.WRONG_REQUEST, "Wrong request type");
		}
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
					(Serializable)res);
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
					(Serializable)res);
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
					(Serializable)res);
			return response;
		} catch (Exception e) {
			return new ResponseJava(TcpResponseCode.WRONG_REQUEST, e.getMessage());
		}
	}
	ResponseJava getDepartmentsSalaries(Serializable requestData) {
		try {
			
			DepartmentSalary[] res = service.getDepartmentAvgSalaryDistribution();
			ResponseJava response = new ResponseJava(TcpResponseCode.OK,
					(Serializable)res);
			return response;
		} catch (Exception e) {
			return new ResponseJava(TcpResponseCode.WRONG_REQUEST, e.getMessage());
		}
	}
}
