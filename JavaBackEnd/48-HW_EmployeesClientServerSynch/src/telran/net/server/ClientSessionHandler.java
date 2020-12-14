package telran.net.server;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.concurrent.atomic.AtomicBoolean;

import telran.net.common.Protocol;
import telran.net.common.ProtocolRequest;
import telran.net.common.ProtocolResponse;

public class ClientSessionHandler implements Runnable {
	final Protocol protocol;
	final Socket socket;
	AtomicBoolean isStoped = new AtomicBoolean(false);
	public ClientSessionHandler(Socket socket, Protocol protocol, AtomicBoolean isStoped) {
		super();
		this.socket = socket;
		this.protocol = protocol;
		this.isStoped = isStoped;
		try {
			socket.setSoTimeout(10000);
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		try (socket;
				ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
				ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream())){
			while (!isStoped.get()) {
				ProtocolRequest request = (ProtocolRequest) input.readObject();
				ProtocolResponse response = protocol.getResponse(request);
				output.writeObject(response);
				output.reset();
			}
		} catch (SocketTimeoutException e) {
			System.out.println("Client disconected by timeout");
		} 
		
		catch (EOFException e) {
			System.out.println("Client closed connection");
			System.out.println("Disconnected client:" + socket.getRemoteSocketAddress());
		} catch (IOException e) {
			System.out.println("Client closed connection abnormally:" + e.getMessage());
			System.out.println("Disconnected client:" + socket.getRemoteSocketAddress());
		} catch (Exception e) {
			System.out.println("Unexpected exception: " + e.getMessage());
			System.out.println("Disconnected client:" + socket.getRemoteSocketAddress());
			//e.printStackTrace();
		}
		
	}
}
