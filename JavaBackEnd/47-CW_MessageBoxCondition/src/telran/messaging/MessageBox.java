package telran.messaging;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MessageBox {
private String message;
private Lock monitor = new ReentrantLock();
private Condition consumerWaitingCondition = monitor.newCondition();
private Condition producerWaitingCondition = monitor.newCondition();

public void putMessage(String message) throws InterruptedException {
	try {
		monitor.lock();
		while (this.message != null) {
			producerWaitingCondition.await();
		}
		this.message = message; //"this" is mandatory  
		consumerWaitingCondition.signal();
	} finally {
		monitor.unlock();
	} 
}
 public String takeMessage() throws InterruptedException {
	try {
		monitor.lock();
		while (message == null) {
			consumerWaitingCondition.await();
		}
		String res = message;
		message = null;
		producerWaitingCondition.signal();
		return res;
	} finally {
		monitor.unlock();
	}
}
 public String poll() {
	try {
		monitor.lock();
		String res = message;
		message = null;
		return res;
	} finally {
		monitor.unlock();
	}
}
}
