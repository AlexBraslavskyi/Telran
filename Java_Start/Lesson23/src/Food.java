
public abstract class Food extends Good {

	
	private boolean isKosher;

	
	
	public Food() {

	}

	public Food(String name, double price, String mItem,boolean isKosher) {
		super(name, price, mItem);
		this.isKosher=isKosher;
	}

	public boolean isKosher() {
		return isKosher;
	}

	public void setKosher(boolean isKosher) {
		this.isKosher = isKosher;
	}

	@Override
	public boolean equals(Object obj) {
		Food fd = (Food)obj;
		if(super.equals(fd)==true&&isKosher == fd.isKosher)
			return true;
		return false;
	}

	@Override
	public String toString() {
		String kosher;
		if(isKosher == true){
			kosher = " Kosher ";
		}
			else{
			kosher = " Not kosher ";
			}
		return super.toString()+kosher;
	}
	
	
}
