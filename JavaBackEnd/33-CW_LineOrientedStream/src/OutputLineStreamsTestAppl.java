import java.io.IOException;

public class OutputLineStreamsTestAppl {

	private static final long N_LINES = 10_000_000;

	public static void main(String[] args) throws IOException {
//	PrintStream ps = new PrintStream("print_stream.txt");
//	PrintWriter pw = new PrintWriter("print_writer.txt");
//	ps.println("Hello PrintStream");
//	pw.println("Hello PrintWriter");//sent to buffer
//	pw.close();
//	ps.close();
	
		//2-nd way
//	try(PrintStream ps = new PrintStream("print_stream.txt");
//			PrintWriter pw = new PrintWriter("print_writer.txt")){
//		ps.println("Hello PrintStream!!!");
//		pw.println("Hello PrintWriter!!!");
//	}
	
//		PerformanceTest testWriter = new PerformanceWriteFileTest("write test", 1 , N_LINES, new PrintWriterFile());
//		PerformanceTest testStream = new PerformanceWriteFileTest("stream test", 1, N_LINES, new PrintStreamWriteFile());
//		
//		testWriter.run();
//		testStream.run();
		
		
		
	}
}
