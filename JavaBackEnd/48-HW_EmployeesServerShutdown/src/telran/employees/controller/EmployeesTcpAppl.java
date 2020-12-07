package telran.employees.controller;

import telran.employees.net.EmployeesProtocol;
import telran.employees.services.impl.EmployeeServiceMapsImpl;
import telran.net.server.ServerJava;

public class EmployeesTcpAppl {

	private static final int PORT = 5000;

	public static void main(String[] args) {
//		ServerJava server = new ServerJava(PORT,
//				new EmployeesProtocol(new EmployeeServiceMapsImpl()));
//		
//		Thread serverThread = new Thread(server);
//		serverThread.run();
//		
		
		
		ServerJava server = new ServerJava(PORT,
				new EmployeesProtocol(new EmployeeServiceMapsImpl()));
		server.run();
		
//		if()

	}

}
