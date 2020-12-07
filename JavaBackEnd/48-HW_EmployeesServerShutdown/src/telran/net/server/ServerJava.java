package telran.net.server;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
public class ServerJava implements Runnable {
ServerSocket serverSocket;
int port;
ProtocolJava protocol;

public ServerJava(int port, ProtocolJava protocol) {
	
	this.protocol = protocol;
	this.port = port;
	try {
		serverSocket = new ServerSocket(port);

	} catch (IOException e) {
		throw new RuntimeException("Port in use " + port);
	}
//	try {
//		serverSocket.setSoTimeout(10000);
//	} catch (SocketException e) {
//		stop();
//		throw new RuntimeException("Timeout finished on port " + port);
//		
//	}
}
	@Override
	public void run() {
		System.out.println("Server is listening on port " + port);
		try {
			Socket socket;
			while(true) {
				socket = serverSocket.accept();
				ServerClientJava client = new ServerClientJava(socket, protocol);
				new Thread(client).start();
				
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		if()
	      //close the ServerSocket object
        try {
			serverSocket.close();;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void stop() {
//if()
	System.out.println("Server stoped on port " + port);
	try {
		serverSocket.close();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
//}
}
}
