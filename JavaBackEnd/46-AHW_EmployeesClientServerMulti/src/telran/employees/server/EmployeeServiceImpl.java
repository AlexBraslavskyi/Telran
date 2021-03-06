package telran.employees.server;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import telran.employees.common.EmployeeService;
import telran.employees.common.dto.DepartmentSalary;
import telran.employees.common.dto.Employee;
import telran.employees.common.dto.MinMaxSalaryEmployees;
import telran.employees.common.dto.ReturnCodes;




public class EmployeeServiceImpl implements EmployeeService {
	Map<Long, Employee> employees = new ConcurrentHashMap<>();
	Map<Integer, List<Employee>> employeesSalary = new ConcurrentSkipListMap<>();
	Map<Integer, List<Employee>> employeesAge = new ConcurrentSkipListMap<>();
	Map<String, List<Employee>> employeesDepartment = new ConcurrentHashMap<>();

	private int getBirthYear(Employee empl) {
		return empl.getBirthDate().getYear();
	}

	@Override
	public ReturnCodes addEmployee(Employee empl) {
		if (employees.putIfAbsent(empl.getId(), empl) != null) {
			return ReturnCodes.EMPLOYEE_ALREADY_EXISTS;
		}
		addEmployeeToOthersMaps(empl, employeesSalary, empl.getSalary());
		
		addEmployeeToOthersMaps(empl, employeesAge, getBirthYear(empl));
		addEmployeeToOthersMaps(empl, employeesDepartment, empl.getDepartment());
		return ReturnCodes.OK;

	}

	private <T> void addEmployeeToOthersMaps(Employee empl, Map<T, List<Employee>> map, T fieldOfEmployee) {
		List<Employee> helperList = map.computeIfAbsent(fieldOfEmployee, (k) -> new ArrayList<>());
		helperList.add(empl);
	}

	@Override
	public ReturnCodes removeEmployee(long id) {
		Employee empl = employees.remove(id);
		if (empl == null) {
			return ReturnCodes.EMPLOYEE_NOT_FOUND;
		}
		removeEmployeeFromOthersMaps(empl, employeesSalary, empl.getSalary());
		removeEmployeeFromOthersMaps(empl, employeesAge, getBirthYear(empl));
		removeEmployeeFromOthersMaps(empl, employeesDepartment, empl.getDepartment());
		return ReturnCodes.OK;
	}

	private <T> void removeEmployeeFromOthersMaps(Employee empl, Map<T, List<Employee>> map, T fieldOfEmployee) {
		List<Employee> helpList = map.get(fieldOfEmployee);
		helpList.remove(empl);
		if (helpList.isEmpty()) {
			map.remove(fieldOfEmployee);
		}
	}

	@Override
	public Employee updateEmployee(Employee updatedEmployee) {
		Employee empl = employees.replace(updatedEmployee.getId(), updatedEmployee);
		if (empl == null) {
			return null;
		}
		updateEmployeeInOthersMaps(empl, updatedEmployee, employeesSalary, empl.getSalary());
		updateEmployeeInOthersMaps(empl, updatedEmployee, employeesAge, getBirthYear(empl));
		updateEmployeeInOthersMaps(empl, updatedEmployee, employeesDepartment, empl.getDepartment());
		return empl;
	}

	private <T> void updateEmployeeInOthersMaps(Employee empl, Employee newEmployee, Map<T, List<Employee>> map,
			T fieldOfEmployee) {
		removeEmployeeFromOthersMaps(empl, map, fieldOfEmployee);
		addEmployeeToOthersMaps(empl, map, fieldOfEmployee);
	}

	@Override
	public ArrayList<Employee> getEmployeesByAges(int ageFrom, int ageTo) {
		if (ageTo < ageFrom) {
			throw new IllegalArgumentException();
		}
		NavigableMap<Integer, List<Employee>> subMap = employeesAge).subMap(LocalDate.now().getYear() - ageTo, true,
				LocalDate.now().getYear() - ageFrom, true);
		return toArrayList(new ConcatenatingIterator<Employee>(subMap.values()));
	}

	@Override
	public ArrayList<Employee> getEmployeesByDepartment(String department) {
		return toArrayList(employeesDepartment.getOrDefault(department, new LinkedList<>()));
	}

	@Override
	public ArrayList<Employee> getEmployeesBySalary(int salaryFrom, int salaryTo) {
		if (salaryTo < salaryFrom) {
			throw new IllegalArgumentException();
		}
		NavigableMap<Integer, List<Employee>> subMap = employeesSalary.subMap(salaryFrom, true, salaryTo, true);		
		return toArrayList(new ConcatenatingIterator<Employee>(subMap.values()));
	}

	@Override
	public Employee getEmployee(long id) {
		return employees.get(id);
	}

	@Override
	public int size() {
		return employees.size();
	}

	@Override
	public ArrayList<Employee> getAllEmployees() {
		return  toArrayList(employees.values());
	}

	public Iterable<Employee> getEmployeesBySalaryByStream(int salaryFrom, int salaryTo) {
		if (salaryTo < salaryFrom) {
			throw new IllegalArgumentException();
		}
		return employeesSalary.entrySet().stream().filter(e -> e.getKey() >= salaryFrom && e.getKey() < salaryTo)
				.flatMap(e -> e.getValue().stream()).collect(Collectors.toList());

	}

	private int getMin(Collection<Employee> collection) {
		return collection.stream().mapToInt(Employee::getSalary).summaryStatistics().getMin();
	}

	private int getMax(Collection<Employee> collection) {
		return collection.stream().mapToInt(Employee::getSalary).summaryStatistics().getMax();
	}

	@Override
	public MinMaxSalaryEmployees[] getEmployeesBySalariesInterval(int intervals) {
		int smallestSalary = getMin(employees.values());
		int biggestSalary = getMax(employees.values());
		double intervalLenth = (biggestSalary + 1.0 - smallestSalary) / intervals;

		return Stream.iterate(0, i -> i + 1).limit(intervals)
				.map(ind -> new MinMaxSalaryEmployees(smallestSalary + (int)(ind * intervalLenth), smallestSalary + (int)((ind + 1) * intervalLenth),
						(List<Employee>) getEmployeesBySalaryByStream(smallestSalary + (int)(ind * intervalLenth),
								smallestSalary + (int)((ind + 1) * intervalLenth))))

				.limit(intervals)
				.sorted((mms1, mms2) -> getMin(mms1.getEmployees()) - getMin(mms2.getEmployees()))
				.toArray(MinMaxSalaryEmployees[]::new);
	}

	@Override
	public DepartmentSalary[] getDepartmentAvgSalaryDistribution() {
		return employeesDepartment.entrySet().stream()
				.map(e -> new DepartmentSalary(e.getKey(),
						e.getValue().stream().mapToInt(Employee::getSalary).summaryStatistics().getAverage()))
				.sorted((ds1, ds2) -> Double.compare(ds2.getAvgSalary(), ds1.getAvgSalary()))
				.toArray(DepartmentSalary[]::new);

	}

	private ArrayList<Employee> toArrayList(Iterable<Employee> iterable){
		ArrayList<Employee> res = new ArrayList<>();
		System.out.println(res);
		for (Employee emp: iterable) {
			res.add(emp);
		}
		return res;
	}

}
