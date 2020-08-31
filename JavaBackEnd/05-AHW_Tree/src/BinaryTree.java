import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Consumer;

public class BinaryTree<E> implements Iterable<E>{
	
	private class Node{		
		E data;
		Node parent;
		Node left;
		Node right;
		
		public Node (E data) {
			this.data = data;
		}
		
		public Node (E data, Node parent) {
			this.data = data;
			this.parent = parent;
		}
		
		public boolean isRoot() {
			return parent == null;
		}
		
		public boolean hasLeft() {
			return left != null;
		}
		
		public boolean hasRight() {
			return right != null;
		}
				
		public boolean isRightChild() {
			return !isRoot() && parent.right == this;
		}
		
		public Node getLeftmost() {
			Node current = this;
			while(current.hasLeft()) {
				current = current.left;
			}
			return current;
		}
		
	}
	
	private class IterImpl implements Iterator<E> {
		Node current;

		public IterImpl() {
			if (root != null) {
				current = root.getLeftmost();
			}
		}

		@Override
		public boolean hasNext() {
			return current != null;
		}

		@Override
		public E next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}

			E result = current.data;
			findNext();
			return result;
		}

		private void findNext() {
			if (current.right != null) {
				current = current.right.getLeftmost();
				return;
			}
			
			while (current.isRightChild()) {
				current = current.parent;
			}
			current = current.parent;
		}

	}

	private Node root = null;
	private int size = 0;
	Comparator<E> comparator = null;
	
	public BinaryTree(Comparator<E> comparator) {
		this.comparator = comparator;
	}
	
	public boolean add(E data) {
		
		if (root == null) {
			root = new Node(data);
			size++;
			return true;
		}
		
		Node current = root;
		while(true) {
			int res = comparator.compare(data, current.data);
			if (res == 0) return false; // such element is already exists
			if (res < 0) {
				if (! current.hasLeft()) {
					current.left = new Node(data, current);	
					break;
				}
				current = current.left;
			}
			else {
				if (! current.hasRight()) {
					current.right = new Node(data, current);
					break;
				}
				current = current.right;
			}
		}
		size++;
		return true;
	}
	
	public boolean contains(E data) {
		return findNodeByData(data) != null;
	}
	
	private Node findNodeByData(E data){
		for(Node current = root;current != null;) {
			int res = comparator.compare(data, current.data);
			if (res == 0) {
				return current;
			}
			current = (res < 0) ? current.left : current.right;
		}		
		return null;	// not found
	}
	
	public boolean remove(E data) {
		Node target = findNodeByData(data);
		if (target == null) return false;
		
		// if target has 2 children, replace target by victim
		if (target.hasLeft() && target.hasRight()) { 
			Node victim = target.right.getLeftmost();
			target.data = victim.data;
			target = victim;
		}
		
		// from here target has no more than 1 child
		Node child = target.hasLeft() ? target.left : target.right;
		if (target.isRoot()) {
			root = child;
		} else if (target.isRightChild()) {
			target.parent.right = child;
		} else {
			target.parent.left = child;
		}
		
		if (child != null) {
			child.parent = target.parent;
		}
		
		size--;
		return true;
	}
	
	public void forEachRecursive(Consumer<E> consumer) {
		forEachRecursive(consumer, root);
	}
	
	public void forEachRecursive(Consumer<E> consumer, Node node) {
		if (node == null) return;
		forEachRecursive(consumer, node.left);
		consumer.accept(node.data);
		forEachRecursive(consumer, node.right);
	}
	
	@Override
	public String toString() {
		StringBuilder bld = new StringBuilder("[");
		forEachRecursive(t->bld.append(t).append(", "));
		return bld.replace(bld.length() - 2, bld.length(), "]").toString();
	}
	
	public int depth() {
		return depth(root);
	}
	
	private int depth(Node node) { // recursive part
		if (node == null) {
			return 0;
		}
		return Integer.max(depth(node.left), depth(node.right))+1;
	}
	
	public int size() {
		return size;
	}
	 void printTree() {
			if (root != null) {
				print(root, 0);
			}
			System.out.println();
		}

		// recursive part of printTree() function
		private void print(Node node, int depth) {
			if (node == null) {
				return;
			}
			print(node.right, depth+1);
			for (int i=0;i<depth;i++) {
				System.out.print("   ");
			}
			System.out.printf("%3d%n",node.data);
			print(node.left, depth+1);
		}

	@Override
	public Iterator<E> iterator() {		
		return new IterImpl();
	}

}