package telran.games.controller;

import java.io.*;
import java.util.*;

import telran.games.BullsAndCowsGame;

import java.time.*;
import java.time.format.DateTimeFormatter;

public class BullsAndCowsAppl {
	static boolean flTest;
	private static final String FILE_NAME_PATTERN = "yyyy-MM-dd_HH_mm";
	static private Integer bullsCows[] = { 0, 0 };
	// bullsCows[0]-number of bulls,bullsCows[1]-number of cows
	static private List<Move> moves = new ArrayList<>();
	static private int count = 0;
	static private BullsAndCowsGame game;
	static BufferedReader reader;
	public static void main(String[] args) throws IOException {
		 flTest = args.length > 0&&args[0].equalsIgnoreCase("test");
		 reader=new BufferedReader(new InputStreamReader(System.in));
		while(startGame()) {}
	}
	private static boolean startGame() throws IOException {
		game=new BullsAndCowsGame();
		if(flTest)
			System.out.println(Arrays.deepToString(game.getNumber()));
		count=0;
		moves.clear();
		runGame();
		System.out.println("Do you want to start new game? Y/N");
		String response=reader.readLine();
		return response.equalsIgnoreCase("Y");
	}
	static private void runGame() throws IOException {
		while(true){
			System.out.println("Enter guess number (4 unrepeated digits) "
					+ "from 1 to 9");
		String line=reader.readLine();
		
		if(!moves.isEmpty()){
			moves.forEach(System.out::println);
		}
		count++;
		bullsCows=game.getMoveResult(line);
		nextMove(line);
		if(bullsCows[0]==4){
			finishGame();
			break;
			}
		}
	}
	static private  void nextMove(String guessNumber) {
		Move move=new Move(bullsCows, guessNumber);
		moves.add(move);
		System.out.println(move);
	}
	static private  void finishGame() {
		System.out.println("Congratulations you have guessed number for "+count+" moves");
		try(PrintWriter writer=new PrintWriter(getGameFileName())){
			moves.forEach(writer::println);
		}catch(IOException e){
			System.out.println
			("File with game history can't be created "
			+e.getMessage());
		}
	}
	private static String getGameFileName() {
		LocalDateTime ldt=LocalDateTime.now();
		DateTimeFormatter dtf=DateTimeFormatter.ofPattern(FILE_NAME_PATTERN);
		return ldt.format(dtf)+"_"+count;
	}
}
