package telran.employees.controller;
import java.io.IOException;

import telran.employees.net.EmployeesTcpProxy;
import telran.employees.view.AdministratorActions;
import telran.employees.view.Statistics;
import telran.employees.view.UserActions;
import telran.view.ConsoleInputOutput;
import telran.view.InputOutput;
import telran.view.Item;
import telran.view.Menu;
public class EmployeesClientAppl {

	private static final String HOST_NAME = "localhost";
	private static final int PORT = 5000;

	public static void main(String[] args) {
	InputOutput io = new ConsoleInputOutput();
	
	EmployeesTcpProxy service = new EmployeesTcpProxy(HOST_NAME, PORT);
	Item[]items = {
			AdministratorActions.getAdministratorOperationsItem(io, "Administrator actions", service),
			UserActions.getUserOperationsItem(io, "User actions", service),
			Statistics.getStatisticsItem(io, "Statistics", service),
		
		Item.of("Exit", iop-> {
			try {
				service.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}, true)	
	};
	Menu menu = new Menu("Employees Menu",items);
	menu.perform(io);

	}

}
