package telran.employees.services.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.IntSummaryStatistics;
import java.util.LinkedList;
import java.util.List;
import java.util.NavigableMap;
import java.util.TreeMap;
import java.util.stream.Stream;

import telran.employees.dto.DepartmentSalary;
import telran.employees.dto.Employee;
import telran.employees.dto.MinMaxSalaryEmployees;
import telran.employees.dto.ReturnCodes;
import telran.employees.services.interfaces.EmployeeService;

public class EmployeesServiceMapsImpl implements EmployeeService {
	HashMap<Long, Employee> employees = new HashMap<>();
	TreeMap<Integer, List<Employee>> employeesSalary = new TreeMap<>();
	TreeMap<Integer, List<Employee>> employeesAge = new TreeMap<>();
	HashMap<String, List<Employee>> employeesDepartment = new HashMap<>();

	@Override
	public ReturnCodes addEmployee(Employee empl) {
		Employee res = employees.putIfAbsent(empl.getId(), empl);
		if (res != null) {
			return ReturnCodes.EMPLOYEE_ALREADY_EXIST;
		}
		addEmployeeSalary(empl);
		addEmployeeAge(empl);
		addEmployeeDepartment(empl);
		return ReturnCodes.OK;
	}

	private void addEmployeeDepartment(Employee empl) {
		String dept = empl.getDepartment();
		employeesDepartment.computeIfAbsent(dept, k -> new ArrayList<>()).add(empl);
	}

	private void addEmployeeAge(Employee empl) {
		int birthYear = empl.getBirthDate().getYear();
		employeesAge.computeIfAbsent(birthYear, k -> new ArrayList<>()).add(empl);
	}

	private void addEmployeeSalary(Employee empl) {
		int salary = empl.getSalary();
		employeesSalary.computeIfAbsent(salary, k -> new ArrayList<>()).add(empl);
	}

	@Override
	public Iterable<Employee> getEmployeesByAge(int ageFrom, int ageTo) {
		NavigableMap<Integer, List<Employee>> submapEmpl = employeesAge.subMap(getBirthYear(ageTo), true,
				getBirthYear(ageFrom), true);
		return toColectionEmployees(submapEmpl.values());
	}

	private Integer getBirthYear(int age) {

		return LocalDate.now().getYear() - age;
	}

	@Override
	public Iterable<Employee> getEmployeesByDepartment(String department) {

		return Collections.unmodifiableList(employeesDepartment.getOrDefault(department, new LinkedList<Employee>()));
	}

	@Override
	public Iterable<Employee> getEmployeesBySalary(int salaryFrom, int salaryTo) {
		NavigableMap<Integer, List<Employee>> submapEmpl = employeesSalary.subMap(salaryFrom, true, salaryTo, true);
		return toColectionEmployees(submapEmpl.values());
	}

	@Override
	public Employee getEmployee(long id) {
		return employees.get(id);
	}

	@Override
	public Employee updateEmployee(long id, Employee newEmployee) {
		if (removeEmployee(id) == ReturnCodes.OK) {
			addEmployee(newEmployee);
			return newEmployee;
		}
		return null;
	}

	@Override
	public ReturnCodes removeEmployee(long id) {
		Employee empl = employees.remove(id);
		if (empl == null) {
			return ReturnCodes.EMPLOYEE_NOT_FOUND;
		}
		removeEmployeeAge(empl);
		removeEmployeeSalary(empl);
		removeEmployeeDepartment(empl);
		return ReturnCodes.OK;
	}

	private void removeEmployeeDepartment(Employee empl) {
		String dept = empl.getDepartment();
		List<Employee> employeesList = employeesDepartment.get(dept);
		if (employeesList.size() == 1) {
			employeesDepartment.remove(dept);
		}
			employeesList.remove(empl);
	}
	private void removeEmployeeSalary(Employee empl) {
		Integer salary = empl.getSalary();
		List<Employee> employeesList = employeesSalary.get(salary);
		if (employeesList.size() == 1) {
			employeesSalary.remove(salary);
		}
			employeesList.remove(empl);
		
	}

	private void removeEmployeeAge(Employee empl) {
		Integer birtYear = empl.getBirthDate().getYear();
		List<Employee> employeesList = employeesAge.get(birtYear);
		if (employeesList.size() == 1) {
			employeesAge.remove(birtYear);	
		} 
		employeesList.remove(empl);
	}

	private Iterable<Employee> toColectionEmployees(Collection<List<Employee>> values) {
		ArrayList<Employee> empl = new ArrayList<>();
		values.forEach(empl::addAll);
		return empl;
	}

	@Override
	public MinMaxSalaryEmployees[] getEmployeesBySalariesInterval(int interval) {
		ArrayList<MinMaxSalaryEmployees> newEmpl = new ArrayList<>();
		int minSalary = employeesSalary.firstKey();
		int maxSalary =employeesSalary.lastKey();
		while (minSalary < maxSalary) {
			List<Employee> emplInt = new ArrayList<>();
				Stream.of((List<Employee>)getEmployeesBySalary(minSalary, minSalary+interval))
				.forEach(x->emplInt.addAll(x));
			newEmpl.add(new MinMaxSalaryEmployees(minSalary, minSalary + interval, emplInt));
			minSalary += interval;
		}
		return newEmpl.toArray(MinMaxSalaryEmployees[]::new);
	}

	@Override
	public DepartmentSalary[] getDepartmentAvgSalaryDistribution() {
		List<DepartmentSalary> Deptlist = new ArrayList<>();
		employeesDepartment.forEach((k, v) -> {
			IntSummaryStatistics stats = v.stream().mapToInt(Employee::getSalary).summaryStatistics();
			Deptlist.add(new DepartmentSalary(k, stats.getAverage()));
		});
		Arrays.sort(Deptlist.toArray(new DepartmentSalary[0]),
				(s1, s2) -> Double.compare(s2.getAvgSalary(), s1.getAvgSalary()));

		return Deptlist.toArray(new DepartmentSalary[0]);
	}

}
