package telran.employees.view;
import java.util.List;

import telran.employees.dto.DepartmentSalary;
import telran.employees.dto.Employee;
import telran.employees.dto.MinMaxSalaryEmployees;
import telran.employees.services.interfaces.EmployeeService;
import telran.view.*;
public class StatisticActions {
	static EmployeeService service;
public static Item getStatisticOperationsItem(InputOutput io, String name,
		EmployeeService service) {
	StatisticActions.service = service;
	return Item.of(name, iop -> {
		Item items[] = getItems(iop);
		Menu menu= new Menu(name,items);
		menu.perform(io);
	});
}

private static Item[] getItems(InputOutput iop) {
	Item [] items = {
		Item.of("Distribution salaries by specified intervals",
				StatisticActions::displayEmployeesSalariesInterval),
		Item.of("Distribution average salaries by department", 
				StatisticActions::displayDepartmentAvgSalary),
		Item.exit()
	};
	return items;
}
private static void displayEmployeesSalariesInterval(InputOutput io) {
	int interval = io.readInteger("Enter interval of salaries for the distribution");
	MinMaxSalaryEmployees[] minMaxSalaries = service.getEmployeesBySalariesInterval(interval);
	for(MinMaxSalaryEmployees minMaxOne: minMaxSalaries) {
		displayMinMaxOne(minMaxOne, io);
	}
}
private static void displayDepartmentAvgSalary(InputOutput io) {
	DepartmentSalary departmentsSalary[] = service.getDepartmentAvgSalaryDistribution();
	for(DepartmentSalary department: departmentsSalary) {
		io.writeLn(String.format("%s\t%f", department.getDepartment(), department.getAvgSalary()));
	}
}

private static void displayMinMaxOne(MinMaxSalaryEmployees minMaxOne, InputOutput io) {
	io.writeLn(String.format("[%d - %d]", minMaxOne.getMinSalary(),
			minMaxOne.getMaxSalary()));
	minMaxOne.getEmployees().forEach(io::writeLn);
	
}


}
