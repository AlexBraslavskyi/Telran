package telran.util;

import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;

import telran.util.*;

public interface IndexedList<T> extends Iterable<T> {

	// **** Add complexity*****

	void add(T obj); // O[1]

	boolean add(int index, T obj);// O[N]

	T remove(int index);// O[N]

	T get(int index);// O[N]- Linked list O[1] - Array

	int indexOf(Object pattern);// O[N]

	int lastIndexOf(Object pattern);// O[N]

	void reverse();// O[N]

	int size();// O[1]

	/**
	 * delete first object == pattern
	 * 
	 * @param pattern
	 * @return reference to removed object or node
	 */
	T remove(Object pattern);// O[N]

	/**
	 * checks if there is an object equaled the given pattern
	 * 
	 * @param pattern
	 * @return true if exist or false
	 */
	boolean contains(T pattern);// O[N]

	void sort(Comparator<T> comporator); // array O[N^2]

	/**
	 * removes all T objects matching a given predicate (condition)
	 * 
	 * @param predicate
	 * @return true if at list one T object has been removed
	 */
	default public boolean removeIf(Predicate<T> predicate) {

		int initialSize = size();
		Iterator<T> it = iterator();
		while (it.hasNext()) {
			T obj = it.next();
			if (predicate.test(obj)) {
				it.remove();
			}
		}
		return initialSize != size();
	}

	default public boolean removeAll(IndexedList<T> pattern) {// O[N]
		return removeIf(t -> PredicateContains(pattern, t));
	}

	default public boolean retainAll(IndexedList<T> pattern) {// O[N]
		return removeIf(t -> !PredicateContains(pattern, t));

	}

	private boolean PredicateContains(IndexedList<T> pattern, T t) {
		return pattern.contains(t);
	}
}
