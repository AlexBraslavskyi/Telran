
public class SalesMan extends Employee {

	private double bonus;
	private double totalSales;
	
	
	
	
	
	public SalesMan() {

	}

	public SalesMan(String name, int id, String position, double baseSalary, double bonus, double totalSales) {
		super(name, id, position, baseSalary);
	setBonus(bonus);
	setTotalSales(totalSales);
	}

	public double getBonus() {
		return bonus;
	}

	public void setBonus(double bonus) {
		this.bonus = bonus;
	}

	public double getTotalSales() {
		return totalSales;
	}

	public void setTotalSales(double totalSales) {
		this.totalSales = totalSales;
	}


	@Override
	public double calculateSalary() {
	
		return getBaseSalary()+((totalSales*bonus)/100);
	}

	@Override
	public String toString() {
		return super.toString()+ " Bonus: " +bonus+" Total Sales: "+ totalSales;
	}

}
