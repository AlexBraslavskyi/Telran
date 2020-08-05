
public class JavaMemoryAllocationTestAppl {

	public static void main(String[] args) {
	
		Runtime runtime = Runtime.getRuntime();
		
//		Runtime runtime1 = Runtime.getRuntime();
//		System.out.println(runtime == runtime1);

		System.out.printf("JVM requests from OS: %d;\n how much free memory: %d; \n"
				+ "how much total memory: %d; ", 
				runtime.totalMemory(), runtime.freeMemory(), runtime.maxMemory());
	}

}
 