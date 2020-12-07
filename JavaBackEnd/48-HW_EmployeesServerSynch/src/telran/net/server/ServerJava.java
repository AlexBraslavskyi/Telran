package telran.net.server;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
public class ServerJava implements Runnable {
ServerSocket serverSocket;
int port;
ProtocolJava protocol;
ExecutorService executor;
public ServerJava(int port, ProtocolJava protocol) {
	this.protocol = protocol;
	this.port = port;

		try {
			serverSocket = new ServerSocket(port);
		} catch (IOException e) {
			throw new RuntimeException("Port in use " + port + e.getMessage());
		}
		executor = Executors.newFixedThreadPool(10);
		
		
}
	@Override
	public void run() {
		System.out.println("Server is listening on port " + port);
		try {
//			int timeout = 15000;
			while(true) {
				Socket socket = serverSocket.accept();
				ServerClientJava client = new ServerClientJava(socket, protocol);
		
//				serverSocket.setSoTimeout(timeout);
//				socket.setSoTimeout(timeout);
				executor.execute(client);
			}
		} catch (IOException e) {
			stop();
//			e.printStackTrace();
		}

	}

	public void stop() {
		System.out.println("Server stoped");
		executor.shutdown();

	}

}
