package telran.employees.controller;

import java.lang.reflect.InvocationTargetException;

import telran.employees.net.EmployeesProtocol;
import telran.employees.services.impl.EmployeesServiceMapsImpl;
import telran.net.server.ServerJava;

public class EmployeesTcpAppl {

	private static final int PORT = 5000;


	public static void main(String[] args) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, ClassNotFoundException {
		
		ServerJava server = new ServerJava(PORT, new EmployeesProtocol(new EmployeesServiceMapsImpl()));
		server.run();
		
	}

}
