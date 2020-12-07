import telran.messaging.MessageBox;

public class Sender extends Thread {
	MessageBox messageBoxEven;
	MessageBox messageBoxOdd;
	private int nMessages = 20;

	public int getnMessages() {
		return nMessages;
	}

	public void setnMessages(int nMessages) {
		this.nMessages = nMessages;
	}

	public Sender(MessageBox messageBoxEven, MessageBox messageBoxOdd) {
		super();
		this.messageBoxEven = messageBoxEven;
		this.messageBoxOdd = messageBoxOdd;
	}

	@Override
	public void run() {
		for (int i = 1; i <= nMessages; i++) {
			try {
				if (i % 2 == 0) {
					messageBoxEven.putMessage("message" + i);
				} else {
					messageBoxOdd.putMessage("message" + i);
				}

			} catch (InterruptedException e) {

			}

		}
	}
}
