
package telran.employees.tests;


import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.time.LocalDate;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telran.employees.dto.DepartmentSalary;
import telran.employees.dto.Employee;
import telran.employees.dto.MinMaxSalaryEmployees;
import telran.employees.dto.ReturnCodes;
import telran.employees.services.impl.EmployeesServiceMapsImpl;
import telran.employees.services.interfaces.EmployeeService;


class EmployeesServiceTests {
private static final int SALARY1 = 1000;
private static final String DEPARTMENT1 = "QA";
private static int AGE1 = 40;
private static int AGE2 = 45;
private static int AGE3 = 50;
private static int AGE4 = 55;
private static final LocalDate BIRTH_DATE1 =
LocalDate.ofYearDay(LocalDate.now().getYear() - AGE1, 1);
private static final int SALARY2 = 2000;
private static final LocalDate BIRTH_DATE2 =
LocalDate.ofYearDay(LocalDate.now().getYear() - AGE2, 1);;
private static final int SALARY3 = 3000;
private static final String DEPARTMENT2 = "Development";
private static final int SALARY4 = 4000;
private static final LocalDate BIRTH_DATE3 = LocalDate.ofYearDay(LocalDate.now().getYear() - AGE3, 1);
private static final LocalDate BIRTH_DATE4 = LocalDate.ofYearDay(LocalDate.now().getYear() - AGE4, 1);
private static final long ID1 = 1;
private static final long ID2 = 2;
private static final long ID3 = 3;
private static final long ID4 = 4;
Employee empl1 = new Employee(ID1, "Ivan", BIRTH_DATE1, DEPARTMENT1, SALARY1);
Employee empl2 = new Employee(ID2, "Petr", BIRTH_DATE2, DEPARTMENT1,SALARY2 );
		
Employee empl3 = new Employee(ID3,"Boris",BIRTH_DATE3 ,DEPARTMENT2,SALARY3 );
Employee empl4 = new Employee(ID4,"David", BIRTH_DATE4, DEPARTMENT2,SALARY4 );
Employee newEmployee = new Employee(123777, "Olga",BIRTH_DATE4 ,DEPARTMENT2,8000 );
Employee employees[] = {empl1, empl2, empl3, empl4};
EmployeeService employeesService ;
	@BeforeEach
	void setUp() throws Exception {
		employeesService = new EmployeesServiceMapsImpl();
		for (Employee empl: employees) {
			employeesService.addEmployee(empl);
		}
	}

	@Test
	void testAddEmployee() {
		assertEquals(ReturnCodes.EMPLOYEE_ALREADY_EXIST,
				employeesService.addEmployee(empl1));
		assertEquals(ReturnCodes.OK,
				employeesService.addEmployee(newEmployee));
	}

	@Test
	void testRemoveEmployee() {
		assertEquals
		(ReturnCodes.EMPLOYEE_NOT_FOUND,
				employeesService.removeEmployee(77777));
		Employee expected[] = {empl2, empl3, empl4};
		assertEquals
		(ReturnCodes.OK,
				employeesService.removeEmployee(ID1));
		testEmployees(expected, employeesService.getEmployeesBySalary(0, Integer.MAX_VALUE));
		
	}

	private void testEmployees(Employee[] expected,
			Iterable<Employee> employeesIter) {
		Employee[] actual = new Employee[expected.length];
		int ind = 0;
		for(Employee empl: employeesIter) {
			actual[ind++] = empl;
		}
		Arrays.sort(actual, (e1, e2) -> Long.compare(e1.getId(), e2.getId()));
		assertArrayEquals(expected, actual);
		
	}

	@Test
	void testGetEmployee() {
		assertEquals(empl1, employeesService.getEmployee(ID1));
		assertNull(employeesService.getEmployee(777777));
	}

	

	@Test
	void testGetEmployeesCompany() {
		Employee expected[] = {empl1, empl2};
		Iterable<Employee> employeesEmpty =
				employeesService.getEmployeesByDepartment("company");
		assertFalse(employeesEmpty.iterator().hasNext());
		Iterable<Employee> employeesCompany1 =
				employeesService.getEmployeesByDepartment(DEPARTMENT1);
		testEmployees(expected, employeesCompany1);
		
		
	}

	@Test
	void testGetEmployeesAges() {
	Employee expected1[] = {empl1, empl2};
	Employee expected2[] = {empl3, empl4};
		Iterable<Employee> employeesEmpty =
				employeesService.getEmployeesByAge(100, 120);
		assertFalse(employeesEmpty.iterator().hasNext());
		testEmployees(expected1, employeesService.getEmployeesByAge(AGE1, AGE2));
		testEmployees(expected2, employeesService.getEmployeesByAge(AGE3, AGE4));
		
		
		
	}

	@Test
	void testGetEmployeesSalary() {
		Employee expected[] = {empl1, empl2};
		Iterable<Employee> employeesEmpty =
				employeesService.getEmployeesBySalary(10000, 25000);
		assertFalse(employeesEmpty.iterator().hasNext());
		Iterable<Employee> employees1000_2500 =
				employeesService.getEmployeesBySalary(1000, 2500);
		testEmployees(expected, employees1000_2500);
	}

	@Test
	void testUpdateCompany() {
		assertEquals(empl1,
				employeesService.updateEmployee(ID1, new Employee(ID1,"name",BIRTH_DATE1, "company", SALARY1)));
		Employee empl = employeesService.getEmployee(ID1);
		
		assertEquals("company", empl.getDepartment());
	}

	@Test
	void testUpdateSalary() {
		int newSalary = 100000;
		assertEquals(empl1,
				employeesService.updateEmployee(ID1, new Employee(ID1,"name",BIRTH_DATE1, "company", newSalary)) );
		Employee empl = employeesService.getEmployee(ID1);
		assertEquals(newSalary, empl.getSalary());
	}
	@Test
	void testGetEmployeesBySalariesInterval() {
		MinMaxSalaryEmployees[] minMaxEmpl = employeesService.getEmployeesBySalariesInterval(1500);
		for (MinMaxSalaryEmployees minMax : minMaxEmpl) {
			System.out.println(minMax.toString());
		}
	}

	@Test
	void testGetDepartmentAvgSalaryDistribution() {
		DepartmentSalary[] depts = employeesService.getDepartmentAvgSalaryDistribution();
		for (DepartmentSalary dept : depts) {
			System.out.println(dept.toString());
		}
	}

}