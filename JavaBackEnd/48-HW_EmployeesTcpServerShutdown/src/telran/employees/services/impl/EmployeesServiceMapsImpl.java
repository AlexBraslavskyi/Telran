package telran.employees.services.impl;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.NavigableMap;
import java.util.TreeMap;
import java.util.stream.Collectors;

import telran.employees.dto.DepartmentSalary;
import telran.employees.dto.Employee;
import telran.employees.dto.MinMaxSalaryEmployees;
import telran.employees.dto.ReturnCodes;
import telran.employees.services.interfaces.EmployeeService;

public class EmployeesServiceMapsImpl implements EmployeeService {
HashMap<Long,Employee> employees = new HashMap<>();
TreeMap<Integer, List<Employee>> employeesSalary = new TreeMap<>(); //key - salary value, value - list of Employees receiving the salary
TreeMap<Integer, List<Employee>> employeesAge = new TreeMap<>(); //key - birth year, value - list of Employee born at the birth year
HashMap<String, List<Employee>> employeesDepartment = new HashMap<>(); //key -department, value - list of Employees working at the department
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
		String department = empl.getDepartment();
		employeesDepartment.computeIfAbsent(department, k-> new ArrayList<>()).add(empl);
		
	}

	private void addEmployeeAge(Employee empl) {
		int birthYear = empl.getBirthDate().getYear();
		employeesAge.computeIfAbsent(birthYear, (k)-> new ArrayList<>()).add(empl);
		
		
	}

	private void addEmployeeSalary(Employee empl) {
		int salary = empl.getSalary();
		employeesSalary.computeIfAbsent(salary, (k) -> new ArrayList<>()).add(empl);
		
		
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
		String department = empl.getDepartment();
		List<Employee> list = employeesDepartment.get(department);
		list.remove(empl);
		if (list.isEmpty()) {
			employeesDepartment.remove(department);
		}
		
	}

	private void removeEmployeeSalary(Employee empl) {
		int salary = empl.getSalary();
		List<Employee> list = employeesSalary.get(salary);
		list.remove(empl);
		if (list.isEmpty()) {
			employeesSalary.remove(salary);
		}
		
		
	}

	private void removeEmployeeAge(Employee empl) {
		int year = empl.getBirthDate().getYear();
		List<Employee> list = employeesAge.get(year);
		list.remove(empl);
		if (list.isEmpty()) {
			employeesAge.remove(year);
		}
		
	}

	@Override
	public Employee updateEmployee(long id, Employee newEmployee) {
	Employee employee = getEmployee(id);
	if (employee == null) {
		return null;
	}
	removeEmployee(id);
	addEmployee(newEmployee);
		return employee;
	}

	@Override
	public Iterable<Employee> getEmployeesByAge(int ageFrom, int ageTo) {
	NavigableMap<Integer, List<Employee>> employeesSubmap =
			employeesAge.subMap(getBirthYear(ageTo), true, getBirthYear(ageFrom), true);
		return toCollectionEmployees(employeesSubmap.values());
	}

	private Iterable<Employee> toCollectionEmployees(Collection<List<Employee>> listsEmployees) {
		List<Employee> res = new ArrayList<>();
		for(List<Employee> list: listsEmployees) {
			res.addAll(list);
		}
		return res;
	}

	private Integer getBirthYear(int age) {
		
		return LocalDate.now().getYear() - age;
	}

	@Override
	public Iterable<Employee> getEmployeesByDepartment(String department) {
		
		return employeesDepartment.getOrDefault(department, new LinkedList<>());
	}

	@Override
	public Iterable<Employee> getEmployeesBySalary(int salaryFrom, int salaryTo) {
		Collection<List<Employee>> listsEmployees = 
				employeesSalary.subMap(salaryFrom, true, salaryTo, true).values();
		return toCollectionEmployees(listsEmployees);
	}

	@Override
	public Employee getEmployee(long id) {
		
		return employees.get(id);
	}

	@Override
	public MinMaxSalaryEmployees[] getEmployeesBySalariesInterval(int interval) {
		
		return employees.values().stream()
				.collect(Collectors.groupingBy(e -> e.getSalary() / interval))
				.entrySet().stream()
				.map(e -> {
					int minSalary = e.getKey() * interval;
					int maxSalary = minSalary + interval;
					List<Employee> employees = e.getValue();
					employees.sort((e1, e2) -> e1.getSalary() - e2.getSalary());
					return new MinMaxSalaryEmployees(minSalary, maxSalary, employees);
				}).sorted((m1, m2)->Integer.compare(m1.getMinSalary(), m2.getMinSalary()))
				.toArray(MinMaxSalaryEmployees[]::new);
	}

	@Override
	public DepartmentSalary[] getDepartmentAvgSalaryDistribution() {
//		int ar[] = {10,200,30,200,400,30};
//		ar = Arrays.stream(ar).distinct()
//				.toArray();
		
		return employees.values().stream()
				.collect(Collectors.groupingBy(Employee::getDepartment,
						Collectors.averagingInt(Employee::getSalary)))
				.entrySet().stream()
				.sorted((e1, e2) -> Double.compare(e2.getValue(), e1.getValue()))
				.map(e -> new DepartmentSalary(e.getKey(), e.getValue()))
				.toArray(DepartmentSalary[]::new);
	}

}






