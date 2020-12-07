import telran.net.server.ServerJava;

public class TcpServerExampleAppl {

	public static void main(String[] args) {
	ServerJava serverJava = new ServerJava(5000, new ProtocolExample());
	serverJava.run();

	}

}
