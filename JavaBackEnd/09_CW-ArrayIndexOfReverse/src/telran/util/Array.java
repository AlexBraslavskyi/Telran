package telran.util;

import java.util.Arrays;

public class Array<T> {
	
	private T [] array;
	
	private int size;//blue  = 0
	private static int defaultCapacity = 16;
	@SuppressWarnings("unchecked")
	public Array(int capacity) {
		array = (T[]) new Object[capacity];
	}
	public Array() {
		this(defaultCapacity);
	}
//	public void add(T obj) {
//		if(size == array.length) {
//			reallocate();
//		}
//		array[size++] = obj;
//		
//	}
//	private void reallocate() {
//		array = Arrays.copyOf(array, array.length * 2);
//	}
//	
//	
//	//return element at given index
//	//if index<0 or index>=size return null
//	
//	public T get(int index) {
//		if(index < 0 || index >= size) {
//			return null;
//		}
//		return array[index];
//	}
//	public int size() {
//		return size;
//	}
//	
//	public boolean add(int index, T obj) {
//		System.out.println("Array - " + Arrays.deepToString(array));
//		System.out.println("Add - " + obj + " Index - " + index);
//		if(index >= 0 && index <= size - 1) {
//			if(size == array.length) {
//				reallocate();
//			}
//			System.arraycopy(array, index, array, index + 1,size-index);
//			array[index] = obj;
//			size ++;
//		System.out.println("Result - " + Arrays.deepToString(array));
//		System.out.println("***********************************************************");
//			return true;
//		}else {
//			return false;
//		}
//		
//	}
//	
//	public T remove(int index) {
//		System.out.println("Array - " + Arrays.deepToString(array));
//		System.out.println("Remove index - " + index);
//		if(index >= 0&& index < size - 1) {
//			size --;
//			T res = array[index];
//			System.arraycopy(array, index + 1, array, index, size-index);
//			array[size] = null;
//		System.out.println("Result - " + Arrays.deepToString(array));
//		System.out.println("************************************************************");
////		System.out.println(res);
//			return res;
//		}else {
//		
//		return null;
//		
//	}
//}
	
	public int indexOf(Object pattern) {
		for(int i = 0; i < size; i++) {
			if(array[i].equals(pattern)) {
				return i;
			}
		}
		return -1;
	}
	public int lastIndexOf(Object pattern) {
		for(int i = size-1; i >=0; i--) {
			if(array[i].equals(pattern)) {
				return i;
			}
		}
		return -1;
	}
	/**
	 * head->?<-tail
	 */
	public void reverse() {   //>=!!
		for(int left = 0, right = size-1; left < right; left++, right--) {
		T tmp = array[left];
		array[left] = array[right];
		array[right] = tmp;
	}
}
}