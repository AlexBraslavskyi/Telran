package telran.net.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

import telran.net.common.Protocol;

public class TcpServer implements Runnable {
	int port;
	Protocol protocol;
	ServerSocket serverSocket;
	AtomicBoolean isStoped = new AtomicBoolean(false);
	ExecutorService executor;
	public TcpServer(int port, Protocol protocol) {
		super();
		this.port = port;
		this.protocol = protocol;
		try {
			serverSocket = new ServerSocket(port);
			serverSocket.setSoTimeout(10000);
			executor = Executors.newFixedThreadPool(10);
		} catch (IOException e) {
			throw new RuntimeException("Port in use " + port);
		}
	}

	public void stopedFlag(boolean stop){
		isStoped.set(stop);
		
	}
	@Override
	public void run() {
		System.out.println("server is listened on port " + port);
		while (!isStoped.get()) {
			try {
				Socket socket = serverSocket.accept();
				ClientSessionHandler client = new ClientSessionHandler(socket, protocol, isStoped);
				executor.execute(client);
			} catch (SocketTimeoutException e) {
				if(isStoped.get()) {
					try {
						executor.shutdownNow();
						serverSocket.close();
//						System.exit(0);
					}catch(IOException e1){
						e.printStackTrace();
					}
					break;
				}
			}catch(IOException e){
					e.printStackTrace();
				}
					
		}
	}
}