
public class Goods {

	
	private int price;
	private String name;
	private String megItem;
	
	
	public Goods() {
		
	}
	
	public Goods(int price, String name, String megItem) {
	setPrice(price);
	setName(name);
	setMegItem(megItem);
	}

	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		if(price!=0)
		this.price = price;
		else
			System.out.println("Error: wrong price");
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		if(name!=null)
		this.name = name;
		else
			System.out.println("Error: wronge name");
	}
	public String getMegItem() {
		return megItem;
	}
	public void setMegItem(String megItem) {
		if(megItem!=null)
		this.megItem = megItem;
		else 
			System.out.println("Error: wronge megerment item");
	}

	@Override
	public String toString() {
		return "Price : "+price+" Name : "+name+" Megerment Item: "+megItem;
	}
	
	
	
	
}
