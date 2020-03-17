
public class TestArray2 {

	public static void main(String[] args) {
		String [] ar ={"Hello","Bye","Money","Happy"};
		String [] ar1 = ar;
		ar[1]=null;
		ar[1] = ar[2];
		ar[2] = "Big Money";
		ar[3] = ar[1]+ar[2];
for(int i = 0;i<ar.length; i++){
	System.out.print(ar[i] + " ");
}


	}

}