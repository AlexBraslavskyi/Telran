package telran.util;

import java.util.Comparator;
import java.util.Iterator;
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
		Predicate<T> predicate = new PredicateRemoveAll(pattern);
		return removeIf(predicate);
	}

	default public boolean retainAll(IndexedList<T> pattern) {// O[N]
		Predicate<T> predicate = new PredicateRemoveAll(pattern);
		return removeIf(predicate.negate());
		
	}
	/**
	 * removes all objects not equaled the ones from the given patterns
	 * 
	 * @param patterns
	 * @return true if at least one object has been removed or false
	 */
//	default public boolean retainAll(IndexedList<T> patterns) {// O[N]
//		int initialSize = size();
//		Iterator<T> it = iterator();
//		while (it.hasNext()) {
//			T obj = it.next();
//			if (!patterns.contains(obj)) {
//				it.remove();
//			}
//		}
//		return initialSize != size();
//	}
//
//	/**
//	 * removes all objects equaled the ones from the given patterns
//	 * 
//	 * @param patterns
//	 * @return true if at least one object has been removed or false
//	 */
//	default public boolean removeAll(IndexedList<T> patterns) {// O[N]
//		int initialSize = size();
//		Iterator<T> it = iterator();
//		while (it.hasNext()) {
//			T obj = it.next();
//			if (patterns.contains(obj)) {
//				it.remove();
//			}
//		}
//		return initialSize != size();
//	}

}
