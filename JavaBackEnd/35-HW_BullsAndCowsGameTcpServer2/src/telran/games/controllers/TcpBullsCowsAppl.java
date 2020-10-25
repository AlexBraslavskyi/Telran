package telran.games.controllers;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.*;
import telran.games.impl.BullsCowsGameImpl;
import telran.games.interfaces.GuessGame;

public class TcpBullsCowsAppl {
private static final int PORT = 4000;
public static void main(String[] args) throws IOException {
	ServerSocket serverSocket = new ServerSocket(PORT);
	GuessGame game = new BullsCowsGameImpl();
	System.out.println("Server is listening on port " + PORT);
	while(true) {
		Socket socket = serverSocket.accept();
		runClient(game, socket);
	}
}
private static void runClient(GuessGame game, Socket socket) {
	try(BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintStream writer = new PrintStream(socket.getOutputStream())) {
		while(true) {
			String request = reader.readLine();
			if(request == null) {
				break;
			}
			writer.println(getResponse(game, request));
		}
	} catch(Exception e) {
		System.out.println("illegal client connection closing");
		return;
	}
	System.out.println("client connection closed");
	
	
}
private static String getResponse(GuessGame game, String request) {
	String headersPayload[] = request.split("#");
	
	switch(headersPayload[0]) {
	case "start": return game.startGame();
	case "prompt": return game.prompt();
	case "move": return game.move(headersPayload[1]);
	case "is_finished": return game.isFinished() ? "yes" : "no";
	default: return "Unknown request";
	}
}

}
