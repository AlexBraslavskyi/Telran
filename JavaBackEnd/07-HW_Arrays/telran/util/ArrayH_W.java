package telran.util;

import java.util.Arrays;

public class ArrayHW {
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
	public void add(T obj) {
		if(size == array.length) {
			reallocate();
		}
		array[size++] = obj;
		
	}
	private void reallocate() {
		array = Arrays.copyOf(array, array.length * 2);
	}
	
	
	//return element at given index
	//if index<0 or index>=size return null
	
	public T get(int index) {
		if(index<0||index>=size) {
			return null;
		}
		return array[index];
	}
	public int size() {
		return size;
	}
}//SystemArrayCoppy


