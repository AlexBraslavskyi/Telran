import java.util.Scanner;

public class InformationPrimitivesAppl {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		while(true) {
			System.out.println("Enter primirtive type or exit");
			String primitiveType = scanner.next();
			if(primitiveType.equals("exit")) {
				
				break;
			}
			System.out.println("Information type: max - maximal value, min - minimal value, bytes - number of bytes");
			String informationType = scanner.next();
			String result = getInformation(primitiveType, informationType);
			System.out.println(result);
		}

	}

	private static String getInformation(String primitiveType, String informationType) {
		switch (primitiveType) {
		case "int": return getIntInformation(informationType);
//		TODO 7cases for all types
				default:return "Error wrong primitive type";
		}
		
	}

	private static String getIntInformation(String informationType) {
		switch(informationType) {
		case "min": return ""+Integer.MIN_VALUE;
//		TODO 3
		default: return "Error: wrong information type";
	}

}
}
