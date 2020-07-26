package telran.util;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.function.Predicate;



public class Array<T> implements Iterable<T>{
	
	private T[] array;
	private Comparator<T> comparator;
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
		if (comparator != null) {
			if (comparator.compare(array[size - 2], obj) > 0) {
				comparator = null;
			}
		}

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

		if (comparator != null) {
			if (comparator.compare(obj, array[index + 1]) > 0) {
				comparator = null;
			} else if (index != 0 && comparator.compare(obj, array[index - 1]) < 0) {
				comparator = null;
			}
		}
		return true;
	}


	
	public class FiltredIterator implements Iterator<T>{

	
private int index = 0;
	
	@Override
	public boolean hasNext() {
		return index < size;
	}

	@Override
	public T next() {
		if (!hasNext()) {
			throw new UnsupportedOperationException("remove");
		}
		return array[index++];

	}
	
	

}

	public void FiltredIterator(Iterator<T> iterator, Predicate<T> filter) {
		
		int initialSize = size();
		Iterator<T> it = iterator;
		while (it.hasNext()) {
			T obj = it.next();
			if (filter.test(obj)) {
				it.remove();
			}
		}

	}

	@Override
	public Iterator<T> iterator() {
	
		return new FiltredIterator();
	}
}
