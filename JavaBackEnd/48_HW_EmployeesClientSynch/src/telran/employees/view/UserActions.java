package telran.employees.view;
import java.util.List;

import telran.employees.dto.Employee;
import telran.employees.services.interfaces.EmployeeService;
import telran.view.*;
public class UserActions {
	static EmployeeService service;
	private static List<String> departments;
public static Item getUserOperationsItem(InputOutput io, String name,
		EmployeeService service, List<String> departments) {
	UserActions.service = service;
	UserActions.departments = departments;
	return Item.of(name, iop -> {
		Item items[] = getItems(iop);
		Menu menu= new Menu(name,items);
		menu.perform(io);
	});
}

private static Item[] getItems(InputOutput iop) {
	Item [] items = {
		Item.of("Display employee's data", UserActions::getEmployee),
		Item.of("Display data about employees by agies", UserActions::getEmployeesAges),
		Item.of("Display data about employees by salaries", UserActions::getEmployeesSalary),
		Item.of("Display data about employees by department", UserActions::getEmployeesDepartment),
		Item.exit()
	};
	return items;
}
private static void getEmployeesDepartment(InputOutput io) {
	String department = io.readOption("Enter department", departments);
	displayEmployees(service.getEmployeesByDepartment(department), io);
}
private static void getEmployeesAges(InputOutput io) {
	int ageFrom = io.readInteger("Enter age from");
	int ageTo = io.readInteger("Enter age to: not less than " + ageFrom, ageFrom,
			200);
	Iterable<Employee> employees = service.getEmployeesByAge(ageFrom, ageTo);
	displayEmployees(employees, io);
}
private static void getEmployee(InputOutput io) {
	long id = io.readLong("Enter employee's id ");
	Employee empl = service.getEmployee(id);
	if (empl == null) {
		io.writeLn(String.format("Employee with id %d doesn't exist", id));
	} else {
		io.writeLn(empl);
	}
	
	
}

private static void getEmployeesSalary(InputOutput io) {
	int salaryFrom = io.readInteger("Enter salary from");
	int salaryTo = io.readInteger("Enter salary to: not less than " + salaryFrom, salaryFrom,
			Integer.MAX_VALUE);
	Iterable<Employee> employees = service.getEmployeesBySalary(salaryFrom, salaryTo);
	displayEmployees(employees, io);
}

private static void displayEmployees(Iterable<Employee> employees, InputOutput io) {
	if(!employees.iterator().hasNext()) {
		io.writeLn("No employees");
	} else {
		employees.forEach(io::writeLn);
	}
	
}


}
