//package telran.employees.tests;
//import static org.junit.jupiter.api.Assertions.assertArrayEquals;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertFalse;
//import static org.junit.jupiter.api.Assertions.assertNull;
//
//import java.time.LocalDate;
//import java.util.Arrays;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import telran.employees.dto.Employee;
//import telran.employees.dto.ReturnCodes;
//import telran.employees.services.impl.EmployeesServiceMapsImpl;
//import telran.employees.services.interfaces.EmployeeService;
//
//
//class EmployeesServiceTests {
//private static final int SALARY1 = 1000;
//private static final String DEPARTMENT1 = "company1";
//private static int AGE1 = 40;
//private static int AGE2 = 45;
//private static int AGE3 = 50;
//private static int AGE4 = 55;
//private static final LocalDate BIRTH_DATE1 =
//LocalDate.ofYearDay(LocalDate.now().getYear() - AGE1, 1);
//private static final int SALARY2 = 2000;
//private static final LocalDate BIRTH_DATE2 =
//LocalDate.ofYearDay(LocalDate.now().getYear() - AGE2, 1);;
//private static final int SALARY3 = 3000;
//private static final String DEPARTMENT2 = "company2";
//private static final int SALARY4 = 4000;
//private static final LocalDate BIRTH_DATE3 = LocalDate.ofYearDay(LocalDate.now().getYear() - AGE3, 1);
//private static final LocalDate BIRTH_DATE4 = LocalDate.ofYearDay(LocalDate.now().getYear() - AGE4, 1);
//private static final long ID1 = 1;
//private static final long ID2 = 2;
//private static final long ID3 = 3;
//private static final long ID4 = 4;
//Employee empl1 = new Employee(ID1, "name", BIRTH_DATE1, DEPARTMENT1, SALARY1);
//Employee empl2 = new Employee(ID2, "name", BIRTH_DATE2, DEPARTMENT1,SALARY2 );
//		
//Employee empl3 = new Employee(ID3,"name",BIRTH_DATE3 ,DEPARTMENT2,SALARY3 );
//Employee empl4 = new Employee(ID4,"name", BIRTH_DATE4, DEPARTMENT2,SALARY4 );
//Employee newEmployee = new Employee(123777, "name",BIRTH_DATE4 ,DEPARTMENT2,SALARY4 );
//Employee employees[] = {empl1, empl2, empl3, empl4};
//EmployeeService employeesService ;
//	@BeforeEach
//	void setUp() throws Exception {
//		employeesService = new EmployeesServiceMapsImpl();
//		for (Employee empl: employees) {
//			employeesService.addEmployee(empl);
//		}
//	}
//
//	@Test
//	void testAddEmployee() {
//		assertEquals(ReturnCodes.EMPLOYEE_ALREADY_EXIST,
//				employeesService.addEmployee(empl1));
//		assertEquals(ReturnCodes.OK,
//				employeesService.addEmployee(newEmployee));
//	}
//
//	@Test
//	void testRemoveEmployee() {
//		assertEquals
//		(ReturnCodes.EMPLOYEE_NOT_FOUND,
//				employeesService.removeEmployee(77777));
//		Employee expected[] = {empl2, empl3, empl4};
//		assertEquals
//		(ReturnCodes.OK,
//				employeesService.removeEmployee(ID1));
//		testEmployees(expected, employeesService.getEmployeesBySalary(0, Integer.MAX_VALUE));
//		
//	}
//
//	private void testEmployees(Employee[] expected,
//			Iterable<Employee> employeesIter) {
//		Employee[] actual = new Employee[expected.length];
//		int ind = 0;
//		for(Employee empl: employeesIter) {
//			actual[ind++] = empl;
//		}
//		Arrays.sort(actual, (e1, e2) -> Long.compare(e1.getId(), e2.getId()));
//		assertArrayEquals(expected, actual);
//		
//	}
//
//	@Test
//	void testGetEmployee() {
//		assertEquals(empl1, employeesService.getEmployee(ID1));
//		assertNull(employeesService.getEmployee(777777));
//	}
//
//	
//
//	@Test
//	void testGetEmployeesCompany() {
//		Employee expected[] = {empl1, empl2};
//		Iterable<Employee> employeesEmpty =
//				employeesService.getEmployeesByDepartment("company");
//		assertFalse(employeesEmpty.iterator().hasNext());
//		Iterable<Employee> employeesCompany1 =
//				employeesService.getEmployeesByDepartment(DEPARTMENT1);
//		testEmployees(expected, employeesCompany1);
//		
//		
//	}
//
//	@Test
//	void testGetEmployeesAges() {
//	Employee expected1[] = {empl1, empl2};
//	Employee expected2[] = {empl3, empl4};
//		Iterable<Employee> employeesEmpty =
//				employeesService.getEmployeesByAge(100, 120);
//		assertFalse(employeesEmpty.iterator().hasNext());
//		testEmployees(expected1, employeesService.getEmployeesByAge(AGE1, AGE2));
//		testEmployees(expected2, employeesService.getEmployeesByAge(AGE3, AGE4));
//		
//		
//		
//	}
//
//	@Test
//	void testGetEmployeesSalary() {
//		Employee expected[] = {empl1, empl2};
//		Iterable<Employee> employeesEmpty =
//				employeesService.getEmployeesBySalary(10000, 25000);
//		assertFalse(employeesEmpty.iterator().hasNext());
//		Iterable<Employee> employees1000_2500 =
//				employeesService.getEmployeesBySalary(1000, 2500);
//		testEmployees(expected, employees1000_2500);
//	}
//
//	@Test
//	void testUpdateCompany() {
//		assertEquals(empl1,
//				employeesService.updateEmployee(ID1, new Employee(ID1,"name",BIRTH_DATE1, "company", SALARY1)));
//		Employee empl = employeesService.getEmployee(ID1);
//		
//		assertEquals("company", empl.getDepartment());
//	}
//
//	@Test
//	void testUpdateSalary() {
//		int newSalary = 100000;
//		assertEquals(empl1,
//				employeesService.updateEmployee(ID1, new Employee(ID1,"name",BIRTH_DATE1, "company", newSalary)) );
//		Employee empl = employeesService.getEmployee(ID1);
//		assertEquals(newSalary, empl.getSalary());
//	}
//
//}



