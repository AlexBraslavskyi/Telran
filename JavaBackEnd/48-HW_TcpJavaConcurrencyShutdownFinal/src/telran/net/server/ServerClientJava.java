package telran.net.server;
import java.net.*;
import java.util.concurrent.atomic.AtomicBoolean;

import telran.net.*;

import java.io.*;
public class ServerClientJava implements Runnable {
ObjectInputStream input;
ObjectOutputStream output;
ProtocolJava protocol;
static AtomicBoolean shutdown = new AtomicBoolean(false);
public ServerClientJava(Socket socket, ProtocolJava protocol) {
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
			while(!shutdown.get()) {
				try {
					RequestJava request = (RequestJava) input.readObject();
					ResponseJava response = protocol.getResponse(request);
					output.writeObject(response);
				} catch(SocketTimeoutException e) {
			//continue
		}
			}
			
		} 
		catch (EOFException e) {
			System.out.println("client closed connection");
		} catch(Exception e) {
			System.out.println("client abnormally closed connection " + e.getMessage());
		}

	}

}
