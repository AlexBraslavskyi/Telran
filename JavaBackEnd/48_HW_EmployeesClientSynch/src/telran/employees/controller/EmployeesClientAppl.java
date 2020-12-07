package telran.employees.controller;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import telran.employees.net.EmployeesTcpProxy;
import telran.employees.view.AdministratorActions;
import telran.employees.view.StatisticActions;
import telran.employees.view.UserActions;
import telran.view.*;
public class EmployeesClientAppl {

	private static final String HOST_NAME = "localhost";
	private static final int PORT = 5000;

	public static void main(String[] args) {
	InputOutput io = new ConsoleInputOutput();
	EmployeesTcpProxy service = new EmployeesTcpProxy(HOST_NAME, PORT);
	List<String> departments = Arrays.asList("Development", "QA", "Security", "Management", "Sales");
	Item [] items = {
			AdministratorActions.getAdministratorOperationsItem(io, "Administrator actions",
					service,departments ),
			UserActions.getUserOperationsItem(io, "User Actions", service, departments),
			StatisticActions.getStatisticOperationsItem(io, "Statistic Distributions", service),
			Item.of("Exit", iop->{
				try {
					service.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}, true)
	};
	Menu menu = new Menu("Employees Menu", items);
	menu.perform(io);

	}

}
