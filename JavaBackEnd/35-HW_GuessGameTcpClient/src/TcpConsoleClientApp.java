import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class TcpConsoleClientApp{
	static final String host = "localhost";
	static final int port = 5000;
	

	public static void main(String[] args) throws Exception{
		try (Socket socket = new Socket(host, port);
				BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
				BufferedReader socketReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				PrintStream socketWriter = new PrintStream(socket.getOutputStream())) {
			while (true) {
				String line= null;
				String socketLine = "";
				socketLine = line + "#";
				System.out.println("Please enter a 4-digit number (or type 'quit' to exit):");
				line = consoleReader.readLine();
				if (line.equalsIgnoreCase("quit")) {
					break; 
				}
				socketLine += line;
				socketWriter.println(socketLine);
				line = socketReader.readLine();
				System.out.println(line);
				
				
			}
		}
	}
}
