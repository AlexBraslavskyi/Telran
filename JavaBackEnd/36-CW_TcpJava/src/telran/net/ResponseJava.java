package telran.net;

import java.io.Serializable;

public class ResponseJava implements Serializable{

	public TcpResponseCode code;
	public Serializable responseData;
	
	private static final long serialVersionUID = 1L;

	public ResponseJava(TcpResponseCode code, Serializable responseData) {
		super();
		this.code = code;
		this.responseData = responseData;
	}

	
}
