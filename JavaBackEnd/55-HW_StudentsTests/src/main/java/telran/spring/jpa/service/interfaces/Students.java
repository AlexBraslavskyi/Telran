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
	void deleteStudent(String string);
	void deleteSubject(String subject);
	void averagingSubjectMarks();
	List<String> bestStudents(); //list of student names having average mark higher than the total one
	List<String> bestStudents(int nStudents); //list of nStudents best student names based on average marks
	List<String> bestStudentsSubject(String subject); //list of student names having average mark on a given subject higher than the total average mark on that subject
	List<String> bestStudentsSubject(int nStudents, String subject);
	List<StudentMarksCount> getStudentsMarksCount();
	List<String> getSubjectsHighestMarks();
	List<String> getTopSubjectsHighestMarks(int nSubjects);
	List<Integer> getTopMarksEncountered(int nMarks, String subject);
	List<IntervalMarks> getIntervalsMarks(int interval);
	
	

	

}
