package telran.games.impl;

import telran.games.interfaces.GuessGame;
import java.net.*;
import java.io.*;
public class GameTcpProxy implements GuessGame {
	public GameTcpProxy(String host, int port) {
		
		this.host = host;
		this.port = port;
	}

	Socket socket;
	BufferedReader reader;
	PrintStream writer;
	String host;
	int port;
	

	@Override
	public String startGame() {
		try {
			socket = new Socket(host, port);
			writer = new PrintStream(socket.getOutputStream());
			reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			return getResponse("start", "");
		} catch (UnknownHostException e) {
			throw new RuntimeException("Start game...unknown host " + host);
		} catch (IOException e) {
			throw new RuntimeException("Start game..." + e.getMessage());
		}
	}

	@Override
	public String prompt() {
		return getResponse("prompt","");
	}

	private String getResponse(String headers, String payload ) {
		writer.println(headers + "#" + payload);
		try {
			String res = reader.readLine();
			if(res.contains("Unknown")) {
				throw new RuntimeException(headers + "..." + "Unknown request");
			}
			return res;
		} catch (IOException e) {
			throw new RuntimeException(headers + "..." + e.getMessage());
		}
	}

	@Override
	public String move(String userInput) {
		
		return getResponse("move", userInput);
	}

	@Override
	public boolean isFinished() {
		String response = getResponse("is_finished", "");
		boolean res = response.equals("yes");
		if (res) {
			try {
				socket.close();
			} catch (IOException e) {
				throw new RuntimeException("Closing socket..." + e.getMessage());
			}
		}
		return res;
	}

}
