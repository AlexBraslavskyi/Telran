package telran.person.dto;

import java.io.Serializable;
import java.time.LocalDate;

public class Person implements Serializable {
	/**
		 * 
		 */
	private static final long serialVersionUID = 1L;
	int id;
	Address adress;
	String name;
	LocalDate birthDate;

	public Person() {

	}

	public Person(int id, Address adress, String name, LocalDate birthDate) {
		super();
		this.id = id;
		this.adress = adress;
		this.name = name;
		this.birthDate = birthDate;
	}

	public int getId() {
		return id;
	}

	public Address getAdress() {
		return adress;
	}

	public String getName() {
		return name;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setAdress(Address adress) {
		this.adress = adress;
	}

}
