package telran.performance.framework;

public class PerformanceTestAppl {

	/**
	 * 
	 * @param args first - class name, second - func name, third - nRuns ....
	 */
	public static void main(String[] args) throws Exception{
if(args.length < 3) {
	System.out.println("Usage: rgs first - class name, second - func name, third - nRuns");
	return;
}
		Object obj = Class.forName(args[0]).getConstructor().newInstance();
		for(int i = 1; i < args.length; i+=2) {
			PerformanceTest.runTest(obj, args[i], Integer.parseInt(args[i+1]));
		}
	}

}
