import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;
import java.util.regex.Pattern;

public class TcpBullsCowsApp extends BullsCowsGameImpl{
	private static final int PORT = 5000;
	static Pattern regex = Pattern.compile("^[1-9]{4}$|^quit$");
	static PrintStream writerToSocket;
	static BufferedReader reader;
	static boolean flTest;
	static BullsCowsGameImpl b = new BullsCowsGameImpl();
public static void main(String[] args) throws Exception {

	flTest = args.length > 0&&args[0].equalsIgnoreCase("test");
	String random = new Random().ints(1, 10).distinct().limit(4)
			.collect(StringBuilder::new, StringBuilder::append, StringBuilder::append).toString();
	
		try (ServerSocket serverSocket = new ServerSocket(PORT)) {
			System.out.println("Server is listening on the port " + PORT);
			while(true) {
				Socket socket = serverSocket.accept();
				runClient(socket);
			}
		}

	}

	private static void runClient(Socket socket) throws Exception {

		
		
		reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		writerToSocket = new PrintStream(socket.getOutputStream());
	
		
		while(true) {
			
			String line = null;
			try {
				line = reader.readLine();
				if (line == null) {
					System.out.println("client closed connection");
					break;
				}
//				line = reader.readLine();
//				if (line == "You win, your number correct!!!") {
//					System.out.println(line);
//					break;
//				}
			} catch (IOException e) {
				System.out.println("client abnormally closed connection");
				break;
			}
			line = getRequest(line);
			writerToSocket.println(line);
		}
		
		
	}

	private static String getRequest(String line) {
		
		String tokens[] = line.split("#");
		if(tokens.length != 2) {
			return "Wrong Request";
		}
		if (!regex.matcher(tokens[1]).find()) {
			String message = 
					"You entered not correct number";
			writerToSocket.println(message);
		}
	return BullsCowsGameImpl.getResult(tokens[1],flTest);
		
		}
}
