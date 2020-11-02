package telran.util;

import java.util.Arrays;

public class Array<T> implements IndexedList<T> {

	private T[] array;

	private int size;// blue = 0
	private static int defaultCapacity = 16;

	@SuppressWarnings("unchecked")
	public Array(int capacity) {
		array = (T[]) new Object[capacity];
	}

	public Array() {
		this(defaultCapacity);
	}

	public void add(T obj) {
		if (size == array.length) {
			reallocate();
		}
		array[size++] = obj;

	}

	private void reallocate() {
		array = Arrays.copyOf(array, array.length * 2);
	}

	public T get(int index) {
		if (index < 0 || index >= size) {
			return null;
		}
		return array[index];
	}

	public int size() {
		return size;
	}

	public boolean add(int index, T obj) {

		if (index < 0 || index >= size) {
			return false;
		}
		if (size == array.length) {
			reallocate();
		}
		System.arraycopy(array, index, array, index + 1, size - index);
		array[index] = obj;
		size++;

		return true;

	}

	public T remove(int index) {
		if (index < 0 || index >= size) {
			return null;
		}
		size--;
		T res = array[index];
		System.arraycopy(array, index + 1, array, index, size - index);
		array[size] = null;
		return res;

	}

	public int indexOf(Object pattern) {
		for (int i = 0; i < size; i++) {
			if (array[i].equals(pattern)) {
				return i;
			}
		}
		return -1;
	}

	public int lastIndexOf(Object pattern) {
		for (int i = size - 1; i >= 0; i--) {
			if (array[i].equals(pattern)) {
				return i;
			}
		}
		return -1;
	}


	public void reverse() { // >=!!
		for (int left = 0, right = size - 1; left < right; left++, right--) {
			T tmp = array[left];
			array[left] = array[right];
			array[right] = tmp;
		}
	}
	@Override
	public T remove(Object pattern) {
		int index = indexOf(pattern);
		T res = remove(index);
		return res;	
		}

	@Override
	public boolean removeAll(IndexedList<T> patterns) {
		int tempSize = size;
		for(int i = size - 1; i >= 0; i--) {
			if (patterns.contains(get(i))) {
		remove(i);
			}}

		return tempSize != size;

	}

	@Override
	public boolean contains(T pattern) {
		for (int i = 0; i < size; i++) {
			if (array[i] == pattern) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean retainAll(IndexedList<T> patterns) {
		int tempSize = size;
		for(int i = size - 1; i >= 0; i--) {
			if (!patterns.contains(get(i))) {
		remove(i);
			}}
 
		return tempSize != size;

	}
}
