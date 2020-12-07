import telran.messaging.MessageBox;

public class Sender extends Thread {
MessageBox messageBoxOdd;
MessageBox messageBoxEven;
private int nMessages = 20;

public int getnMessages() {
	return nMessages;
}
public void setnMessages(int nMessages) {
	this.nMessages = nMessages;
}
public Sender(MessageBox messageBoxEven, MessageBox messageBoxOdd, int nMessages) {
	super();
	this.messageBoxOdd = messageBoxOdd;
	this.messageBoxEven = messageBoxEven;
	this.nMessages = nMessages;
}
@Override
public void run() {
	for (int i = 2; i <= nMessages; i += 2) {
		try {
			messageBoxEven.putMessage("message"+i);
		} catch (InterruptedException e) {
			
		}

	}
	for (int i = 1; i <= nMessages; i += 2) {
		try {
			messageBoxOdd.putMessage("message"+i);
		} catch (InterruptedException e) {
			
		}

	}
	}

}
