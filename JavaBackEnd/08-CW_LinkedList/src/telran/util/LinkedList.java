package telran.util;

public class LinkedList<T> {
	
	private static class Node<T>{
		public T obj;
		public Node<T> next;
		public Node<T> prev;
		
		public Node(T obj) {
			this.obj = obj;
		}
			
	}
	
private Node<T> head; //reference to the first node
private Node<T> tail; //reference to the last node
private int size;


public void add(T obj) {
	Node<T> node = new Node<>(obj);
	if(head == null) {
		head = tail = node;
	}else {
		tail.next = node;
		node.prev = tail;
		tail = node;
		
	}
	size++;
	
}

public T get(int index) {
	if(index < 0 || index >= size) {
		return null;
	}
	return (index < size / 2) ? getFromLeft(index) : getFromRight(index);
}
private T getFromRight(int index) {
Node<T> current = tail;
for(int i = size - 1; i > index; i--) {
	current = current.prev;
}
	return current.obj;
}

private T getFromLeft(int index) {
	Node<T> current = head;
	for(int i = 0; i < index; i++) {
		current = current.next;
	}
		return current.obj;
}

public int size() {
	return size;
	}

public boolean add(int index, T obj) {
	if(index < 0 || index >= size) {
		return false;
	}
	if(index == 0) {
		addRoot(obj);//private
	}
	else {
		addMiddle(index,obj);//private
	}
	size++;
	return true;
}

private void addMiddle(int index, T obj) {
	// TODO Auto-generated method stub
	
}

private void addRoot(T obj) {
	// TODO Auto-generated method stub
	
}
public T remove (int index) {
	if(index < 0 || index >=size) {
		return null;
		
	}
	Node<T> node = getNode(index);//get
	T res = node.obj;
	if(index == 0) {
		removeRoot();
	}else if(index == size - 1){
		removeTail();
	}else {
		removeNode(node);
	}
	size --;
		return res;

}

private void removeNode(Node<T> node) {
	// TODO Auto-generated method stub
	
}

private void removeTail() {
	// TODO Auto-generated method stub
	
}

private void removeRoot() {
	// TODO Auto-generated method stub
	
}

private Node<T> getNode(int index) {
	// TODO Auto-generated method stub
	return null;
}
}

