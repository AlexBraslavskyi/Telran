package telran.employees.server;

import static telran.employees.common.EmployeesApiConstants.*;

import java.io.*;
import java.util.*;
import java.util.function.Function;

import telran.employees.common.EmployeeService;
import telran.employees.common.dto.*;
import telran.net.common.*;

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
		functionsMap.put(SIZE, this::size);
		functionsMap.put(GET_EMPLOYEE, this::getEmployee);
		functionsMap.put(GET_ALL_EMPLOYEE, this::getAllEmployee);
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
			return new ProtocolResponse(ResponseCode.OK, service.updateEmployee((Employee) requestData));
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

	ProtocolResponse size(Serializable requestData) {
		try {
			return new ProtocolResponse(ResponseCode.OK, service.size());
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

	ProtocolResponse getAllEmployee(Serializable requestData) {
		try {
			return new ProtocolResponse(ResponseCode.OK, (Serializable) (service.getAllEmployees()));
		} catch (Exception e) {
			return new ProtocolResponse(ResponseCode.WRONG_REQUEST, e.getMessage());
		}
	}

	ProtocolResponse getEmployeesByAges(Serializable requestData) {
		try {
			Serializable[] param = (Serializable[]) requestData;
			return new ProtocolResponse(ResponseCode.OK, service.getEmployeesByAges((int) param[0], (int) param[1]));
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
