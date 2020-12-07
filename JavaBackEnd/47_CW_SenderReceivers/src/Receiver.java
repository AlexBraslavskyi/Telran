import telran.messaging.MessageBox;

public class Receiver extends Thread {
MessageBox messageBox;
//volatile boolean running = true;//
//public void stopReceiver() {
//	running = false;
//}
public Receiver(MessageBox messageBox) {
	super();
	this.messageBox = messageBox;
	
	
}
public void setMessageBox(MessageBox messageBox) {
	this.messageBox = messageBox;
}
@Override
public void run() {
	String message =  null;
	while(!interrupted()) {//!interrupted()
		try {
			message = messageBox.takeMessage();
			System.out.printf("Thread with id %d: %s\n", getId(), message);
		} catch (InterruptedException e) {
			break;
		}
	}
	
	while(true) {
		message = messageBox.poll();
		if (message == null) {
			break;
		}
		System.out.printf("Thread with id %d: %s\n", getId(), message);
	}
}
}
