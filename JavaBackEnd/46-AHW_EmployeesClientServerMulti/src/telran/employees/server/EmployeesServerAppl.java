package telran.employees.server;

import static telran.employees.common.EmployeesApiConstants.*;

import telran.net.server.TcpServer;

public class EmployeesServerAppl {

	public static void main(String[] args) {
		// TODO: Parallel Server with non-synchronized model. Needs synchronization
		TcpServer server = new TcpServer(PORT, new EmployeesProtocolController(new EmployeeServiceImpl()));
		server.run();
	}
}