package telran.employees.server;

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
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import telran.employees.common.EmployeeService;
import telran.employees.common.dto.Employee;
import telran.net.common.Protocol;
import telran.net.common.ProtocolRequest;
import telran.net.common.ProtocolResponse;
import telran.net.common.ResponseCode;

public class EmployeesProtocolController implements Protocol {
	Map<String, Function<Serializable, ProtocolResponse>> functionsMap;
	EmployeeService service;

	public EmployeesProtocolController(EmployeeService service) {
		super();
		this.service = service;
		functionsMap = new HashMap<>();
		functionsMap.put(ADD_EMPLOYEE, this::addEmployee);
		functionsMap.put(UPDATE_EMPLOYEE, this::updateEmployee);
		functionsMap.put(REMOVE_EMPLOYEE, this::removeEmployee);
		functionsMap.put(GET_EMPLOYEE, this::getEmployee);
		functionsMap.put(GET_EMPLOYEE_BY_AGES, this::getEmployeesByAges);
		functionsMap.put(GET_EMPLOYEE_BY_DEPARTMENT, this::getEmployeesByDepartment);
		functionsMap.put(GET_EMPLOYEE_BY_SALARY, this::getEmployeesBySalary);
		functionsMap.put(GET_EMPLOYEE_BY_SALARY_INTERVAL, this::getEmployeesBySalariesInterval);
		functionsMap.put(GET_DEPARTMENT_AVG_SALARY_DISTRIBUTION, this::getDepartmentAvgSalaryDistribution);
	}

	@Override
	public ProtocolResponse getResponse(ProtocolRequest request) {
		return functionsMap.getOrDefault(request.requestType, r-> new ProtocolResponse(ResponseCode.WRONG_REQUEST, "WRONG_REQUEST Type"))
				.apply(request.requestData);
	}

	ProtocolResponse addEmployee(Serializable requestData) {
		try {
			return new ProtocolResponse(ResponseCode.OK, service.addEmployee((Employee) requestData));
		} catch (Exception e) {
			return new ProtocolResponse(ResponseCode.WRONG_REQUEST, e.getMessage());
		}
	}

	ProtocolResponse updateEmployee(Serializable requestData) {
		try {
			Employee newEmployee= (Employee) requestData;
			return  new ProtocolResponse(ResponseCode.OK,
					service.updateEmployee(newEmployee.getId(), newEmployee));
		} catch (Exception e) {
			return new ProtocolResponse(ResponseCode.WRONG_REQUEST, e.getMessage());
		}
	}

	ProtocolResponse removeEmployee(Serializable requestData) {
		try {
			return new ProtocolResponse(ResponseCode.OK, service.removeEmployee((Long) requestData));
		} catch (Exception e) {
			return new ProtocolResponse(ResponseCode.WRONG_REQUEST, e.getMessage());
		}
	}


	ProtocolResponse getEmployee(Serializable requestData) {
		try {
			return new ProtocolResponse(ResponseCode.OK, service.getEmployee((Long) requestData));
		} catch (Exception e) {
			return new ProtocolResponse(ResponseCode.WRONG_REQUEST, e.getMessage());
		}
	}

	ProtocolResponse getEmployeesByAges(Serializable requestData) {
		try {
			Serializable[] param = (Serializable[]) requestData;
			return new ProtocolResponse(ResponseCode.OK, service.getEmployeesByAge((int) param[0], (int) param[1]));
		} catch (Exception e) {
			return new ProtocolResponse(ResponseCode.WRONG_REQUEST, e.getMessage());
		}
	}
	ProtocolResponse getEmployeesByDepartment(Serializable requestData) {
		try {
			return new ProtocolResponse(ResponseCode.OK, service.getEmployeesByDepartment((String) requestData));
		} catch (Exception e) {
			return new ProtocolResponse(ResponseCode.WRONG_REQUEST, e.getMessage());
		}
	}
	ProtocolResponse getEmployeesBySalary(Serializable requestData) {
		try {
			Serializable[] param = (Serializable[]) requestData;			
			return new ProtocolResponse(ResponseCode.OK, service.getEmployeesBySalary((int) param[0], (int) param[1]));
		} catch (Exception e) {
			return new ProtocolResponse(ResponseCode.WRONG_REQUEST, e.getMessage());
		}
	}
	ProtocolResponse getEmployeesBySalariesInterval(Serializable requestData) {
		try {
			return new ProtocolResponse(ResponseCode.OK, service.getEmployeesBySalariesInterval ((int) requestData));
		} catch (Exception e) {
			return new ProtocolResponse(ResponseCode.WRONG_REQUEST, e.getMessage());
		}
	}
	ProtocolResponse getDepartmentAvgSalaryDistribution(Serializable requestData) {
		try {
			return new ProtocolResponse(ResponseCode.OK, service.getDepartmentAvgSalaryDistribution());
		} catch (Exception e) {
			return new ProtocolResponse(ResponseCode.WRONG_REQUEST, e.getMessage());
		}
	}
	
}
