package telran.measure;

public class Length {
	private float number;
	private LengthUnit unit;
	public Length(float number, LengthUnit unit) {
		super();
		this.number = number;
		this.unit = unit;
	}
	public float getNumber() {
		return number;
	}
	public void setNumber(float number) {
		this.number = number;
	}
	public LengthUnit getUnit() {
		return unit;
	}
	public void setUnit(LengthUnit unit) {
		this.unit = unit;
	}
	@Override
	public String toString(){
		//TODO
		return "";
	}
	public Length plus(Length length) {
		//TODO
		return null;
	}
	public Length minus(Length length) {
		//TODO
		return null;
	}
	public Length convert(LengthUnit otherUnit) {
		//TODO
		return null;
	}
	
	@Override
	public boolean equals(Object obj) {
		//TODO
		return false;
	}
	
}
