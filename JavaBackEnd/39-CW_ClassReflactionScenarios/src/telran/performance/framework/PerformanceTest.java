package telran.performance.framework;

import java.lang.reflect.Method;
import java.time.Instant;
import java.time.temporal.ChronoUnit;



public class PerformanceTest {

	
	static void runTest(Object obj, String testFn, int nRuns) throws Exception{
		Class <?> clazz = obj.getClass();
		Method method = clazz.getDeclaredMethod(testFn, int.class);//response.class
		method.setAccessible(true);
		Instant start = Instant.now();
		method.invoke(obj, nRuns);//this
		System.out.printf("Runing time of method %s is %d number of runs is %d\n",
				testFn, ChronoUnit.MILLIS.between(start, Instant.now()),nRuns);
		
	}
}
