import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import telran.performance.PerformanceTest;

public class ListOperationsTestAppl {

	private static final int N_Runs = 10000;
	private static final int N_ELEMENTS = 100000;

	public static void main(String[] args) {
		Integer array[] = {1,2,3,4,5,6,7,8,9,10};
		//1-st way
//		List <Integer> list = new ArrayList<>();
//		for(Integer num: array) {
//			list.add(num);
//		}
		//2-nd way
		List <Integer> list2 = Arrays.asList(1,2); //2.1 way (1,2,3,4,5,...10)
//		list.add(30); not work
		
		
		//3-rd way
		List <Integer> list = new ArrayList<>(Arrays.asList(array)); //3-rd way (1,2,3,4,5,...10)
		list.add(30);
		System.out.println("All elements of List");
		System.out.println(list);
//		int indexFrom = 3, indexTo = 7;
//		List<Integer> sublist = list.subList(indexFrom, indexTo);
//				System.out.printf("sublist from index %d to index %d\n", indexFrom, indexTo);
//				System.out.println(sublist);
//				sublist.add(2, 300);
//				System.out.println("After updating sublist");
//				System.out.println(sublist);
//				System.out.println(list);
//				sublist.clear();
//				System.out.println(list);
//				list = list2;
//				System.out.println(list);
//		Integer arList[]= new Integer[list.size()];
//		list.toArray(arList);
//// 		Integer arList[] = list.toArray(new Integer[0]);//30
//		System.out.println("To Array");
//		System.out.println(Arrays.deepToString(arList));
//				PerformanceTest testArrayList = new ListCreationPerformanceTest("array list", N_Runs, new ArrayList<Integer>(), N_ELEMENTS);
//				
//				PerformanceTest testLinkedList = new ListCreationPerformanceTest("linked list", N_Runs, new LinkedList<Integer>(), N_ELEMENTS);
//				PerformanceTest testArrayList = new ListAddRemoveFirstTest("array list", N_Runs, new ArrayList<Integer>(), N_ELEMENTS);
//				
//				PerformanceTest testLinkedList = new ListAddRemoveFirstTest("linked list", N_Runs, new LinkedList<Integer>(), N_ELEMENTS);
		PerformanceTest testArrayList = new ListGetPerformanceTest("array list", N_Runs, new ArrayList<Integer>(), N_ELEMENTS);
		
		PerformanceTest testLinkedList = new ListGetPerformanceTest("linked list", N_Runs, new LinkedList<Integer>(), N_ELEMENTS);
				testArrayList.run();
				testLinkedList.run();
	}

}
