package telran.util;

import java.util.*;
import telran.util.LinkedListLoop.Node;

public class ListValidator {

	public static boolean hasLoop(Node head) {

		// Create a temporary node
		Node temp = new Node();
		while (head != null) {

			// This condition is for the case when there is no loop
			if (head.next == null) {
				return false;
			}

			// Check if next is already pointing to temp
			if (head.next == temp) {
				return true;
			}

			// Store the pointer to the next node in order to get to it in the next step
			Node next = head.next;

			// Make next point to temp
			head.next = temp;

			// Get to the next node in the list
			head = next;
		}

		return false;
	}

	// Floyd's Cycle-Finding Algorithm O(n) time complexity
	public static int loopStartIndex(Node head) {
		Node step = head, stepX2 = head;
		int flag = 0;
		int index = 0;
		while (step != null && stepX2 != null && stepX2.next != null) {
			step = step.next;
			stepX2 = stepX2.next.next;
			index++;
			if (step == stepX2) {
				flag = 1;
				break;
			}
		}
		if (flag == 1) {
			return index;
		} else {
			return -1;
		}
	}
}
