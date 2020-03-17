
public class ArrayMethods {

	public static void printArray(int [] ar){
		
		for(int i =0;i<ar.length;i++){
			System.out.print(ar[i] + " ");
			
		}
		System.out.println();
	}
	
	public static int arraySum(int[] ar) {
	int res = 0;
	for(int i = 0;i<ar.length;i++){
		res = res+ar[i];
	}
	return res;
	}
	public static void sortBubble(int []ar) {
		boolean res = false;
		do{
		res = maxToEnd(ar);
		}while(res==true);
	}
	private static boolean maxToEnd(int [] ar) {
		int temp = 0;
		boolean flag = false;
		for(int i=  0; i<ar.length-1;i++){
			if(ar[0]>ar[i+1]){
				temp = ar[i];
				ar[i] = ar[i+1];
				ar[i+1]=temp;
			flag = true;
			}
		}
		
		return flag;
		
	}
}
