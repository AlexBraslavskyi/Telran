import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class MyStackTests {

	List<Integer> list; 
	MyStack myStack;
	
	@BeforeEach
	void setUp() {
		list = new ArrayList<>(Arrays.asList());
		myStack = new MyStack(list);
	}
//	@Test
//	void testPush() throws Exception {
//		int[] exspected = {1, 2, 3, 4, 5 };
//		myStack.push(1);
//		myStack.push(2);
//		myStack.push(3);
//		myStack.push(4);
//		myStack.push(5);
//		assertEquals(exspected.length, list.size());
//		for (int i = 0; i < list.size(); i++) {
//			assertEquals(exspected[i], list.get(i));
//		}
//	}
//
	@Test
	void testPop() throws Exception {
		myStack.push(1);
		myStack.push(2);
		myStack.push(3);
		myStack.push(4);
		assertEquals(4,myStack.pop());
	}
	@Test
	void testGetMax() throws Exception {
		int exspected = 4;
		myStack.push(1);
		myStack.push(1);
		myStack.push(4);
		myStack.push(1);
		myStack.pop();
		myStack.push(2);
		myStack.pop();
		myStack.push(1);
		myStack.push(3);
		myStack.push(5);
		myStack.pop();
		myStack.pop();
		myStack.pop();
		assertEquals(exspected, myStack.getMax());
	}
	@Test
	void testIsEmpty() throws Exception {
		assertTrue(myStack.isEmpty());
		myStack.push(1);
		assertFalse(myStack.isEmpty());
	}

}
