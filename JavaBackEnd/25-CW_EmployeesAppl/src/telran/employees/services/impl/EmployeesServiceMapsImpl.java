package telran.employees.services.impl;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.NavigableMap;
import java.util.TreeMap;

import telran.employees.dto.Employee;
import telran.employees.dto.ReturnCodes;
import telran.employees.services.interfaces.EmployeeService;

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
		System.out.println(employeesDepartment.toString());
		return ReturnCodes.OK;
	}

	private void removeEmployeeDepartment(Employee empl) {
		employeesDepartment.remove(empl.getDepartment(),empl);
	
		if(employeesDepartment.size() == 0) {
			employeesDepartment = null;
		}
	}

	private void removeEmployeeSalary(Employee empl) {
		employeesSalary.remove(empl.getSalary(),empl);
		if(employeesSalary.size() == 0) {
			employeesSalary = null;
		}
	}

	private void removeEmployeeAge(Employee empl) {
		
		employeesAge.remove(empl.getBirthDate().getYear(),empl);
		if(employeesAge.size() == 0) {
			employeesAge = null;
		}
	}
		


	@Override
	public Employee updateEmployee(long id, Employee newEmployee) {
		removeEmployee(id);
		addEmployee(newEmployee);
		return null;
	}

	@Override
	public Iterable<Employee> getEmployeesByAge(int ageFrom, int ageTo) {
		NavigableMap<Integer, List<Employee>> submapEmpl = 
				employeesAge.subMap(getBirthYear(ageTo),true, getBirthYear(ageFrom), true);
		return  toColectionEmployees(submapEmpl.values());
	}

	private Iterable<Employee> toColectionEmployees(Collection<List<Employee>> values) {
	 ArrayList<Employee> empl = new ArrayList<>();
	Iterator<List<Employee>> it = values.iterator();
	while(it.hasNext()) {
	empl.addAll(it.next());
	}
		return empl;
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
