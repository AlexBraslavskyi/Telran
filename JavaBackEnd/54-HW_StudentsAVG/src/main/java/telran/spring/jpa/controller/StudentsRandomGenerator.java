package telran.spring.jpa.controller;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import telran.spring.jpa.dto.MarkDto;
import telran.spring.jpa.dto.StudentDto;
import telran.spring.jpa.dto.SubjectDto;
import telran.spring.jpa.service.interfaces.Students;

//@Component
public class StudentsRandomGenerator {
	List<String> names = Arrays.asList("Abraham", "Sarah", "Itshak", "Rahel", "Asaf", "Yacob","Rivka", "Yosef",
			"Benyanim", "Dan", "Ruben", "Moshe", "Aron", "Yehashua", "David", "Salomon", "Nefertity",
			"Naftaly", "Natan","Asher");
	List<String> subjects = Arrays.asList("Java core", "Java Technologies", 
			"Spring Data", "Spring Security", "Spring Cloud", "CSS", "HTML", "JS", "React", "Material-UI");
	private static final int N_MARKS = 20;
	@Autowired
	Students students;

	@PostConstruct
	void fillDB() {
		Stream.iterate(0, n -> n + 1).limit(names.size())
				.forEach(i -> students.addStudent(new StudentDto(i + 1, names.get(i))));
		int i[] = { 1 };
		subjects.forEach(s -> students.addSubject(new SubjectDto(i[0]++, s)));
		IntStream.range(0, N_MARKS).forEach(j -> students
				.addMark(new MarkDto(getRandomNumber(1, 20), getRandomNumber(1, 10), getRandomNumber(60, 100))));

	}

	int getRandomNumber(int min, int max) {
		return ThreadLocalRandom.current().nextInt(min,max+1);
	}

}
