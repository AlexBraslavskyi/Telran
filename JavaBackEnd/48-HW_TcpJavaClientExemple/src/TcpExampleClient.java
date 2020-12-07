import telran.net.TcpClientJava;

public class TcpExampleClient extends TcpClientJava {

	public TcpExampleClient(String hostname, int port) {
		super(hostname, port);
		
	}

	public String send(String type, String line) {
		return sendRequest(type, line).toString();
	}
}
