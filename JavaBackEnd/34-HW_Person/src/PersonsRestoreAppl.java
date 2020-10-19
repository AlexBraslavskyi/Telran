import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.IntSummaryStatistics;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import telran.person.dto.Employee;
import telran.person.dto.Person;

public class PersonsRestoreAppl {

	public static void main(String[] args) throws Exception {

		Person[] persons;
		try (ObjectInputStream stream = new ObjectInputStream(new FileInputStream("persons.data"))) {
			persons = (Person[]) stream.readObject();
		}

		displayMostPopularCities(persons);
		System.out.println("*".repeat(200) + "\n");
		displayAvrSalaryByCompany(persons);
		System.out.println("*".repeat(200) + "\n");
		displayEmployeesSalaryMoreAvr(persons);

	}

	private static void displayMostPopularCities(Person[] persons) {

				Stream.of(persons)
				.collect(Collectors.groupingBy(p -> p.getAdress().getCity(), Collectors.counting()))
				.entrySet().stream().sorted((e1,e2)->e2.getValue().compareTo(e1.getValue())).limit(3)
				.forEach(c->System.out.printf("%s : %s\n",c.getValue(),c.getKey()));
	}

	private static void displayAvrSalaryByCompany(Person[] persons) {

		Map<Object, Double> avrSalaryByCompany = Stream.of(persons)
				.filter(p -> {return p instanceof Employee;}).map(p -> (Employee) p)
				.collect(Collectors.groupingBy(Employee::getCompany, Collectors.averagingInt(Employee::getSalary)));

		System.out.println(avrSalaryByCompany);

	}

	private static void displayEmployeesSalaryMoreAvr(Person[] persons) {
		IntSummaryStatistics stats = Stream.of(persons).filter(p -> {return p instanceof Employee;})
				.mapToInt(p -> ((Employee) p).getSalary()).summaryStatistics();

		Stream.of(persons).filter(p -> {return p instanceof Employee;})
				.filter(p -> ((Employee) p).getSalary() > stats.getAverage())
				.forEach(System.out::print);

	}
}
