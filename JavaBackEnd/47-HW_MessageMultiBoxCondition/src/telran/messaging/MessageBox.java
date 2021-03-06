package telran.messaging;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
//import telran.util.concurrent;

import telran.util.concurrent.BlockingListQueue;

public class MessageBox {
private static int defaultLimit = Integer.MAX_VALUE;
private Lock monitor = new ReentrantLock();
private Condition consumerWaitingCondition = monitor.newCondition();
private Condition producerWaitingCondition = monitor.newCondition();
BlockingListQueue<String> messages = new BlockingListQueue<String>();
//private Blockin<String> messages = new LinkedList<String>();
private int limit;


public MessageBox(int limit) {
	this.limit = limit;
}
public MessageBox() {
	this(defaultLimit );
}
public static int getDefaultLimit() {
	return defaultLimit;
}
public static void setDefaultLimit(int defaultLimit) {
	MessageBox.defaultLimit = defaultLimit;
}
public void putMessage(String message) throws InterruptedException {
	try {
		monitor.lock();
		while (messages.size() == limit) {
			producerWaitingCondition.await();
		}
		messages.offer(message); //"this" is mandatory  
		consumerWaitingCondition.signal();
	} finally {
		monitor.unlock();
	} 
}
 public String takeMessage() throws InterruptedException {
	try {
		monitor.lock();
		while (messages.isEmpty()) {
			consumerWaitingCondition.await();
		}
		String res = messages.remove();
		
		producerWaitingCondition.signal();
		return res;
	} finally {
		monitor.unlock();
	}
}
 public String poll() {
	try {
		monitor.lock();
		return messages.poll();
	} finally {
		monitor.unlock();
	}
}
}
