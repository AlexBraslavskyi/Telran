
public class MainCompany {

	public static void main(String[] args) {

		Company c = new Company("IBM","Petakh-Tikva",456478);
		//String s = c.toString();
		System.out.println(c);
		Shop sp = new Shop("Zara Group", "Spain", 5464, "clothes", "Zara");
System.out.println(sp);
NetShop ns = new NetShop("Zara Group", "Spain", 5464, "clothes", "Zara","zara.com");
System.out.println(ns);

Object obj = ns;
Company comp = ns;  //up casting privedenie tipov 

Object obj1 = new Object();
//ns = (NetShop) obj1; // down casting 
	ns = (NetShop) obj;
	System.out.println(ns);
	}

}
