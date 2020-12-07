import telran.messaging.MessageBox;

public class Receiver extends Thread {
	MessageBox messageBox;

	public Receiver(MessageBox messageBox) {
		super();
		this.messageBox = messageBox;
//	FIXME del deamon
//	setDaemon(true);
	}

	@Override
	public void run() {
		while (true) {
			try {
				String message = messageBox.takeMessage();
				System.out.printf("Thread %d : %s\n", getId(), message);
			} catch (InterruptedException e) {
				if (messageBox.takeWithoutWaiting() == null) {
					break;
				} else {
					System.out.printf("Thread %d : %s\n", getId(), messageBox.takeWithoutWaiting());
				}
			}
		}

	}

	public void setMessageBox(MessageBox messageBox) {
		this.messageBox = messageBox;
	}
}
