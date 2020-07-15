package telran.util;

import java.util.Arrays;

public class Array<T> implements IndexedList<T>{
private T [] array;
private int size = 0;
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
/**
 * 
 * @param index
 * @return element at the given index
 * in the case index < 0 or index >= size returns null
 */
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
/**
 * looks for object equaled a given pattern
 * @param pattern
 * @return index of a first object equaled the pattern or -1
 */
public int indexOf(Object pattern) {
	for (int i = 0; i < size; i++) {
		if(array[i].equals(pattern)) {
			return i;
		}
	}
	return -1;
}
/**
 * looks for object equaled a given pattern
 * @param pattern
 * @return index of a last object equaled the pattern or -1
 */
public int lastIndexOf(Object pattern) {
	for (int i = size - 1; i >= 0; i--) {
		if(array[i].equals(pattern)) {
			return i;
		}
	}
	return -1;
}
/**
 * reversing the existing order
 * 1 2 3 4 before 
 * 4 3 2 1 after
 */
public void reverse () {
	for(int left = 0, right = size - 1; left < right; left++, right--) {
		T tmp = array[left];
		array[left] = array[right];
		array[right] = tmp;
	}
}
@Override
public T remove(Object pattern) {
	
	return remove(indexOf(pattern));
}
@Override
public boolean removeAll(IndexedList<T> patterns) {
	boolean res = false;
	Array<T> tmp = new Array<>(size);
	for(int i = 0; i < size; i++) {
		if (!patterns.contains(array[i])) {
			tmp.add(array[i]);
		}
	}
//	for(int i = size - 1; i >= 0; i--) {
//		if (patterns.contains(array[i])) {
//			remove(i);
//		}
//	}
	if (tmp.size < size) {
		res = true;
		array = tmp.array;
		size = tmp.size;
	}
	return res;
}
@Override
public boolean contains(T pattern) {
	
	return indexOf(pattern) != -1;
}
@Override
public boolean retainAll(IndexedList<T> patterns) {
	boolean res = false;
	Array<T> tmp = new Array<>(size);
	for(int i = 0; i < size; i++) {
		if (patterns.contains(array[i])) {
			tmp.add(array[i]);
		}
	}
	if (tmp.size < size) {
		res = true;
		array = tmp.array;
		size = tmp.size;
	}
	return res;
}
}
