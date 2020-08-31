package telran.numbers;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;


public class SetOperations {
	/**
	 * 
	 * @param array
	 * @return array with no repeated numbers additional requirement: one code line
	 */

	public static Integer[] removeRepeated(Integer array[]) {

		return new LinkedHashSet<Integer>(Arrays.asList(array)).toArray(new Integer[0]);
	}

	/**
	 * 
	 * @param ar1
	 * @param ar2
	 * @return array of common integers from two received arrays throws
	 *         IllegalArgumentException if at least one array from the received has
	 *         the repeated numbers ar1 -> {1,2,3,4}, ar2 -> {1,2,8,9} => result ->
	 *         {1,2}
	 */


	public static int[] intersection(int ar1[], int ar2[]) {
	
			Set<Integer> mySet1 = new HashSet<Integer>(Arrays.asList(intToIntegerArrayConvert(ar1)));
			Set<Integer> mySet2 = new HashSet<Integer>(Arrays.asList(intToIntegerArrayConvert(ar2))); 
			if (mySet1.size()!=ar1.length || mySet2.size()!=ar2.length) {
				throw new IllegalArgumentException("array has duplicate value");
			}
			mySet1.retainAll(mySet2);
		
			return IntegerToIntArrayConvert(mySet1.toArray(new Integer[0]));
	}

	/**
	 * 
	 * @param array
	 * @return true if the array has the repeated numbers
	 */
	public static boolean hasRepeated(int array[]) {
		// v1
		Set<Integer> mySet1 = new HashSet<Integer>(Arrays.asList(intToIntegerArrayConvert(array)));

		return array.length == mySet1.size() ? false : true;

		// v2
//			Set <Integer> dupes = new HashSet<Integer>();
//			for (int i : array) { 
//				if (!dupes.add(i)) { 
//					return true;
//				}				
//		}
//			return false;
	}

	/**
	 * 
	 * @param ar1
	 * @param ar2
	 * @return union of ar1 and ar2 (all elements of ar1 + elements of ar2 that
	 *         don't exist in ar1) throws IllegalArgumentException if at least one
	 *         array from the received has the repeated numbers ar1 -> {1,2,3,4},
	 *         ar2 -> {1,2,8,9} => result -> {1,2,3,4,8,9}
	 */

	public static int[] union(int ar1[], int ar2[]) {
	
			Set<Integer> mySet1 = new LinkedHashSet<Integer>(Arrays.asList(intToIntegerArrayConvert(ar1)));
			Set<Integer> mySet2 = new LinkedHashSet<Integer>(Arrays.asList(intToIntegerArrayConvert(ar2)));
			if (mySet1.size()!=ar1.length || mySet2.size()!=ar2.length) {
				throw new IllegalArgumentException("array has duplicate value");
			}
			mySet1.addAll(mySet2);

			return IntegerToIntArrayConvert(mySet1.toArray(new Integer[0]));
	
	}

	/**
	 * 
	 * @param ar1
	 * @param ar2
	 * @return array of elements from ar1 that don't exist in ar2 throws
	 *         IllegalArgumentException if at least one array from the received has
	 *         the repeated numbers ar1 -> {1,2,3,4}, ar2 -> {1,2,8,9} => result ->
	 *         {3,4}
	 */

	public static int[] subtraction(int ar1[], int ar2[]) {
			Set<Integer> mySet1 = new HashSet<Integer>(Arrays.asList(intToIntegerArrayConvert(ar1)));
			Set<Integer> mySet2 = new HashSet<Integer>(Arrays.asList(intToIntegerArrayConvert(ar2)));
				if (mySet1.size()!=ar1.length || mySet2.size()!=ar2.length) {
					throw new IllegalArgumentException("array has duplicate value");
				}
		
			mySet1.removeAll(mySet2);

			return IntegerToIntArrayConvert(mySet1.toArray(new Integer[0]));
		
	}
//	The 'array' has random length and contains random numbers, probably repeatable. 
//	The function returns true, if given 'number' could be represented exactly as sum of two array elements, false otherwise. 
//	The main task is to do it with complexity O(N), considering the N  is array length. 
	public static boolean isSumOfTwoElements(int[] array, int number) {
		if(array.length < 2) return false;
		
	    HashSet <Integer> set = new LinkedHashSet<>();
	    set.add(number - array[0]);

	    for(int i = 1; i < array.length; i++) {
	       if(set.contains(array[i])) return true;
	       set.add(number - array[i]);
	    }
	    return false;
	}
	
	public static Integer[] intToIntegerArrayConvert(int[] array) {
		Integer[] ar = new Integer[array.length];
		Arrays.setAll(ar, i -> array[i]);
		return ar;
	}

	public static int[] IntegerToIntArrayConvert(Integer[] array) {
		int[] ar = new int[array.length];
		Arrays.setAll(ar, i -> array[i]);
		return ar;
	}

}
