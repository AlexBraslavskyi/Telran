package telran.net.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerJava implements Runnable {

	int port;
	ServerSocket serverSocket;
	ProtocolJava protocol;

	public ServerJava(int port, ProtocolJava protocol) {
		this.protocol = protocol;
		this.port = port;
		try {
			serverSocket = new ServerSocket(port);
		} catch (IOException e) {
			throw new RuntimeException("Port in use " + port);
		}
	}

	@Override
	public void run() {
		System.out.println("Server is listening on port " + port);
		try {
			while (true) {
					Socket socket = serverSocket.accept();
					ServerClientJava client = new ServerClientJava(socket, protocol);
					client.run();//
					
//					Thread thread = new Thread(client);
//					thread.start();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
