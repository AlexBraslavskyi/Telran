package telran.util;

import telran.util.LinkedListLoop.Node;


public class ListValidator {
	private static boolean cycle;
	private static Node step;
	private static Node stepX2;
	private static int position;

	
	public static int loopStartIndex(Node head) {

		step = stepX2 = head;
		cycle = hasLoop(head);

		if (cycle) {
			System.out.println("Found cycle.");
			position = findPosition(head);
			System.out.println("Cycle start on position - " + position);
		} else {
			System.out.println("No cycle.");
			position = -1;
		}
		return position;
	}

	private static int findPosition(Node head) {
		int i = 0;
		step = head;
		while (step != stepX2) {
			step = step.next;
			stepX2 = stepX2.next;
			++i;
		}
		return i;
	}

	// Floyd's Cycle-Finding Algorithm O(n) time complexity
	public static boolean hasLoop(Node head) {
		step = stepX2 = head;
		if (step == null || step.next == null) {
			return false;
		}
		while (step != null && stepX2 != null && stepX2.next != null) {
			step = step.next;
			stepX2 = stepX2.next.next;
			if (step == stepX2) {
				return true;
			}
		}
		return false;
	}
}
