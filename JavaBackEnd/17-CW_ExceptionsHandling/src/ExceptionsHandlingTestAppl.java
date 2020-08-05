
public class ExceptionsHandlingTestAppl {

	
	
	
	
	
	public static void main(String[] args) {
	int ar[] = {1, 2, 3, 4, 5};
System.out.println(getAtIndex(ar, 3));

	}
	private static int getAtIndex(int[] ar, int i) {
		
		
	try {
		return ar[i];
	} catch (Exception e) {
		return Integer.MIN_VALUE;
	}finally {
		System.out.println("block finally");
	}
		
	}

}
