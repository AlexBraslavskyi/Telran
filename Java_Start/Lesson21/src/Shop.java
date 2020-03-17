
public class Shop extends Company {

	private String prodGroup;
	private String logo;
	
public Shop(){
	
}
	
public Shop (String name,String address, int id, String prodGroup, String logo){
		super(name, address, id);
		setProdGroup(prodGroup);
		setLogo(logo);
	}
	public String getProdGroup() {
		return prodGroup;
	}
	public void setProdGroup(String prodGroup) {
	if(prodGroup!=null)
		this.prodGroup = prodGroup;
	else
		System.out.println("Error");
	}
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		if(logo!=null)
		this.logo = logo;
		else
			System.out.println("Error");
	}
	@Override
	public String toString() {
	
		return super.toString()+" Product Group -" + prodGroup+" Logo -"+this.logo;
	}
	
	
	
	
}
