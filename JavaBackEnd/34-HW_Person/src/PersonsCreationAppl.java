import static telran.util.RandomData.chance;
import static telran.util.RandomData.getRandomCity;
import static telran.util.RandomData.getRandomDate;
import static telran.util.RandomData.getRandomElement;
import static telran.util.RandomData.getRandomInt;
import static telran.util.RandomData.getRandomName;
import static telran.util.RandomData.getRandomStreet;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.stream.Stream;

import telran.person.dto.Address;
import telran.person.dto.Child;
import telran.person.dto.Employee;
import telran.person.dto.Person;

public class PersonsCreationAppl {

	private static final int EMPLOYEES_PROB = 70;
	private static final int MIN_YEAR = 1960;
	private static final int MAX_YEAR = 2020;
	private static final int MIN_ID = 100_000_000;
	private static final int MAX_ID = 900_000_000;
	private static final long N_PERSONS = 100;
	private static final int MIN_SALARY = 10000;
	private static final int MAX_SALARY = 20000;
	private static final String[] TITLES = { "QA Tester", "Java Developer", "Web Developer", "C# Developer",
			"UI/UX Designer", "Product Manager" };
	private static final String[] COMPANY = { "CheckPoint", "Applied Materials", "Prodware", "Amdocs", "Google",
			"Microsoft", "Epam", "jetBrains" };
	private static final String[] GARTEN = { "Garten - #1", "Garten - #2", "Garten - #3", "Garten - #4", "Garten - #5",
			"Garten - #6", "Garten - #7" };

	public static void main(String[] args) throws Exception {
		Person[] persons = getRandomPerson();
		try (ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream("persons.data"))) {
			stream.writeObject(persons);

		}
	}

	private static Person[] getRandomPerson() {

		return Stream.generate(() -> {
			return chance() <= EMPLOYEES_PROB ? getEmployees() : getChaild();
		}).limit(N_PERSONS).toArray(Person[]::new);
	}

	private static Person getEmployees() {
		String city = getRandomCity();
		String street = getRandomStreet();
		int building = getRandomInt(1, 200);
		int aprt = getRandomInt(1, 100);
		String company = getRandomElement(COMPANY);
		String title = getRandomElement(TITLES);
		return new Employee(getRandomInt(MIN_ID, MAX_ID), getRandomAddress(city, street, building, aprt),
				getRandomName(), getRandomDate(MIN_YEAR, MAX_YEAR - 18), company, title,
				getRandomInt(MIN_SALARY, MAX_SALARY));
	}

	private static Address getRandomAddress(String city, String street, int building, int aprt) {
		Address address = new Address(city, street, building, aprt);
		return address;
	}

	private static Person getChaild() {

		String city = getRandomCity();
		String street = getRandomStreet();
		int building = getRandomInt(1, 200);
		int aprt = getRandomInt(1, 100);
		String garten = getRandomElement(GARTEN);

		return new Child(getRandomInt(MIN_ID, MAX_ID), getRandomAddress(city, street, building, aprt), getRandomName(),
				getRandomDate(MAX_YEAR - 5, MAX_YEAR), garten);
	}

}
