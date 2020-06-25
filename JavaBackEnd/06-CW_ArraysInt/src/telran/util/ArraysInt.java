package telran.util;

import java.util.Arrays;

public class ArraysInt {
	public static int[] add(int[] ar, int num) {
		// adds num in new array created on basis the given ar
		
		int result[]= Arrays.copyOf(ar, ar.length+1);
		result[ar.length] = num;
		
		return result;
	}
	
	public static int[] add(int ar[], int num, int index) {
		// adds num in specified index array created on basis the given ar
	int result[]= new int[ar.length+1];
			System.arraycopy(ar, 0, result, 0, index);
			result[index] = num;
			System.arraycopy(ar, index, result,index+1, ar.length-index);
		
		return result;
		
	}
	public static int[] remove(int ar[]) {
		//removes last element
		
		int result[]= Arrays.copyOf(ar, ar.length-1);
	
		
		return result;
		
	}
	public static int[] remove(int ar[],int index) {
		//removes element at specified index
		
		int result[]= new int[ar.length-1];
		System.arraycopy(ar, 0, result, 0, index);
		System.arraycopy(ar, index+1, result, index, ar.length-index-1);
		
		return result;
		
	}

}
