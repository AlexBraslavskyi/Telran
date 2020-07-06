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

	private Node<T> head; // reference to the first node
	private Node<T> tail; // reference to the last node
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
//	return (index < size / 2) ? getFromLeft(index).obj : getFromRight(index).obj;
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
		if (index < 0 || index >= size) { // if size-1 -> cann't add to end
			return false;
		}
		if (index == 0) {
			addHead(obj);// private

		} else {
			addMiddle(index, obj);// private
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
		Node<T> node = getNode(index);// get
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
}

//private void addMiddle(int index, T obj) {
//	Node<T> node = new Node<>(obj);
//	Node<T> current = this.head;
//	if(head == null) {
//		head = tail = node;
//	}else {
//	for(int i = 0; i < index; i++) {
//		current = current.next;
//		if(i == index-1) {
//			node.next = current;
//			node.prev = current.prev;
//			current.prev.next = node;
//			current.prev = node;
//		
//		}
//		
//	}
//	}
//	
//}
//
//private void addRoot(T obj) {
//	Node<T> node = new Node<>(obj);
//	if(head == null) {
//		head = tail = node;
//	}else {
//		head.prev = node;
//		node.next = head;
//		head = node;
//	}
//	
//}
//public T remove (int index) {
//	if(index < 0 || index >=size) {
//		return null;
//		
//	}
//	Node<T> node = getNode(index);//get
//	T res = node.obj;
////	System.out.println(res);
//	if(head == null) {
//		head = tail = node;
//		return null;
//	}
//	 if(index == 0) {
//		removeRoot();
//		
//	}else if(index == size - 1){
//		removeTail();
//	}else {
//		removeNode(node);
//		
//	}
//	
//	size --;
//		return res;
//
//}
//
//private void removeNode(Node<T> node) {
//	node.prev.next = node.next;
//	node.next.prev = node.prev;
//
//	
//}
//
//private void removeTail() {
//	tail = tail.prev;
//}
//
//private void removeRoot() {
//	head = head.next;
//	
//}
//
//private Node<T> getNode(int index) {
//
//	return (index < size / 2) ? getFromLeft(index) : getFromRight(index);
//}
//}
//
