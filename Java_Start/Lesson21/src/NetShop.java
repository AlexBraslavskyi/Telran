
public class NetShop extends Shop {

	
	private String url;

	public NetShop(){
		
	}
	
	
	public NetShop(String name, String address, int id, String prodGroup,
			String logo, String url) {
		super(name, address, id, prodGroup, logo);
		setUrl(url);
	}


	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		if(url !=null)
		this.url = url;
		else
			System.out.println("Error");
	}


	@Override
	public String toString() {
	
		return super.toString()+ " URL - " + url;
	}
	
	
	
}
