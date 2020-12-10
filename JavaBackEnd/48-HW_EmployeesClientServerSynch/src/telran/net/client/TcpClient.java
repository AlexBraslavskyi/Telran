package telran.net.client;

import java.io.Closeable;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;

import telran.net.common.ProtocolRequest;
import telran.net.common.ProtocolResponse;
import telran.net.common.ResponseCode;


public class TcpClient implements Closeable {
	ObjectOutputStream output; 
	ObjectInputStream input; 
	Socket socket;
	protected TcpClient(String hostname, int port) {
		try {
			socket = new Socket(hostname, port);
			socket.setSoTimeout(50000);
			output = new ObjectOutputStream(socket.getOutputStream());
			input = new ObjectInputStream(socket.getInputStream());
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);			
		}
	}
	@Override
	public void close() throws IOException {
		input.close();
		output.close();
		socket.close();		
	}
	
	@SuppressWarnings("unchecked")
	protected <T> T sendRequest(String requestType, Serializable requestData)	{
		ProtocolRequest request = new ProtocolRequest(requestType, requestData);
		try {
			output.writeObject(request);
			output.reset();
			ProtocolResponse response = (ProtocolResponse) input.readObject();
			if(response.code!=ResponseCode.OK) {
				throw new Exception(response.responseData.toString());
			}
			return (T) response.responseData;
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		
	}
}
