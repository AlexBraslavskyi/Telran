package telran.employees.server;

import static telran.employees.common.EmployeesApiConstants.PORT;

import java.util.Scanner;

import telran.net.server.TcpServer;

public class EmployeesServerAppl {
	public static void main(String[] args) {
		TcpServer server = new TcpServer(PORT, new EmployeesProtocolController(new EmployeeServiceImpl()));
		Scanner scan = new Scanner(System.in);
		Thread main = new Thread(server);
		main.start();
		while(true) {
			String message = scan.nextLine();
			if(message.equalsIgnoreCase("exit")) {
				scan.close();
				server.stopedFlag(true);
				break;
			}
		}
		try {
			main.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Server stoped");
	}
}