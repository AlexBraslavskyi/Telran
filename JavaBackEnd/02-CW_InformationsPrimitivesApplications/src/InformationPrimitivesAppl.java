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
		case "int":
				return getIntInformation(informationType);
		case "long":
			return getLongInformation(informationType);
		case "char":
			return getCharInformation(informationType);
		case "byte":
			return getByteInformation(informationType);
		case "double":
			return getDoubleInformation(informationType);
		case "float":
			return getFloatInformation(informationType);
		case "short":
			return getShortInformation(informationType);
				default:return "Error wrong primitive type";
		}
		
	}

	private static String getIntInformation(String informationType) {
		switch(informationType) {
		case "min": return ""+Integer.MIN_VALUE;
		case "max": return ""+Integer.MAX_VALUE;
		case "bytes": return ""+Integer.BYTES;
		default: return "Error: wrong information type";
	}

}
	private static String getLongInformation(String informationType) {
		switch(informationType) {
		case "min": return ""+Long.MIN_VALUE;
		case "max": return ""+Long.MAX_VALUE;
		case "bytes": return ""+Long.BYTES;
		default: return "Error: wrong information type";
	}

}
	private static String getByteInformation(String informationType) {
		switch(informationType) {
		case "min": return ""+Byte.MIN_VALUE;
		case "max": return ""+Byte.MAX_VALUE;
		case "bytes": return ""+Byte.BYTES;
		default: return "Error: wrong information type";
	}

}
	private static String getCharInformation(String informationType) {
		switch(informationType) {
		case "min": return ""+Character.MIN_VALUE;
		case "max": return ""+Character.MAX_VALUE;
		case "bytes": return ""+Character.BYTES;
		default: return "Error: wrong information type";
	}

}
	private static String getDoubleInformation(String informationType) {
		switch(informationType) {
		case "min": return ""+Double.MIN_VALUE;
		case "max": return ""+Double.MAX_VALUE;
		case "bytes": return ""+Double.BYTES;
		default: return "Error: wrong information type";
	}

}
	private static String getShortInformation(String informationType) {
		switch(informationType) {
		case "min": return ""+Short.MIN_VALUE;
		case "max": return ""+Short.MAX_VALUE;
		case "bytes": return ""+Short.BYTES;
		default: return "Error: wrong information type";
	}

}
	private static String getFloatInformation(String informationType) {
		switch(informationType) {
		case "min": return ""+Float.MIN_VALUE;
		case "max": return ""+Float.MAX_VALUE;
		case "bytes": return ""+Float.BYTES;
		default: return "Error: wrong information type";
	}

}
}
