package telran.net.server;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import telran.net.RequestJava;
import telran.net.ResponseJava;
public class ServerClientJava implements Runnable {

	final ProtocolJava protocol;
	final Socket socket;

	public ServerClientJava(Socket socket, ProtocolJava protocol) {
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
				RequestJava request = (RequestJava) input.readObject();
				ResponseJava response = protocol.getResponse(request);
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
//	try {
//		input = new ObjectInputStream(socket.getInputStream());
//		output = new ObjectOutputStream(socket.getOutputStream());
//		this.protocol = protocol;
//		
//	} catch (IOException e) {
//		
//		e.printStackTrace();
//	}
//	
//}
//	@Override
//	public void run() {
//		try {
//			while(true) {
//				RequestJava request = (RequestJava) input.readObject();
//				ResponseJava response = protocol.getResponse(request);
//				output.writeObject(response);
//			}
//		} catch (EOFException e) {
//			System.out.println("client closed connection");
//		} catch(Exception e) {
//			System.out.println("client abnormally closed connection " + e.getMessage());
//		}
//
//	}

