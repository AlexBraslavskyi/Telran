package telran.util;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;

import telran.util.IndexedList;

public class LinkedList<T> implements IndexedList<T> {
	private static class Node<T> {
		public T obj;
		public Node<T> next;
		public Node<T> prev;

		public Node(T obj) {
			this.obj = obj;
		}

	}
private class LinkedListIterator implements Iterator<T>{
Node <T> current = head;
	@Override
	public boolean hasNext() {
		return current != null;
	}

	@Override
	public T next() {

		T res = current.obj;
		current = current.next;
		return res;
	}
	
	
}
	private Comparator<T> comparator;
	private Node<T> head; // reference to the first node
	private Node<T> tail; // reference to the last node
	private int size = 0;

	public void add(T obj) {
		Node<T> node = new Node<>(obj);
		if (head == null) {
			head = tail = node;
		} else {
			tail.next = node;
			node.prev = tail;
			tail = node;
		}
		size++;

	}

	public T get(int index) {
		if (index < 0 || index >= size) {
			return null;
		}
//	return (index < size / 2) ?  getFromLeft(index) : getFromRight(index);
		Node<T> nodeIndex = getNode(index);
		return nodeIndex.obj;
	}

	private Node<T> getFromRight(int index) {
		Node<T> current = tail;
		for (int i = size - 1; i > index; i--) {
			current = current.prev;
		}
		return current;
	}

	private Node<T> getFromLeft(int index) {
		Node<T> current = head;
		for (int i = 0; i < index; i++) {
			current = current.next;
		}
		return current;
	}

	public int size() {
		return size;
	}

	/**
	 * adds at index
	 * 
	 * @param index
	 * @param obj
	 * @return true if added (index is correct [0 - size-1]) false otherwise
	 */
	public boolean add(int index, T obj) {
		if (index < 0 || index >= size) {
			return false;
		}
		if (index == 0) {
			addHead(obj);
		} else {
			addMiddle(index, obj);
		}
		size++;
		return true;
	}

	private void addMiddle(int index, T obj) {
		Node<T> node = new Node<>(obj);
		Node<T> nodeIndex = getNode(index);
		Node<T> nodePrev = nodeIndex.prev;
		nodePrev.next = node;
		node.prev = nodePrev;
		nodeIndex.prev = node;
		node.next = nodeIndex;

	}

	private void addHead(T obj) {

		Node<T> node = new Node<>(obj);
		head.prev = node;
		node.next = head;
		head = node;

	}

	public T remove(int index) {
		if (index < 0 || index >= size) {
			return null;
		}
		Node<T> node = getNode(index);
 
		return removeNode(node);
	}

	private T removeNode(Node<T> node) {
		T res = node.obj;
		size--;
		if (tail == head) {
			head = tail = null;
		} else if (node == head) {
			removeHead();
		} else if (node == tail) {
			removeTail();
		} else {
			removeNodeInMiddle(node);
		}
		return res;
	}

	private void removeNodeInMiddle(Node<T> node) {
		Node<T> nodeNext = node.next;
		Node<T> nodePrev = node.prev;
		nodePrev.next = nodeNext;
		nodeNext.prev = nodePrev;

	}
 
	private void removeTail() {
		tail = tail.prev;
		tail.next = null;

	}

	private void removeHead() {
		head = head.next;
		head.prev = null;

	}

	private Node<T> getNode(int index) {

		return (index < size / 2) ? getFromLeft(index) : getFromRight(index);
	}

	/**
	 * looks for object equaled a given pattern
	 * 
	 * @param pattern
	 * @return index of a first object equaled the pattern or -1
	 */
	public int indexOf(Object pattern) {
		int index = 0;
		for (Node<T> current = head; current != null; current = current.next, index++) {
			if (current.obj.equals(pattern)) {
				return index;
			}
		}
		return -1;
	}

	/**
	 * looks for object equaled a given pattern
	 * 
	 * @param pattern
	 * @return index of a last object equaled the pattern or -1
	 */
	public int lastIndexOf(Object pattern) {
		int index = size - 1;
		for (Node<T> current = tail; current != null; current = current.prev, index--) {
			if (current.obj.equals(pattern)) {
				return index;
			}
		}
		return -1;
	}

	/**
	 * reversing the existing order 1 2 3 4 before 4 3 2 1 after
	 */
	public void reverse() {
		for (Node<T> left = head, right = tail; left != right
				&& left.prev != right; left = left.next, right = right.prev) {
			T tmp = left.obj;
			left.obj = right.obj;
			right.obj = tmp;
		}
	}

	@Override
	public T remove(Object pattern) {
//very simple but less optimal code
//	int index = indexOf(pattern);
//	return index != -1 ? remove(index) : null;
		// more complex code but requires only one pass over the list
		for (Node<T> current = head; current != null; current = current.next) {
			if (current.obj.equals(pattern)) {
				return removeNode(current);
			}
		}
		return null;
	}

	@Override
	public boolean removeAll(IndexedList<T> patterns) {

		int initialSize = size;
		Node<T> current = head;
		while (current != null) {
			Node<T> nextNode = current.next; // next node in any case if current is removed or isn't
			if (patterns.contains(current.obj)) {
				removeNode(current);
			}
			current = nextNode;
		}

		return initialSize > size;
	}

	@Override
	public boolean contains(T pattern) {

		return indexOf(pattern) != -1;
	}

	@Override
	public boolean retainAll(IndexedList<T> patterns) {
		int initialSize = size;
		Node<T> current = head;
		while (current != null) {
			Node<T> nextNode = current.next; // next node in any case if current is removed or isn't
			if (!patterns.contains(current.obj)) {
				removeNode(current);
			}
			current = nextNode;
		}

		return initialSize > size;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void sort(Comparator<T> comparator) {
		Node<T> current = this.head;
		if (comparator != null) {
			T array[] = (T[]) new Object[size];
			for (int i = 0; current != null; i++,current = current.next) {
				array[i] = current.obj;
			}
			Arrays.sort(array, comparator);
			current = this.head;
			for (int i = 0; i < array.length; i++,current = current.next) {
				current.obj = array[i];
			}
		}
	}

	@Override
	public Iterator<T> iterator() {
	
		
		return new LinkedListIterator();
	}
}
