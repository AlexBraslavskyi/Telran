package telran.performance.framework;

import java.lang.reflect.Method;

import telran.performance.framework.annotations.Test;

public class PerformanceTestAppl {

	/**
	 * 
	 * @param args first and single- class name
	 */
	public static void main(String[] args) throws Exception{
if(args.length !=1) {
	System.out.println("Usage: args - class name");
	return;
}
Class<?> clazz = Class.forName(args[0]);
		Object obj = Class.forName(args[0]).getConstructor().newInstance();
		
		Method[] methods = clazz.getDeclaredMethods();
		for(Method method:methods) {
			Test testAnnotation = method.getAnnotation(Test.class);
			if(testAnnotation != null) {
				PerformanceTest.runTest(obj, method, testAnnotation.nRuns());
			}
		}
	}

}
