
public abstract class Good  {

	private String name;
	private double price;
	private String mItem;
	
	public Good() {

	}

	public Good(String name, double price, String mItem) {
		setName(name);
		setPrice(price);
		setmItem(mItem);
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
	if(name!=null)
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		if(price!=0)
		this.price = price;
	}
	public String getmItem() {
		return mItem;
	}
	public void setmItem(String mItem) {
		if(mItem!=null)
		this.mItem = mItem;
	}

	@Override
	public boolean equals(Object obj) {
Good gd = (Good)obj;

		if(name.equalsIgnoreCase(gd.name)&&mItem.equalsIgnoreCase(gd.mItem))
	return true;
		else
		return false;
	}

	@Override
	public String toString() {
		return "Name: "+ name+" Price: " +price+" MegItem: "+ mItem;
	}

	
	
	
	
}
