package telran.employees.net;

import java.io.Serializable;
import java.lang.reflect.Method;

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
			Class <?> clazz = getClass();
			Method method = clazz.getDeclaredMethod(request.requestType,Serializable.class);
			method.setAccessible(true);
	
		return (ResponseJava) method.invoke(this, request.requestData);
	} catch (Exception e) {
		return new ResponseJava(TcpResponseCode.WRONG_REQUEST, "Wrong request type");
		
	}

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
