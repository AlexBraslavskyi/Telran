import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.Random;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class GemeCowsAndBulls {

	private static boolean exit = true;
	static PrintStream resultTemp;
	static PrintStream resultStatistics;
	String sourceFileName = "src/result.txt";
	  String destinationFileName = "D:\\Test Folder\\Test File 1.txt";
	public static void main(String[] args) {
		try {
			resultTemp = new PrintStream("src/result.txt");
		} catch (FileNotFoundException e) {
		System.out.println(e.getMessage());
		}
		try {
			runGame();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

	}

	private static void runGame() throws IOException {
		Pattern regex = Pattern.compile("^\\d{4}$|^quit$");
		String random = new Random().ints(0, 10).distinct().limit(4)
				.collect(StringBuilder::new, StringBuilder::append, StringBuilder::append).toString();

		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

		while (exit) {
			System.out.print("Please enter a 4-digit number (or type 'quit' to exit):");
			String input = reader.readLine();
			if (!regex.matcher(input).find()) {
				String message = "You entered not correct number";
				resultTemp.println(message);
				System.out.println(message);
				continue;
			}
			if (input.equalsIgnoreCase("quit")) {
				resultTemp.println("Exit");
				break;
			}
			String result = getResult(random, input);
				resultTemp.println("You enter "+input + " (Random number - " + random + ")");
				resultTemp.println(result);
			System.out.println(result);

		}
		Stream<String> lines = Files.lines(Path.of("src/result.txt"), Charset.defaultCharset());
			  long numOfLines = lines.count();
	
			
		File fileStatistics = new File("src/" + LocalDateTime.now().toString() +"-"+ numOfLines+".txt");
		System.out.println(fileStatistics.getAbsolutePath());
		resultStatistics =  new PrintStream(fileStatistics);
		copyFile("src/result.txt", resultStatistics);
	}

	public static String getResult(String random, String input) {
		int bulls = 0;
		int cows = 0;
		if (random == input) {
			return "You win, your number correct!!!";
		}

		int[] randArr = new int[10];
		int[] inputArr = new int[10];


		for (int i = 0; i < random.length(); i++) {
			
			// get the current characters
			char charRand = random.charAt(i);
			char charInput = input.charAt(i);

			if (charRand == charInput)
				bulls++;
			else {
				// otherwise increment our arrays
				randArr[charRand - '0']++;
				inputArr[charInput - '0']++;
			}
		}

	
		for (int i = 0; i < 10; i++) {

			cows += Math.min(randArr[i], inputArr[i]);
		}

		if (bulls == 4) {
			exit  = false;
			return "You win, your number correct!!!";
		}
		return "Your result " + bulls + " Bulls " + cows + " Cows ";
	}
	 private static void copyFile(String sourceFileName,PrintStream ps) { 

	      BufferedReader br = null;

	      try {
	          br = new BufferedReader(new FileReader( sourceFileName ));
	 

	          String line;
	          while ((line = br.readLine()) != null) {
	              ps.println(line);
	          }

	          br.close();
	          ps.close();
	      }catch (Exception e) {
		  e.printStackTrace();
	      }
}
}
