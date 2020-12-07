package telran.tests;

import static java.time.Duration.ofMillis;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTimeout;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telran.util.concurrent.BlockingListQueue;

class BlockingListQueueTest {
	BlockingListQueue<String> myQueue;
	BlockingListQueue<String> myEmptyQueue;
	BlockingListQueue<String> myFullQueue;
	final static String[] array = { "Java", "C", "Python", "JS", "TS", "C#", "Kotlin" };
	AtomicInteger step;

	@BeforeEach
	void setup() {
		myQueue = new BlockingListQueue<String>(8);
		myEmptyQueue = new BlockingListQueue<String>(3);
		myFullQueue = new BlockingListQueue<String>(7);
		for (String elm : array) {
			myQueue.add(elm);
			myFullQueue.add(elm);
			step = new AtomicInteger(0);
		}
	}

	@Test
	void testAdd() {
		assertThrows(NullPointerException.class, () -> myQueue.add(null));
		assertDoesNotThrow(() -> myQueue.add("more data"));
		assertThrows(IllegalStateException.class, () -> myQueue.add("to much data"));
	}

	@Test
	void testPeek() {
		assertEquals("Java", myQueue.peek());
		assertEquals("Java", myQueue.peek());
		assertNotEquals("C", myQueue.peek());
		assertEquals("Java", myQueue.peek());
		assertNull(myEmptyQueue.peek());
	}

	@Test
	void testElement() {
		assertEquals("Java", myQueue.element());
		assertNotEquals("C", myFullQueue.element());
		assertThrows(NoSuchElementException.class, () -> myEmptyQueue.element());
	}

	@Test
	void testIsEmpty() {
		assertFalse(myQueue.isEmpty());
		assertFalse(myFullQueue.isEmpty());
		assertTrue(myEmptyQueue.isEmpty());
	}

	@Test
	void testOffer() {
		assertTrue(myQueue.offer("new Data"));
		assertFalse(myFullQueue.offer("new Data"));
		assertThrows(NullPointerException.class, () -> myEmptyQueue.offer(null));
	}

	@Test
	void testOfferCondition() throws InterruptedException {
		assertThrows(NullPointerException.class, () -> myEmptyQueue.offer(null, 3, TimeUnit.MILLISECONDS));
		assertDoesNotThrow(() -> myQueue.offer("extra data", 3, TimeUnit.MILLISECONDS));
		assertEquals(8, myQueue.size());
		assertDoesNotThrow(() -> assertFalse(myQueue.offer("extra data")));
		assertTimeout(ofMillis(200), () -> {
			assertDoesNotThrow(() -> assertFalse(myQueue.offer("extra data", 150, TimeUnit.MILLISECONDS)));

		});

	}

	@Test
	void testPoll() {
		assertNull(myEmptyQueue.poll());
		assertEquals("Java", myQueue.poll());
		assertEquals("C", myQueue.poll());
	}

	@Test
	void testPollCondition() throws InterruptedException {
		assertDoesNotThrow(() -> {
			assertTrue(myEmptyQueue.add("data"));
		});
		assertTimeout(ofMillis(55), () -> {
			assertEquals("data", myEmptyQueue.poll(50, TimeUnit.MILLISECONDS));
		});
	}

	@Test
	void testPutCondition() throws InterruptedException {
		assertThrows(NullPointerException.class, () -> myQueue.put(null));
		assertDoesNotThrow(() -> myQueue.put("data"));
		assertEquals(8, myQueue.size());

	}

	@Test
	void testRemove() {
		assertEquals("Java", myQueue.remove());
		assertNotEquals("Java", myQueue.remove());
		assertThrows(NoSuchElementException.class, () -> myEmptyQueue.remove());
	}

	@Test
	void testRemoveObject() {
		assertTrue(myQueue.remove("C#"));
		assertFalse(myQueue.remove("C#"));
		assertFalse(myEmptyQueue.remove("C#"));
		assertThrows(NullPointerException.class, () -> myEmptyQueue.remove(null));
	}

	@Test
	void tesTake() throws InterruptedException {
		myEmptyQueue.add("data");
		assertEquals("data", myEmptyQueue.take());

	}
}