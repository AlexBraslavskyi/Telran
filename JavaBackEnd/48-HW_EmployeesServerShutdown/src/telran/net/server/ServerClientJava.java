package telran.net.server;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import telran.net.RequestJava;
import telran.net.ResponseJava;
import telran.net.TcpResponseCode;
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
		RequestJava request = null;
		try (socket;
				ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
				ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream())){
			
			while (true) {
				request = (RequestJava) input.readObject();
				ResponseJava response = protocol.getResponse(request);
				if(response.code==TcpResponseCode.SERVER_NOT_AVAILABLE) {
					socket.close();
				}
				System.out.println("Server stoped");
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
//		System.out.println(request.requestType);
//		System.out.println(request.requestData);
//		if(((String) request.requestData).equalsIgnoreCase("Yes")) {
//			System.out.println("Server stoped");
//			try {
//				socket.close();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
		System.out.println("Disconnected client:" + socket.getRemoteSocketAddress());
	}
}


