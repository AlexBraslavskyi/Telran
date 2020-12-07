package telran.net.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;

import telran.net.common.*;

public class TcpServer implements Runnable {
	int port;
	Protocol protocol;
	ServerSocket serverSocket;

	public TcpServer(int port, Protocol protocol) {
		super();
		this.port = port;
		this.protocol = protocol;
		try {
			serverSocket = new ServerSocket(port);
		} catch (IOException e) {
			throw new RuntimeException("Port in use " + port);
		}
	}

	@Override
	public void run() {
		System.out.println("server is listened on port " + port);
		SocketAddress clientAddr = null;
		while (true) {
			try {
				Socket socket = serverSocket.accept();
				clientAddr = socket.getRemoteSocketAddress();
				System.out.println("Connected client:" + clientAddr);
				ClientSessionHandler client = new ClientSessionHandler(socket, protocol);
				//client.run();
				new Thread(client).start();
			} catch (IOException e) {
				e.printStackTrace();
			}			
		}		
	};
}
