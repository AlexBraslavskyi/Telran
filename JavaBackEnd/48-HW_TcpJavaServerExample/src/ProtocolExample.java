import java.io.Serializable;

import telran.net.RequestJava;
import telran.net.ResponseJava;
import telran.net.TcpResponseCode;
import telran.net.server.ProtocolJava;

public class ProtocolExample implements ProtocolJava {

	@Override
	public ResponseJava getResponse(RequestJava request) {
	try {
		switch (request.requestType) {
		case "length": return new ResponseJava(TcpResponseCode.OK, ((String)request.requestData).length());
		case "reverse": return new ResponseJava(TcpResponseCode.OK, getReverse((String)request.requestData));
		default: throw new Exception("Unknown Request" + request.requestType);
		}
	} catch (Exception e) {
		return  new ResponseJava(TcpResponseCode.WRONG_REQUEST, e.getMessage());
	}

	}

	private Serializable getReverse(String requestData) {
	StringBuilder builder = new StringBuilder(requestData);
	
		return builder.reverse().toString();
	}

}
