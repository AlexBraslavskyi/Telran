
public class LinkedListLoop {
	  public int data;
	     public Node next;

	

	Node n0=new Node(0);
	Node n1=new Node(1);
	Node n9=new Node(9);
	Node n10=new Node(10);

	n0.next = n1;
	n1.next = n2;
	n9.next = n10;
	n10.next = n3; //would be null, this is error

	//As result, your list has a loop:

	//(0)->(1)->(2)->(3)->(4)->(5)->(6)->(7)->(8)->(9)->(10)_
	             //  ^______________________________________/

//	Task 1.  
//	=======
//	Implement method accepting head of list (like n0 above) 
//	and returning true if list has a loop, false if loop is not found.

	class ListValidator{
	     public static boolean hasLoop(Node head) {
	         // TODO - implement
	     }
	   
	}


//	Task 2.
//	=======
//	Implement method returning index of node, where loop is started, or -1 if loop does not exist.
//	For example, for list above the index of loop starting node is 3.

	class ListValidator{
	   
	     public static int loopStartIndex(Node head) {
	         // TODO - implement
	     }
	}
	
}
