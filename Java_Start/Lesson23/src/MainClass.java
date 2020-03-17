

public class MainClass {

	public static void main(String[] args) {
	Manager m = new Manager("Ivanov Ivan",123456,"Manager",5800, 3);
	Manager m5 = new Manager("Ivanov Ivan",1236,"Manager",5800, 3);
	WageEmployee w = new WageEmployee("Petrov Petr",3256,"Saler",0,0,32);
	w.setHours(180);
	Manager m2 = new Manager("Sidorov Sidor",3241564,"Manager",5800,4);
	SalesMan s = new SalesMan("Vasilev Vasiliy", 12365456, "Saler", 5300, 5, 0);
		Manager m3 = new Manager("Ivanov Ivan",13456,"Manager",5800, 3);
	s.setTotalSales(250000);
	
		MilkFood mf = new MilkFood("Milk",10,"L",true,"Natural milk",3);
		MeatFood mf1 = new MeatFood ("Chicken grill",20,"gr",false,"Meat-chicken");
		Alcohol a = new Alcohol("Beluga", 50, "L",true,false,40);
		Drink d = new Drink("Sprite",10,"L",true,true);

		Employee [] staff = {m,m2,w,s};
		Good [] goods = {mf,mf1,a,d};

	Minimarket mini = new Minimarket("Rami Levi", "Rishon");
	mini.addAllGoods(goods);
	mini.addAllStaff(staff);
	mini.displayMinimarket();
	System.out.println();
	mini.printAllSalaries();


	mini.addEmployee(staff,m5);
		//mini.addEmployee(staff,m3);
		mini.displayMinimarket();
		System.out.println();

	mini.delEmployee(staff,12365456);
	mini.displayMinimarket();


	}

}
