package telran.performance;
import java.io.IOException;
import java.io.PrintStream;
public class PrintStreamWriteFile implements IWriteFile {

	@Override
	public void writeToFile(long nLines) {
		
		try(PrintStream ps = new PrintStream("print_stream.txt")){
			for(long i = 0; i< nLines; i++) {
				ps.println("Hello");
			}
			
		}catch(IOException e) {
			System.out.println(e.getMessage());
		}

	}

}