package telran.employees.tests;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import telran.employees.dto.Employee;
import telran.employees.dto.ReturnCodes;
import telran.employees.services.impl.EmployeesServiceMapsImpl;

class EmployeesServiceMapsImplTest {
Employee first = new Employee(1, "Ivan", LocalDate.parse("1982-05-20"), "QA", 5000);
Employee second = new Employee(2, "Alex", LocalDate.parse("1980-05-20"), "Developer", 6000);
Employee third= new Employee(3, "Viktor", LocalDate.parse("1970-05-20"), "Developer", 10000);
Employee fourth = new Employee(4, "Sergei", LocalDate.parse("1990-05-20"), "QA", 8000);

EmployeesServiceMapsImpl empl = new EmployeesServiceMapsImpl();
	@Test
	void test() {
		empl.addEmployee(first);
		empl.addEmployee(second);
		empl.addEmployee(third);
		empl.addEmployee(fourth);
		assertEquals(ReturnCodes.EMPLOYEE_ALREADY_EXIST, empl.addEmployee(first));
		assertEquals(first, empl.getEmployee(1));
		assertEquals(ReturnCodes.OK, empl.removeEmployee(2));
		assertEquals(null, empl.getEmployee(2));
		Employee firstNew = new Employee(1, "Ivan", LocalDate.parse("1982-05-20"), "QA", 10000);
		empl.updateEmployee(1, firstNew);
		assertEquals(10000, empl.getEmployee(1).getSalary());
//		empl.getEmployeesByAge(30, 40).forEach(System.out :: println);
//		empl.getEmployeesByDepartment("QA").forEach(System.out :: println);
//		empl.getEmployeesBySalary(5000,6000).forEach(System.out :: println);
		Iterable<Employee> emplByAge = empl.getEmployeesByAge(30, 40);
		for (Employee empl : emplByAge) {
			assertTrue(30<=LocalDate.now().getYear() - empl.getBirthDate().getYear()&&
					LocalDate.now().getYear() - empl.getBirthDate().getYear()<=40);
		}
		Iterable<Employee> emplByDept = empl.getEmployeesByDepartment("QA");
		for (Employee empl : emplByDept) {
			assertEquals("QA", empl.getDepartment());
		
		}
		
		Iterable<Employee> emplBySalary = empl.getEmployeesBySalary(5000, 6000);
		for (Employee empl : emplBySalary) {
			assertTrue(empl.getSalary() >= 5000 && empl.getSalary() <= 6000);
		}
		
	}

}
