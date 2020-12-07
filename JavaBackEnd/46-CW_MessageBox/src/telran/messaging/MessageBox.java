package telran.messaging;

public class MessageBox {
	private String message;

	synchronized public void putMessage(String message) throws InterruptedException {

		while (this.message != null) {
			this.wait();

		}
		this.message = message;
		notifyAll();

	}

	synchronized public String takeMessage() throws InterruptedException {

		while (message == null) {
			this.wait();

		}

		String res = message;
		message = null;
		this.notifyAll();
		return res;
	}

	synchronized public String takeWithoutWaiting() {
		String res = message;
		message = null;
		return res;
	}
}
