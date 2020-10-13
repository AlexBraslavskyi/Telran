import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;



public class InputLineStreamTestAppl {

	public static void main(String[] args) throws IOException {

//		1-st not effective
//FileSystem fs = FileSystems.getDefault();
//Files.lines(fs.getPath("file.txt")).forEach(System.out::println);
		
		
		//2-nd most effective way reed
//		try(BufferedReader reader = new BufferedReader(new FileReader("file.txt"));) {
//
//			String line;
//			while(true) {
//				line = reader.readLine();
//				if(line == null) { //end of file
//					break;
//				}
//				System.out.println(line);
//			}
//	}

		//consol 
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		while(true) {
			String line = reader.readLine();
			if(line.equalsIgnoreCase("exit")) {
		
			break;
			}
			System.out.println("length "+line.length());
		}
		
		
		
}
}
