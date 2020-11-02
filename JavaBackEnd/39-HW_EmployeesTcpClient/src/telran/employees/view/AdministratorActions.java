package telran.employees.view;
import static telran.employees.api.ApiConstants.SALARY_FROM;
import static telran.employees.api.ApiConstants.SALARY_TO;

import java.time.LocalDate;
import java.util.Arrays;

import telran.employees.dto.Employee;
import telran.employees.services.interfaces.EmployeeService;
import telran.view.InputOutput;
import telran.view.Item;
import telran.view.Menu;

public class AdministratorActions {
static EmployeeService service;
	public static Item getAdministratorOperationsItem(InputOutput io, String name, EmployeeService service) {
		AdministratorActions.service = service;
		return Item.of(name, iop->{
			Item items[] = getItems(iop);
			Menu menu = new Menu(name, items);
			menu.perform(io);
		});
	}

	private static Item[] getItems(InputOutput iop) {
		
		Item [] items = {
				Item.of("Add new employee", AdministratorActions::addNewEmployee),
				Item.of("Remove employee", AdministratorActions::removeEmployee),
				Item.of("Update employee", AdministratorActions::updateEmployee),
				Item.exit()
		};
		return items;
	}

	private static void addNewEmployee(InputOutput io) {
	long id = io.readLong("Enter employee's id");
	String name = io.readString("Enter employee's name");
	LocalDate birthDate = io.readDate("Enter employee's birthdate", "dd/MM/yyyy");
	String department = io.readOption("Enter employee's department", 
			Arrays.asList("Development","QA","Sales","Management"));
	int salary = io.readInteger(String.format("Enter salary in range : %d - %d", SALARY_FROM,SALARY_TO));
	io.writeLn(service.addEmployee(new Employee(id, name, birthDate, department, salary)));
	
	
	}
	
	private static void removeEmployee(InputOutput io) {
		long id = io.readLong("Enter employee's id");
		io.writeLn(service.removeEmployee(id));
		
		
		}
	private static void updateEmployee(InputOutput io) {
		long id = io.readLong("Enter employee's id");
		String name = io.readString("Enter employee's name");
		LocalDate birthDate = io.readDate("Enter employee's birthdate", "dd/MM/yyyy");
		String department = io.readOption("Enter employee's department", 
				Arrays.asList("Development","QA","Sales","Management"));
		int salary = io.readInteger(String.format("Enter salary in range : %d - %d", SALARY_FROM,SALARY_TO));
		io.writeLn(service.updateEmployee(id, new Employee(id, name, birthDate, department, salary)));
//		io.writeLn(service.removeEmployee(id));
//		addNewEmployee(io);
		}

	}
