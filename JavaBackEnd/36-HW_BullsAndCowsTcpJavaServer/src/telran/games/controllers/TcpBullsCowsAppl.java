package telran.games.controllers;

import java.io.IOException;

import telran.net.server.ServerJava;

public class TcpBullsCowsAppl {
private static final int PORT = 4000;
public static void main(String[] args) throws IOException {
	
	ServerJava serverJava = new ServerJava(PORT, new Protocol());
	serverJava.run();

}

}
