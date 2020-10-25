package telran.games.controllers;

import java.io.IOException;
import java.util.Scanner;

import telran.games.impl.TcpClient;

public class TcpGameConsoleAppl {
static  final String HOST = "localhost";
static final int PORT = 4000;
	public static void main(String[] args) throws Exception{
		boolean isTest = args.length > 0 && args[0].contentEquals("test");
		Scanner scan = new Scanner(System.in);
		TcpClient client = new TcpClient(HOST, PORT);
		System.out.println(runGames(isTest, scan, client));
		
	}		

	private static String runGames(boolean isTest, Scanner scan, TcpClient client) {
		String line;
		String res = "";
		try {
			while(true) {
				System.out.println("Are you ready to start new game? yes/no");
				line = scan.nextLine();
				if (!line.equalsIgnoreCase("yes")) {
					client.close();
					break;
				}
				String secretNumber = client.send("start", "");;
				if (isTest) {
					System.out.println(secretNumber);
				}
				runGame(scan, client);
				
			}
		} catch (Exception e) {
			res = e.getMessage();
		}
		return res;
	}
	private static void runGame(Scanner scanner, TcpClient client) throws IOException {
		
		while(true) {
			System.out.println(client.send("prompt",""));
			System.out.println(client.send("move", scanner.nextLine()));
			if((client.send("is_finished", "")).equalsIgnoreCase("yes")) {
				break;
			}
			
		}
		
	}

}
