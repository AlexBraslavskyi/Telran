
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.TreeSet;

import telran.performance.PerformanceTest;
public class HashSetTestAppl {

	private static final int N_Runs = 10000;
	private static final int N_Elements = 100000;

	public static void main(String[] args) {
		Integer ar[] = {10,100,12,11,20,17};
		//Iterating HashSet according to hashCode %16
//		Set <Integer> set = new HashSet<Integer>(Arrays.asList(ar));
		//Iterating LinkedHashSet according to the order
//		Set <Integer> set = new LinkedHashSet<Integer>(Arrays.asList(ar));
//		set.forEach(System.out::println);
		
		
		PerformanceTest testHashSet = new CollectionContainsPerformanceTest("HashSet", N_Runs, new HashSet<Integer>(), N_Elements);
		PerformanceTest testLinkedHashSet = new CollectionContainsPerformanceTest("LinkedHashSet", N_Runs, new LinkedHashSet<Integer>(), N_Elements);
		PerformanceTest testTreeSet = new CollectionContainsPerformanceTest("TreeSet", N_Runs, new TreeSet<Integer>(), N_Elements);
		PerformanceTest testArrayList = new CollectionContainsPerformanceTest("ArrayList", N_Runs, new ArrayList<Integer>(), N_Elements);
		PerformanceTest testLinkedList = new CollectionContainsPerformanceTest("LinkedList", N_Runs, new LinkedList<Integer>(), N_Elements);
		PerformanceTest tests[] = {testHashSet,testLinkedHashSet, testTreeSet, testArrayList, testLinkedList};
		Arrays.asList(tests).forEach(PerformanceTest::run);
		
	}

}
