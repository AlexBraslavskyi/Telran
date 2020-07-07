package telran.util;

import java.util.Arrays;

public class LinkedList<T> {

	private static class Node<T> {
		public T obj;
		public Node<T> next;
		public Node<T> prev;

		public Node(T obj) {
			this.obj = obj;
		}

	}

	private Node<T> head; 
	private Node<T> tail; 
	private int size;

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

	public boolean add(int index, T obj) {
		if (index < 0 || index >= size-1) { 
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
		Node<T> nodeIndex = getNode(index);
		Node<T> node = new Node<>(obj);
		Node<T> nodePrev = nodeIndex.prev;
		nodePrev.prev = node;
		node.prev = nodePrev;
		;
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
		T res = node.obj;
		if (head == tail) {
			head = tail = null;
		} else {
			if (index == 0) {
				removeHead();

			} else if (index == size - 1) {
				removeTail();
			} else {
				removeNode(node);

			}
		}
		size--;
		return res;

	}

	private void removeNode(Node<T> node) {
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

	public int indexOf(Object pattern) {
		Node<T> current = head;
		for (int i = 0; i < size; i++) {
			if (current.obj.equals(pattern)) {
				return i;
			}
			current = current.next;
		}
		return -1;
	}

	public int lastIndexOf(Object pattern) {
		Node<T> current = tail;
		for (int i = size - 1; i >= 0; i--) {
			if (current.obj.equals(pattern)) {
				return i;
			}
			current = current.prev; 
		}
		return -1;
	}

	/**
	 * head->exit?<-tail 
	 */
	public void reverse() {
		Node<T> currentTail = tail;
		Node<T> currentHead = head;
		for (int i = 0; i <= (size-1) / 2; i++) {
			T tmp = currentHead.obj;
			
			currentHead.obj = currentTail.obj;
			currentTail.obj = tmp;
			if(currentHead.next!=currentTail) {
			currentHead = currentHead.next;
			currentTail = currentTail.prev;
			}
		}
	}
}
