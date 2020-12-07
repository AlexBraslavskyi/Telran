package telran.employees.controller;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import sun.rmi.transport.tcp.TCPDirectSocketFactory;
import telran.employees.net.EmployeesTcpProxy;
import telran.employees.view.AdministratorActions;
import telran.employees.view.StatisticActions;
import telran.employees.view.UserActions;
import telran.net.RequestJava;
import telran.net.TcpClientJava;
import telran.view.ConsoleInputOutput;
import telran.view.InputOutput;
import telran.view.Item;
import telran.view.Menu;
public class EmployeesClientAppl {

	private static final String HOST_NAME = "localhost";
	private static final int PORT = 5000;
	static EmployeesTcpProxy service ;
	public static void main(String[] args) {
	InputOutput io = new ConsoleInputOutput();
	boolean []isClose = {false};
	service = new EmployeesTcpProxy(HOST_NAME, PORT);
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
			}, true),
			Item.of("Stop server", servStop->{
				try {
					RequestJava request = new RequestJava("Yes", String.format("Yes"));
					
					io.writeLn(request);
					System.out.println("Client and server closed");
					service.close();
//					isClose[0] = stopServer(io);
//					System.out.println(isClose[0]);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			},true)
	};
	Menu menu = new Menu("Employees Menu", items);
	menu.perform(io);

	}
	}

