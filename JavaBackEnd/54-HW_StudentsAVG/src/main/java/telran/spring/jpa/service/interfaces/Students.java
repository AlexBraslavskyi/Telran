package telran.spring.jpa.service.interfaces;

import java.util.List;

import telran.spring.jpa.dto.IntervalMarks;
import telran.spring.jpa.dto.MarkDto;
import telran.spring.jpa.dto.StudentDto;
import telran.spring.jpa.dto.StudentMarksCount;
import telran.spring.jpa.dto.SubjectDto;

public interface Students {
	void addStudent(StudentDto studentDto);
	void addSubject(SubjectDto subjectDto);
	void addMark(MarkDto markDto);
	void deleteMarks(String name, String subject);
	void deleteStudent(String name);
	void deleteSubject(String subject);
	void createTempTable();
	void delateAllDuplicates();
	void insert();
	List<String> bestStudents();//List of student names having avg mark > then the total one
	List<String> bestStudents(int nStudents); //list of nStudents best student names based on avg marks
	List<String> bestStudentsSubject(String subject); //list of student names having avg mark on a given 
	//subject higher then total avg subject for all students
	List<String> bestStudentsSubject(int nStudent, String subject);
	List<StudentMarksCount> getStudentsMarksCount();
	
	//Homework
	List<String> getSubjectsHighestMarks();
	List<String> getTopSubjectsHighestMarks(int nSubjects);
	List<Integer> getTopMarksEncountered(int nMarks, String subject);
	List<IntervalMarks> getIntervalsMarks(int interval);

}
