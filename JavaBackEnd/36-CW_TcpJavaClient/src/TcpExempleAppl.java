import java.util.Scanner;

public class TcpExempleAppl {

	public static void main(String[] args) throws Exception {
		TcpExampleClient client = new TcpExampleClient("localhost", 5000);
		Scanner scan = new Scanner(System.in);
		
		
		while(true) {
			try {
				System.out.println("Enter request type or quit");
				String lineType = scan.nextLine();
				if(lineType.equalsIgnoreCase("quit")) {
					break;
				}
				System.out.println("Enter any string");
				String line = scan.nextLine();
				System.out.println(client.send(lineType, line));
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		client.close();
		scan.close();
	}
}
