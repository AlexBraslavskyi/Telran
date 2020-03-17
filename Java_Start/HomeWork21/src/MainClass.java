
public class MainClass {

	public static void main(String[] args) {
	
		Goods g = new Goods(10, "Cola", "ml.");
		System.out.println(g);
		Food f = new Food(10, "Wine", "ml.", true);
		System.out.println(f);
		MilkFood m = new MilkFood(15, "Tnuva", "l", true, "Natural", 3);
		System.out.println(m);
		Drinks d = new Drinks(8, "Sprit", "l", 	true, true);
System.out.println(d);
Alcohol a = new Alcohol(30, "Beluga", "ml.", true, false, 40);
System.out.println(a);
	}

}
