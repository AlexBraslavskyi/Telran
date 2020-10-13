package telran.performance;
import java.io.IOException;
import java.io.PrintWriter;
public class PrintWriterFile implements IWriteFile {

	@Override
	public void writeToFile(long nLines) {
		
		try(PrintWriter pw = new PrintWriter("print_stream.txt")){
			for(long i = 0; i< nLines; i++) {
				pw.println("Hello");
			}
			
		}catch(IOException e) {
			System.out.println(e.getMessage());
		}

	}

}
