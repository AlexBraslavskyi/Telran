package telran.employees.view;
import java.util.Arrays;

import telran.employees.services.interfaces.EmployeeService;
import telran.view.InputOutput;
import telran.view.Item;
import telran.view.Menu;

public class UserActions {
static EmployeeService service;
	public static Item getUserOperationsItem(InputOutput io, String name, EmployeeService service) {
		UserActions.service = service;
		return Item.of(name, iop->{
			Item items[] = getItems(iop);
			Menu menu = new Menu(name, items);
			menu.perform(io);
		});
	}

	private static Item[] getItems(InputOutput iop) {
		
		Item [] items = {
				Item.of("Get employee", UserActions::getEmployee),
				Item.of("Get employees by dept", UserActions::getEmplByDept),
				Item.of("Get employees by age", UserActions::getEmplByAge),
				Item.of("Get employees by salary", UserActions::getEmplBySalary),
				Item.exit()
		};
		return items;
	}
	private static void getEmployee(InputOutput io) {
		long id = io.readLong("Enter employee's id");
		io.writeLn(service.getEmployee(id));
		
		
		}
	private static void getEmplByDept(InputOutput io) {
		String department = io.readOption("Enter employee's department", 
				Arrays.asList("Development","QA","Sales","Management"));
		io.writeLn(service.getEmployeesByDepartment(department));
		
		
		}
	private static void getEmplByAge(InputOutput io) {
		int ageFrom = io.readInteger("Enter age from");
		int ageTo = io.readInteger("Enter age to");
		io.writeLn(service.getEmployeesByAge(ageFrom, ageTo));
	
		}
	private static void getEmplBySalary(InputOutput io) {
		int salaryFrom = io.readInteger("Enter salary from");
		int salaryTo = io.readInteger("Enter salary to");
		io.writeLn(service.getEmployeesBySalary(salaryFrom, salaryTo));
	
		}
	}
