package telran.spring.jpa.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import telran.spring.jpa.dto.IntervalMarks;
import telran.spring.jpa.dto.MarkDto;
import telran.spring.jpa.dto.StudentDto;
import telran.spring.jpa.dto.StudentMarksCount;
import telran.spring.jpa.dto.SubjectDto;
import telran.spring.jpa.entities.Mark;
import telran.spring.jpa.entities.Student;
import telran.spring.jpa.entities.Subject;
import telran.spring.jpa.repo.MarkRepositoty;
import telran.spring.jpa.repo.StudentRepository;
import telran.spring.jpa.repo.SubjectRepository;
import telran.spring.jpa.service.interfaces.Students;
@Service
public class StudentsJpaImpl implements Students {
	@Autowired
	StudentRepository students;
	@Autowired
	SubjectRepository subjects;
	@Autowired
	MarkRepositoty marks;

	@Override
	public void addStudent(StudentDto studentDto) {
		Student student = new Student(studentDto.stid, studentDto.name);
		students.save(student);
	}

	@Override
	public void addSubject(SubjectDto subjectDto) {
		Subject subject = new Subject(subjectDto.suid, subjectDto.subject);
		subjects.save(subject);

	}

	@Override
	public void addMark(MarkDto markDto) {
		Student student = students.findById(markDto.stid).orElse(null);
		//if student null exception
		Subject subject = subjects.findById(markDto.suid).orElse(null);
		//if subject null exception
		Mark mark = new Mark(markDto.mark, student, subject);
		//if mark null exception
		marks.save(mark);
	}

	@Override
	@Transactional
	public void deleteMarks(String name, String subject) {
		marks.deleteMarks(name, subject);
		
	}
	@Override
	@Transactional
	public void deleteStudent(String name) {
//		Student student = students.findByName(name);
//		if (student != null) {
//			students.delete(student);
//		}
		students.deleteStudent(name);
	}
	@Override
	@Transactional
	public void deleteSubject(String subject) {
		subjects.deleteSubject(subject);
	}
	@Override
	@Transactional
	public void createTempTable() {
		marks.createTempTable();
	}

	@Override
	@Transactional
	public void delateAllDuplicates() {
		marks.delateAllDuplicates();
		
	}

	@Override
	@Transactional
	public void insert() {
		marks.insert();
		
	}


	@Override
	public List<String> bestStudents() {
		
		return marks.findBestStudents();
	}

	@Override
	public List<String> bestStudents(int nStudents) {
		
		return marks.findTopBestStudents(nStudents);
	}

	@Override
	public List<String> bestStudentsSubject(String subject) {
		
		return marks.findBestStudentsSubject(subject);
	}

	@Override
	public List<String> bestStudentsSubject(int nStudent, String subject) {
		
		return marks.findTopBestStudentsSubject(nStudent, subject);
	}

	@Override
	public List<StudentMarksCount> getStudentsMarksCount() {
		
		return marks.findStudentsMarksCount();
	}

	
	@Override
	public List<String> getSubjectsHighestMarks() {
	
		return marks.findSubjectsHighestMarks();
	}

	@Override
	public List<String> getTopSubjectsHighestMarks(int nSubjects) {
		
		return marks.findTopSubjectsHighestMarks(nSubjects);
	}

	@Override
	public List<Integer> getTopMarksEncountered(int nMarks, String subject) {
		
		return marks.findTopMarksEncountered(nMarks, subject);
	}

	@Override
	public List<IntervalMarks> getIntervalsMarks(int interval) {
	
		return marks.findIntervalsMarks(interval);
	}




}
