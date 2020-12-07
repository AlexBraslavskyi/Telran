package telran.employees.view;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import telran.employees.dto.Employee;
import telran.employees.services.interfaces.EmployeeService;
import telran.view.*;
public class AdministratorActions {
	static EmployeeService service;
	static List<String> departments;
public static Item getAdministratorOperationsItem(InputOutput io, String name,
		EmployeeService service, List<String> departments) {
	AdministratorActions.service = service;
	AdministratorActions.departments = departments;
	return Item.of(name, iop -> {
		Item items[] = getItems(iop);
		Menu menu= new Menu(name,items);
		menu.perform(io);
	});
}

private static Item[] getItems(InputOutput iop) {
	Item [] items = {
		Item.of("Add new employee", AdministratorActions::addNewEmployee),
		Item.of("Remove Employee", AdministratorActions::removeEmployee),
		Item.of("Update Employee", AdministratorActions::updateEmployee),
		Item.exit()
	};
	return items;
}
private static void addNewEmployee(InputOutput io) {
	Employee empl = readEmployee(io);
	io.writeLn(service.addEmployee(empl));
	
}

private static Employee readEmployee(InputOutput io) {
	long id = io.readLong("Enter employee's ID");
	String name = io.readString("Enter employee's name");
	LocalDate birthDate = io.readDate("Enter employee's birthdate", "dd/MM/yyyy");
	String department = io.readOption("Enter department",
			departments);
	int salary = io.readInteger("Enter salary", 5000, 30000);
	Employee empl = new Employee(id, name, birthDate, department, salary);
	return empl;
}
private static void updateEmployee(InputOutput io) {
	Employee newEmployee = readEmployee(io);
	io.writeLn(service.updateEmployee(newEmployee.getId(), newEmployee));
}
private static void removeEmployee(InputOutput io) {
	long id = io.readLong("Enter employee's ID");
	io.writeLn(service.removeEmployee(id));
}


}
