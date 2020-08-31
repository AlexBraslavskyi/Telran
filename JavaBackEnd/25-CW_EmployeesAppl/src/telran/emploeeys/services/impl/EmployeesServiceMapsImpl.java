package telran.emploeeys.services.impl;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.NavigableMap;
import java.util.TreeMap;

import telran.emploeeys.dto.Employee;
import telran.emploeeys.dto.ReturnCodes;
import telran.emploeeys.services.interfaces.EmployeeService;

public class EmployeesServiceMapsImpl implements EmployeeService {
HashMap<Long, Employee> employees = new HashMap<>();
TreeMap<Integer, List<Employee>>  employeesSalary = new TreeMap<>();
TreeMap<Integer, List<Employee>> employeesAge = new TreeMap<>();
HashMap<String, List<Employee>> employeesDepartment = new HashMap<>();


	@Override
	public ReturnCodes addEmployee(Employee empl) {
	Employee res = employees.putIfAbsent(empl.getId(), empl);
		if(res!=null) {
			return ReturnCodes.EMPLOYEE_ALREADY_EXIST;
		}
		addEmployeeSalary(empl);
		addEmployeeAge(empl);
		addEmployeeDepartment(empl);
		return ReturnCodes.OK;
	}

	private void addEmployeeDepartment(Employee empl) {
		String dept = empl.getDepartment();
		List <Employee> employeesList = employeesDepartment.getOrDefault(dept,new ArrayList<>());
		employeesList.add(empl);
		employeesDepartment.putIfAbsent(dept, employeesList);
	}

	private void addEmployeeAge(Employee empl) {
		int birthYear = empl.getBirthDate().getYear();
		List <Employee> employeesList = employeesAge.getOrDefault(birthYear, new ArrayList<>());
		employeesList.add(empl);
		employeesAge.putIfAbsent(birthYear, employeesList);
	}

	private void addEmployeeSalary(Employee empl) {
		int salary = empl.getSalary();
		List <Employee> employeesList = employeesSalary.getOrDefault(salary, new ArrayList<>());
		employeesList.add(empl);
		employeesSalary.putIfAbsent(salary, employeesList);
		
	}

	@Override
	public ReturnCodes removeEmployee(long id) {
		Employee empl = employees.remove(id);
		if(empl == null) {
			return ReturnCodes.EMPLOYEE_NOT_FOUND;
		}
		removeEmployeeAge(empl);
		removeEmployeeSalary(empl);
		removeEmployeeDepartment(empl);
		return ReturnCodes.OK;
	}

	private void removeEmployeeDepartment(Employee empl) {
		// TODO Auto-generated method stub
		
	}

	private void removeEmployeeSalary(Employee empl) {
		// TODO Auto-generated method stub
		
	}

	private void removeEmployeeAge(Employee empl) {
		
		//ToDo
		int birthYear = empl.getBirthDate().getYear();
		List <Employee> employeesList = employeesAge.getOrDefault(birthYear, new ArrayList<>());
		employeesList.add(empl);
		employeesAge.putIfAbsent(birthYear, employeesList);
	}
		


	@Override
	public Employee updateEmployee(long id, Employee newEmployee) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<Employee> getEmployeesByAge(int ageFrom, int ageTo) {
		NavigableMap<Integer, List<Employee>> submapEmpl = 
				employeesAge.subMap(getBirthYear(ageTo),true, getBirthYear(ageFrom), true);
		return  toColectionEmployees(submapEmpl.values());
	}

	private Iterable<Employee> toColectionEmployees(Collection<List<Employee>> values) {
		// TODO Auto-generated method stub   addAll
		return null;
	}

	private Integer getBirthYear(int ageTo) {
	
		return LocalDate.now().getYear() - ageTo; 
	}

	@Override
	public Iterable<Employee> getEmployeesByDepartment(String department) {
		
		return employeesDepartment.getOrDefault(department, new LinkedList<Employee>());
	}

	@Override
	public Iterable<Employee> getEmployeesBySalary(int salaryFrom, int salaryTo) {
		NavigableMap<Integer, List<Employee>> submapEmpl = 
				employeesSalary.subMap(salaryFrom,true, salaryTo, true);
		return toColectionEmployees(submapEmpl.values());
	}

	@Override
	public Employee getEmployee(long id) {

		return employees.get(id);
	}

}
