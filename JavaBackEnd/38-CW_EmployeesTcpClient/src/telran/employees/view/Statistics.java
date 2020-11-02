package telran.employees.view;
import telran.employees.dto.DepartmentSalary;
import telran.employees.dto.MinMaxSalaryEmployees;
import telran.employees.services.interfaces.EmployeeService;
import telran.view.InputOutput;
import telran.view.Item;
import telran.view.Menu;

public class Statistics {
static EmployeeService service;
	public static Item getStatisticsItem(InputOutput io, String name, EmployeeService service) {
		Statistics.service = service;
		return Item.of(name, iop->{
			Item items[] = getItems(iop);
			Menu menu = new Menu(name, items);
			menu.perform(io);
		});
	}

	private static Item[] getItems(InputOutput iop) {
		
		Item [] items = {
				Item.of("Get employees by salary interval", Statistics::getEmplBySalaryInterval),
				Item.of("Get departments average salary distributions", Statistics::getDeptAvrSalaryDistr),
				Item.exit()
		};
		return items;
	}
	private static void getEmplBySalaryInterval(InputOutput io) {
		int interval = io.readInteger("Enter interval");
		MinMaxSalaryEmployees[] minMaxEmpl = service.getEmployeesBySalariesInterval(interval);
		for (MinMaxSalaryEmployees minMax : minMaxEmpl) {
			io.writeLn(minMax);
		}
			
		
		}
	private static void getDeptAvrSalaryDistr(InputOutput io) {
		DepartmentSalary[] depts = service.getDepartmentAvgSalaryDistribution();
		for (DepartmentSalary dept : depts) {
			io.writeLn(dept);
		}
		
		}
	}
