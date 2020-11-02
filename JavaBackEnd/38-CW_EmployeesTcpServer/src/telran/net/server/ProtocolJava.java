package telran.net.server;
import telran.net.RequestJava;
import telran.net.ResponseJava;
public interface ProtocolJava {

	ResponseJava getResponse(RequestJava request);
}
