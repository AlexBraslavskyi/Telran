package telran.net.server;

import java.net.*;

import telran.net.common.Protocol;
import telran.net.common.ProtocolRequest;
import telran.net.common.ProtocolResponse;

import java.io.*;

public class ClientSessionHandler implements Runnable {
	ObjectInputStream input;
	ObjectOutputStream output;
	Protocol protocol;

	public ClientSessionHandler(Socket socket, Protocol protocol) {
		try {
			input = new ObjectInputStream(socket.getInputStream());
			output = new ObjectOutputStream(socket.getOutputStream());
			this.protocol = protocol;

		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	@Override
	public void run() {
		try {
			while (true) {
				ProtocolRequest request = (ProtocolRequest) input.readObject();
				ProtocolResponse response = protocol.handleRequest(request);
				output.writeObject(response);
				output.reset();
			}
		} catch (EOFException e) {
			System.out.println("client closed connection");
		} catch (Exception e) {
			System.out.println("client abnormally closed connection " + e.getMessage());
		}

	}

}
