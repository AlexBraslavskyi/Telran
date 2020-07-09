package telran.util;

public interface IndexedList<T> {

	
	void add(T obj);
	boolean add(int index, T obj);
//	T remove(int index);
	T get(int index);
	int indexOf(Object pattern);
	int lastIndexOf(Object pattern);
	void reverse();
	int size();
	/**
	 * delete first object == pattern
	 * @param pattern
	 * @return reference to removed object or node
	 */
	T remove(Object pattern);
	/**
	 * removes all objects equaled the ones from the given patterns 
	 * @param patterns
	 * @return true if at least one object has been removed or false
	 */
	boolean removeAll(IndexedList<T> patterns);
	
	/**
	 * checks if there is an object equaled the given pattern 
	 * @param pattern
	 * @return true if exist or false
	 */
	boolean contains (T pattern);
	
	/**
	 * removes all objects not equaled the ones from the given patterns
	 * @param patterns
	 * @return true if at least one object has been removed or false
	 */
	boolean reteinAll(IndexedList <T> patterns);
	
	
	
}
