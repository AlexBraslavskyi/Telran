package telran.net.server;

import java.io.IOException;
import java.net.*;

import telran.net.common.Protocol;

public class TcpServer implements Runnable {
	ServerSocket serverSocket;
	int port;
	Protocol protocol;

	public TcpServer(int port, Protocol protocol) {
		this.protocol = protocol;
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
				try (Socket socket = serverSocket.accept();) {
					ClientSessionHandler client = new ClientSessionHandler(socket, protocol);
					client.run();
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
