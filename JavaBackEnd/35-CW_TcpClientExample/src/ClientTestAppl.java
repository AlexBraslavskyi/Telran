import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;



public class ClientTestAppl {
	
	static final String HOST = "localhost";
	static final int PORT = 5000;

	public static void main(String[] args) throws Exception {
		try (Socket socket = new Socket(HOST, PORT);
				BufferedReader consolReader = new BufferedReader(new InputStreamReader(System.in));
				BufferedReader sockerReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				PrintStream sockerWriter = new PrintStream(socket.getOutputStream())) {
			while(true) {
				String line = null;
				String socketLine = "";
				System.out.println("Enter request type (length or reverse) or quit");
				line = consolReader.readLine();
				if(line.equalsIgnoreCase("quit")) {
					break;
				}
				socketLine = line + "#";
				System.out.println("enter  any string");
				line = consolReader.readLine();
				socketLine +=line;
				sockerWriter.println(socketLine);
				line = sockerReader.readLine(); //ansver from server
				System.out.println(line);
			}
		}

	}
;
}
