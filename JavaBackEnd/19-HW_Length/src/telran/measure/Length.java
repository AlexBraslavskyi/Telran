package telran.measure;

import java.util.Locale;

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
		return String.format(Locale.ROOT, "%.1f%s",number,unit); // without Locale.ROOT on macOS getting "," and tests failing
	}
	public Length plus(Length length) {
		return new Length(number + length.convert(unit).number, unit); 
	}
	public Length minus(Length length) {
		
		return new Length(number - length.convert(unit).number, unit);
	}
	public Length convert(LengthUnit otherUnit) {
		return new Length(number * unit.getValue() / otherUnit.getValue(), otherUnit);
	}
	@Override
	public boolean equals(Object obj) {
		Length otherLength = (Length) obj;
		return unit == otherLength.unit && number == otherLength.number;
	}
	
}
