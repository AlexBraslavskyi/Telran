package telran.net.server;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.concurrent.atomic.AtomicBoolean;

import telran.net.RequestJava;
import telran.net.ResponseJava;
public class ServerClientJava implements Runnable {

	final ProtocolJava protocol;
	final Socket socket;
	AtomicBoolean stopedFlag = new AtomicBoolean(false);

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
				if(request.requestData.toString().equalsIgnoreCase("EXIT")) {
					stopedFlag.getAndSet(true);
//					socket.setSoTimeout(1);
					
				}
				
				output.writeObject(response);
				output.reset();
			}
		} catch (EOFException e) {
			System.out.println("Client closed connection");
		} catch (SocketTimeoutException e) {
			System.out.println("Client stoped server:" + e.getMessage());
			
		} catch (IOException e) {
			System.out.println("Client closed connection abnormally:" + e.getMessage());
		} catch (Exception e) {
			System.out.println("Unexpected exception: " + e.getMessage());
			//e.printStackTrace();
		}
	
		System.out.println("Disconnected client:" + socket.getRemoteSocketAddress());
	}
boolean isStoped() {
	return stopedFlag.get();
	
}
}


