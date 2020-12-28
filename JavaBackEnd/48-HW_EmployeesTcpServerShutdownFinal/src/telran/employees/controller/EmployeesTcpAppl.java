package telran.employees.controller;

import java.util.Scanner;

import telran.employees.net.EmployeesProtocol;
import telran.employees.services.impl.EmployeeServiceMapsImpl;
import telran.net.server.ServerJava;

public class EmployeesTcpAppl {

	private static final int PORT = 5000;

	public static void main(String[] args) {
		ServerJava server = new ServerJava(PORT,
				new EmployeesProtocol(new EmployeeServiceMapsImpl()));
		new Thread(server).start();
		Scanner scanner = new Scanner(System.in);
		while(true) {
			System.out.println("Enter shutdown for the server shutdown");
			String line = scanner.nextLine();
			if(line.equals("shutdown")) {
				System.out.println("bye");
				server.stop();
				break;
			}
		}

	}

}
