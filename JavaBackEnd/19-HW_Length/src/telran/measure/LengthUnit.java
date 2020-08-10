package telran.measure;

public enum LengthUnit {
	MM(1f),CM(10f),IN(25.4f),FT(304.8f),M(1000f), KM(1_000_000f);
	private float value;
	private LengthUnit(float value){
		this.value=value;
	}
	public float getValue(){
		return value;
	}
	public float between(Length l1, Length l2){
	
		return 0;
	}
	
}
