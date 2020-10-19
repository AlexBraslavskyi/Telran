import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;



public class ServerTestAppl {

	private static final int PORT = 5000;

	public static void main(String[] args) throws Exception {
		
		
	try (ServerSocket serverSocket = new ServerSocket(PORT)) {
		System.out.println("Server is listening on port " + PORT);
		
		while(true) {
			Socket socket = serverSocket.accept();
			runClient(socket);
			
		}
	}

	}

	private static void runClient(Socket socket) throws Exception {
	
BufferedReader readerFromSocket = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	
	PrintStream writerToSocket = new PrintStream(socket.getOutputStream());
		
	while(true) {
		String line = "";
		try {
			line = readerFromSocket.readLine();
			if(line == null) {
				System.out.println("Client closed connection");
				break;
			}
			
		} catch (IOException e) {
			System.out.println("Client abnormally closed connection");
			break;
		}
		line = getRequest(line);
		writerToSocket.println(line);
		
		
	}
		
	}

	private static String getRequest(String line) {
		//<request type>#<string>
		String [] tokens = line.split("#");
		if(tokens.length!=2) {
			return "Wrong Request";
		}
		
		switch (tokens[0]) {
		case "length" : return tokens[1].length() + "";
		case "reverse" : return getReverse(tokens[1]);
		default: return "Wrong request type";
		}
	
	}

	private static String getReverse(String string) {
	StringBuilder builder = new StringBuilder(string);
	
		return builder.reverse().toString();
	}	

}
