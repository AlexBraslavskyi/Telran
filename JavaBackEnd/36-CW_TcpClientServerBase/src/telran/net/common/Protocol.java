package telran.net.common;


public interface Protocol {
	ProtocolResponse handleRequest(ProtocolRequest request);
}
