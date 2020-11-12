public class Printer extends Thread {
 

	private String symbols;

	public Printer(String symbols) {
		setDaemon(true);
		this.symbols = symbols;
	}
	
	
	@Override
	public void run() {
		int index = 0;
		int length = symbols.length();
		while(true) {
			System.out.println(symbols.charAt(index));
			try {
				sleep(2000);
			} catch (InterruptedException e) {
				index++;
				if(index == length) {
					index = 0;
				}
			}
		}
	}
}
		
//	char[]chars = symbols.toCharArray();
//int i = 0;
//		while (true) {
//			System.out.println(chars[i]);
//			if(interrupted()) {
//				System.out.println(chars[i++]);
//			}
//			try {
//				sleep(2000);
//			} catch (InterruptedException e) {
//				System.out.println(chars[i++]);
//			}
//			
//		}
//	}
//}
