
public enum WeightUnit {
	
	GR(1.0f), KG(1000.0f), TN(1_000_000f), LBS(453.592f), MGR(0.001f), CN(100000.0f);
	private WeightUnit(float value) {
		this.value = value;
	}

	private float value;
	public float getGrammsValue() {
		
		return value;
	}
	public float convert(int amount, WeightUnit other) {
		return amount * value / other.value;
	}

}
