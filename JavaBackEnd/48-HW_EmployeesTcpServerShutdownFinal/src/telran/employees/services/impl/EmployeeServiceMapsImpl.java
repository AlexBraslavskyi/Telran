package telran.employees.services.impl;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.stream.Collectors;

import telran.employees.dto.DepartmentSalary;
import telran.employees.dto.Employee;
import telran.employees.dto.MinMaxSalaryEmployees;
import telran.employees.dto.ReturnCodes;
import telran.employees.services.interfaces.EmployeeService;

public class EmployeeServiceMapsImpl implements EmployeeService {
HashMap<Long,Employee> employees = new HashMap<>();
ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
Lock readLock = lock.readLock();
Lock writeLock = lock.writeLock();
TreeMap<Integer, List<Employee>> employeesSalary = new TreeMap<>(); //key - salary value, value - list of Employees receiving the salary
TreeMap<Integer, List<Employee>> employeesAge = new TreeMap<>(); //key - birth year, value - list of Employee born at the birth year
HashMap<String, List<Employee>> employeesDepartment = new HashMap<>(); //key -department, value - list of Employees working at the department

@Override
	public ReturnCodes addEmployee(Employee empl) {
		try {
			writeLock.lock();
			Employee res = employees.putIfAbsent(empl.getId(), empl);
			if (res != null) {
				return ReturnCodes.EMPLOYEE_ALREADY_EXISTS;
			}
			addEmployeeSalary(empl);
			addEmployeeAge(empl);
			addEmployeeDepartment(empl);
			return ReturnCodes.OK;
		} finally {
			writeLock.unlock();
		}
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
		try {
			writeLock.lock();
			Employee empl = employees.remove(id);
			if (empl == null) {
				return ReturnCodes.EMPLOYEE_NOT_FOUND;
			}
			removeEmployeeAge(empl);
			removeEmployeeSalary(empl);
			removeEmployeeDepartment(empl);
			return ReturnCodes.OK;
		} finally {
			writeLock.unlock();
		}
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
		
	try {
		writeLock.lock();
		Employee employee = getEmployee(id);
		if (employee == null) {
			return null;
		}
		removeEmployee(id);
		addEmployee(newEmployee);
		return employee;
	} finally {
		writeLock.unlock();
	}
	}

	@Override
	public Iterable<Employee> getEmployeesByAge(int ageFrom, int ageTo) {
	try {
		readLock.lock();
		NavigableMap<Integer, List<Employee>> employeesSubmap = employeesAge.subMap(getBirthYear(ageTo), true,
				getBirthYear(ageFrom), true);
		return toCollectionEmployees(employeesSubmap.values());
	} finally {
		readLock.unlock();
	}
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
		
		try {
			readLock.lock();
			return employeesDepartment.getOrDefault(department, new LinkedList<>());
		} finally {
			readLock.unlock();
		}
	}

	@Override
	public Iterable<Employee> getEmployeesBySalary(int salaryFrom, int salaryTo) {
		try {
			readLock.lock();
			Collection<List<Employee>> listsEmployees = employeesSalary.subMap(salaryFrom, true, salaryTo, true)
					.values();
			return toCollectionEmployees(listsEmployees);
		} finally {
			readLock.unlock();
		}
	}

	@Override
	public Employee getEmployee(long id) {
		
		try {
			readLock.lock();
			return employees.get(id);
		} finally {
			readLock.unlock();
		}
	}

	@Override
	public MinMaxSalaryEmployees[] getEmployeesBySalariesInterval(int interval) {
		
		try {
			readLock.lock();
			return employees.values().stream().collect(Collectors.groupingBy(e -> e.getSalary() / interval)).entrySet()
					.stream().map(e -> {
						int minSalary = e.getKey() * interval;
						int maxSalary = minSalary + interval;
						List<Employee> employees = e.getValue();
						employees.sort((e1, e2) -> e1.getSalary() - e2.getSalary());
						return new MinMaxSalaryEmployees(minSalary, maxSalary, employees);
					}).sorted((m1, m2) -> Integer.compare(m1.getMinSalary(), m2.getMinSalary()))
					.toArray(MinMaxSalaryEmployees[]::new);
		} finally {
			readLock.unlock();
		}
	}

	@Override
	public DepartmentSalary[] getDepartmentAvgSalaryDistribution() {
//		int ar[] = {10,200,30,200,400,30};
//		ar = Arrays.stream(ar).distinct()
//				.toArray();
		
		try {
			readLock.lock();
			return employees.values().stream()
					.collect(Collectors.groupingBy(Employee::getDepartment,
							Collectors.averagingInt(Employee::getSalary)))
					.entrySet().stream().sorted((e1, e2) -> Double.compare(e2.getValue(), e1.getValue()))
					.map(e -> new DepartmentSalary(e.getKey(), e.getValue())).toArray(DepartmentSalary[]::new);
		} finally {
			readLock.unlock();
		}
	}

}
