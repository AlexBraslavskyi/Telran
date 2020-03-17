
public class MainClass {

	public static void main(String[] args) {
	Manager m = new Manager("Ivanov Ivan",123456,"Manager",5800, 3);
	WageEmployee w = new WageEmployee("Petrov Petr",3256,"Salar",5300,180,32);
	Manager m2 = new Manager("Sidorov Sidor",3241564,"Manager",5800,4);
	SalesMan s = new SalesMan("Vasilev Vasiliy", 12365456, "Saler", 5300, 5, 50000);
		MilkFood mf = new MilkFood("Milk",10,"L",true,"Natural milk",3);
		MeatFood mf1 = new MeatFood ("Chicken grill",20,"gr",false,"Meat-chicken");
		Alcohol a = new Alcohol("Beluga", 50, "L",true,false,40);
		Drink d = new Drink("Sprite",10,"L",true,true);

		Employee [] staff = {m,w,m2,s};
		Good [] goods = {mf,mf1,a,d};

	Minimarket mm = new Minimarket("Rami Levi", "Rishon", goods, staff);
		System.out.println(mm);

		//Good gd1 = new Good("Mango", 10.5, "kg");
		//Good gd2 = new Good("Apple", 8.5, "kg");	

		//boolean res = gd1.equals(gd2);
		//System.out.println(res);
		
	}

}
