package telran.util;

public class LinkedListLoop {

	static class Node {
		public int data;
		public Node next;
		public Node prev;
		
		public Node(int data) {
			this.data = data;
		}
		
		public static void main(String[] args) {
		
			Node n0 = new Node(0);
			Node n1 = new Node(1);
			Node n2 = new Node(2);
			Node n3 = new Node(3);
			Node n4 = new Node(4);
			Node n5 = new Node(5);
			Node n6 = new Node(6);
			Node n7 = new Node(7);
			Node n8 = new Node(8);
			Node n9 = new Node(9);
			Node n10 = new Node(10);

			n0.next = n1;
			n1.next = n2;
			n2.next = n3;
			n3.next = n4;
			n4.next = n5;
			n5.next = n6;
			n6.next = n7;
			n7.next = n8;
			n8.next = n9;
			n9.next = n10;
			n10.next = n3;

			
			 ListValidator.loopStartIndex(n0);
			
			

		}
	}
}
