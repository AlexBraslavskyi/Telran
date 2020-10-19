package telran.person.dto;

import java.time.LocalDate;

public class Child extends Person {
	String garten;
	
	public Child() {
		
	}
	public Child(int id, Address adress, String name, LocalDate birthDate, String garten) {
		super(id, adress, name, birthDate);
		this.garten = garten;
	}


	@Override
	public String toString() {
		return "Child [garten=" + garten + ", id=" + id + ", adress=" + adress + ", name=" + name + ", birthDate="
				+ birthDate + "]";
	}
	public String getGarten() {
		return garten;
	}
	public void setGarten(String garten) {
		this.garten = garten;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;



}
