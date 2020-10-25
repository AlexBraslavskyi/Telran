package telran.games.impl;
import telran.net.TcpClientJava;

public class TcpClient extends TcpClientJava {

	public TcpClient(String hostname, int port) {
		super(hostname, port);
		
	}

	public String send(String type, String line) {
		return sendRequest(type, line).toString();
	}
}
