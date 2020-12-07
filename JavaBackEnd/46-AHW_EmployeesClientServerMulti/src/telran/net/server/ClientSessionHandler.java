package telran.net.server;

import java.io.*;
import java.net.*;

import telran.net.common.*;

public class ClientSessionHandler implements Runnable {
	final Protocol protocol;
	final Socket socket;

	public ClientSessionHandler(Socket socket, Protocol protocol) {
		super();
		this.socket = socket;
		this.protocol = protocol;

	}

	@Override
	public void run() {
		try (socket;
				ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
				ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream())){
			while (true) {
				ProtocolRequest request = (ProtocolRequest) input.readObject();
				ProtocolResponse response = protocol.getResponse(request);
				output.writeObject(response);
				output.reset();
			}
		} catch (EOFException e) {
			System.out.println("Client closed connection");
		} catch (IOException e) {
			System.out.println("Client closed connection abnormally:" + e.getMessage());
		} catch (Exception e) {
			System.out.println("Unexpected exception: " + e.getMessage());
			//e.printStackTrace();
		}
		System.out.println("Disconnected client:" + socket.getRemoteSocketAddress());
	}
}
