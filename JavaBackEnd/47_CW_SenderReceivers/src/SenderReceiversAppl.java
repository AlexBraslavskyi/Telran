import telran.messaging.MessageBox;

public class SenderReceiversAppl {
	private static final int N_MESSAGES = 20;
	private static final int N_RECEIVERS = 10;

	public static void main(String[] args) throws InterruptedException {
		MessageBox messageBoxEven = new MessageBox();
		MessageBox messageBoxOdd = new MessageBox();
		Sender sender = 
				new Sender(messageBoxEven, messageBoxOdd, N_MESSAGES);
		Receiver[] receivers = new Receiver[N_RECEIVERS];
		startReceivers(messageBoxEven, messageBoxOdd, receivers);
		sender.start();
		sender.join();
		finishReceivers(receivers);
		

	}

	private static void finishReceivers(Receiver[] receivers) {
		for (int i = 0; i < N_RECEIVERS; i++) {
//			receivers[i].stopReceiver();//
			receivers[i].interrupt();
		}
		
	}

	private static void startReceivers(MessageBox messageBoxEven,
			MessageBox messageBoxOdd, Receiver[] receivers) {
		for (int i = 0; i < N_RECEIVERS; i++) {
			receivers[i] = new Receiver(null);
			receivers[i].setMessageBox
			(receivers[i].getId() % 2 == 0 ? 
					messageBoxEven : messageBoxOdd);
			receivers[i].start();
		}
	}

}
