package telran.net;

import java.io.Closeable;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
public class TcpClientJava implements Closeable {
	ObjectOutputStream output;
	ObjectInputStream input;
	Socket socket;
protected TcpClientJava(String hostname, int port) {
	try {
		socket = new Socket(hostname, port);
		output = new ObjectOutputStream(socket.getOutputStream());
		input = new ObjectInputStream(socket.getInputStream());
	} catch (IOException e) {
		
		e.printStackTrace();
	}
}
	@Override
	public void close() throws IOException {
		socket.close();

	}
	@SuppressWarnings("unchecked")
	protected <T> T sendRequest(String requestType, Serializable requestData) {
		RequestJava request = new RequestJava(requestType, requestData);
		try {
			output.writeObject(request);
			ResponseJava response = (ResponseJava) input.readObject();
			if(response.code == TcpResponseCode.EXIT) {
//				close();
			}
			if (response.code != TcpResponseCode.OK) {
				throw new Exception(response.responseData.toString());
			}
			return (T) response.responseData;
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
		
		
	}

}