//package telran.employees.services.impl;
//
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Collection;
//import java.util.Collections;
//import java.util.HashMap;
//import java.util.IntSummaryStatistics;
//import java.util.LinkedList;
//import java.util.List;
//import java.util.NavigableMap;
//import java.util.TreeMap;
//import java.util.stream.Stream;
//
//import telran.employees.dto.DepartmentSalary;
//import telran.employees.dto.Employee;
//import telran.employees.dto.MinMaxSalaryEmployees;
//import telran.employees.dto.ReturnCodes;
//import telran.employees.services.interfaces.EmployeeService;
//
//public class EmployeesServiceMapsImpl implements EmployeeService {
//	HashMap<Long, Employee> employees = new HashMap<>();
//	TreeMap<Integer, List<Employee>> employeesSalary = new TreeMap<>();
//	TreeMap<Integer, List<Employee>> employeesAge = new TreeMap<>();
//	HashMap<String, List<Employee>> employeesDepartment = new HashMap<>();
//
//	@Override
//	public ReturnCodes addEmployee(Employee empl) {
//		Employee res = employees.putIfAbsent(empl.getId(), empl);
//		if (res != null) {
//			return ReturnCodes.EMPLOYEE_ALREADY_EXIST;
//		}
//		addEmployeeSalary(empl);
//		addEmployeeAge(empl);
//		addEmployeeDepartment(empl);
//		return ReturnCodes.OK;
//	}
//
//	private void addEmployeeDepartment(Employee empl) {
//		String dept = empl.getDepartment();
//		employeesDepartment.computeIfAbsent(dept, k -> new ArrayList<>()).add(empl);
//	}
//
//	private void addEmployeeAge(Employee empl) {
//		int birthYear = empl.getBirthDate().getYear();
//		employeesAge.computeIfAbsent(birthYear, k -> new ArrayList<>()).add(empl);
//	}
//
//	private void addEmployeeSalary(Employee empl) {
//		int salary = empl.getSalary();
//		employeesSalary.computeIfAbsent(salary, k -> new ArrayList<>()).add(empl);
//	}
//
//	@Override
//	public Iterable<Employee> getEmployeesByAge(int ageFrom, int ageTo) {
//		NavigableMap<Integer, List<Employee>> submapEmpl = employeesAge.subMap(getBirthYear(ageTo), true,
//				getBirthYear(ageFrom), true);
//		return toColectionEmployees(submapEmpl.values());
//	}
//
//	private Integer getBirthYear(int age) {
//
//		return LocalDate.now().getYear() - age;
//	}
//
//	@Override
//	public Iterable<Employee> getEmployeesByDepartment(String department) {
//
//		return Collections.unmodifiableList(employeesDepartment.getOrDefault(department, new LinkedList<Employee>()));
//	}
//
//	@Override
//	public Iterable<Employee> getEmployeesBySalary(int salaryFrom, int salaryTo) {
//		NavigableMap<Integer, List<Employee>> submapEmpl = employeesSalary.subMap(salaryFrom, true, salaryTo, true);
//		return toColectionEmployees(submapEmpl.values());
//	}
//
//	@Override
//	public Employee getEmployee(long id) {
//		return employees.get(id);
//	}
//
//	@Override
//	public Employee updateEmployee(long id, Employee newEmployee) {
//		if (removeEmployee(id) == ReturnCodes.OK) {
//			addEmployee(newEmployee);
//			return newEmployee;
//		}
//		return null;
//	}
//
//	@Override
//	public ReturnCodes removeEmployee(long id) {
//		Employee empl = employees.remove(id);
//		if (empl == null) {
//			return ReturnCodes.EMPLOYEE_NOT_FOUND;
//		}
//		removeEmployeeAge(empl);
//		removeEmployeeSalary(empl);
//		removeEmployeeDepartment(empl);
//		return ReturnCodes.OK;
//	}
//
//	private void removeEmployeeDepartment(Employee empl) {
//		String dept = empl.getDepartment();
//		List<Employee> employeesList = employeesDepartment.get(dept);
//		if (employeesList.size() == 1) {
//			employeesDepartment.remove(dept);
//		}
//			employeesList.remove(empl);
//	}
//	private void removeEmployeeSalary(Employee empl) {
//		Integer salary = empl.getSalary();
//		List<Employee> employeesList = employeesSalary.get(salary);
//		if (employeesList.size() == 1) {
//			employeesSalary.remove(salary);
//		}
//			employeesList.remove(empl);
//		
//	}
//
//	private void removeEmployeeAge(Employee empl) {
//		Integer birtYear = empl.getBirthDate().getYear();
//		List<Employee> employeesList = employeesAge.get(birtYear);
//		if (employeesList.size() == 1) {
//			employeesAge.remove(birtYear);	
//		} 
//		employeesList.remove(empl);
//	}
//
//	private Iterable<Employee> toColectionEmployees(Collection<List<Employee>> values) {
//		ArrayList<Employee> empl = new ArrayList<>();
//		values.forEach(empl::addAll);
//		return empl;
//	}
//
//	@Override
//	public MinMaxSalaryEmployees[] getEmployeesBySalariesInterval(int interval) {
//		ArrayList<MinMaxSalaryEmployees> newEmpl = new ArrayList<>();
//		int minSalary = employeesSalary.firstKey();
//		int maxSalary =employeesSalary.lastKey();
//		while (minSalary < maxSalary) {
//			List<Employee> emplInt = new ArrayList<>();
//				Stream.of((List<Employee>)getEmployeesBySalary(minSalary, minSalary+interval))
//				.forEach(x->emplInt.addAll(x));
//			newEmpl.add(new MinMaxSalaryEmployees(minSalary, minSalary + interval, emplInt));
//			minSalary += interval;
//		}
//		return newEmpl.toArray(MinMaxSalaryEmployees[]::new);
//	}
//
//	@Override
//	public DepartmentSalary[] getDepartmentAvgSalaryDistribution() {
//		List<DepartmentSalary> deptlist = new ArrayList<>();
//		employeesDepartment.forEach((k, v) -> {
//			IntSummaryStatistics stats = v.stream().mapToInt(Employee::getSalary).summaryStatistics();
//			deptlist.add(new DepartmentSalary(k, stats.getAverage()));
//		});
//		Arrays.sort(deptlist.toArray(new DepartmentSalary[0]),
//				(s1, s2) -> Double.compare(s2.getAvgSalary(), s1.getAvgSalary()));
//
//		return deptlist.toArray(new DepartmentSalary[0]);
//	}
//
//}
