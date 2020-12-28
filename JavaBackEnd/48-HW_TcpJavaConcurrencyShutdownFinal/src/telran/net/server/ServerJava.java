package telran.net.server;
import java.io.IOException;
import java.net.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
public class ServerJava implements Runnable {
private static final int N_THREADS = 4;
ServerSocket serverSocket;
int port;
boolean shutdown = false;
ProtocolJava protocol;
private  int timeout = 3000;
ExecutorService executor;
public int getTimeout() {
	return timeout;
}
public void setTimeout(int timeout) {
	this.timeout = timeout;
}
public ServerJava(int port, ProtocolJava protocol) {
	this(port, protocol,N_THREADS);
}
public ServerJava(int port, ProtocolJava protocol, int nThreads) {
	this.protocol = protocol;
	this.port = port;
	executor = Executors.newFixedThreadPool(nThreads);
	try {
		serverSocket = new ServerSocket(port);
		serverSocket.setSoTimeout(timeout );
	} catch (IOException e) {
		throw new RuntimeException("Port in use " + port);
	}
}
public void stop() {
	executor.shutdown();
	shutdown = true;
	ServerClientJava.shutdown.set(true);
}
	@Override
	public void run() {
		System.out.println("Server is listening on port " + port);
		
		
		try {
			while(!shutdown) {
				try {
					Socket socket = serverSocket.accept();
					socket.setSoTimeout(timeout);
					ServerClientJava client = new ServerClientJava(socket, protocol);
					executor.execute(client);
				}  catch(SocketTimeoutException e) {
			//continue
		}
			}
			
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
