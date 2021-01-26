package telran.spring.jpa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import telran.spring.jpa.dto.IntervalMarks;
import telran.spring.jpa.dto.StudentMarksCount;
import telran.spring.jpa.service.interfaces.Students;

@RestController
public class StudentsController {
	@Autowired
	Students students;
	@GetMapping("/students/best")
List<String> bestStudentNames(@RequestParam (name = "n_students", defaultValue = "0")int nStudents,
		@RequestParam(name="subject", required = false) String subject) {
	if(nStudents == 0 && subject == null) {
		return students.bestStudents();
	}
	if(nStudents == 0) {
		return students.bestStudentsSubject(subject);
	}
	if(subject == null) {
		return students.bestStudents(nStudents);
	}
	return students.bestStudentsSubject(nStudents, subject);
}
	@GetMapping("/students/marks/count")
	List<StudentMarksCount> studentsMarksCount() {
		return students.getStudentsMarksCount();
	}
	@GetMapping("/subjects/marks/highest")
	List<String> subjectsMarksHighest(@RequestParam(name="n_subjects", defaultValue = "0") int nSubjects) {
	   return nSubjects == 0 ? students.getSubjectsHighestMarks() :
		   students.getTopSubjectsHighestMarks(nSubjects);
	}
	@GetMapping("/marks/widespread/{subject}")
	List<Integer> marksSubject(@RequestParam(name="n_marks", required = true)int nMarks,
			@PathVariable("subject") String subject) {
		return students.getTopMarksEncountered(nMarks, subject);
	}
	@GetMapping("/marks/distribution")
	List<IntervalMarks> marksDistribution(@RequestParam(name="interval", defaultValue = "10") int interval){
		return students.getIntervalsMarks(interval);
	}
	@DeleteMapping("/marks/{subject}")
	void deleteMarks(@PathVariable("subject") String subject,
			@RequestParam(name="name", required = true) String name) {
		students.deleteMarks(name, subject);
		
	}
	@DeleteMapping("/subject/del/{subject}")
	void deleteSubject(@PathVariable("subject") String subject) {
		students.deleteSubject(subject);
		
	}
	@DeleteMapping("/student/del/{student}")
	void deleteStudent(@PathVariable("student") String student) {
		students.deleteStudent(student);
		
	}
	@PutMapping("/subjects/marks/averaging")
	void averagingRepeatedMarks() {
		students.averagingSubjectMarks();
	}
	
}
