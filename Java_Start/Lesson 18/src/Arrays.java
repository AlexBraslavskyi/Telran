
public class Arrays {

	public static void main(String[] args) {

		int [] ar = {8,1,5,6,7};
		System.out.println("length -" + ar.length);
		int [] ar2 = ar;
		ar2[0] = -1;
		ar[1] = -2;
		//ar = null;
		for(int i=0; i <ar.length;i++){
			System.out.print("["+ar[i]+"]");
		}
		System.out.println();
		int [] ar1 = new int[11]; // array 00000000000
	
		for(int i=0; i <ar1.length;i++){
			System.out.print("["+ar1[i]+"]");
		}
		
	}

}
