package telran.spring.jpa.service.impl;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import telran.spring.jpa.dto.IntervalMarks;
import telran.spring.jpa.dto.MarkDto;
import telran.spring.jpa.dto.StudentDto;
import telran.spring.jpa.dto.StudentMarkSubjectAvg;
import telran.spring.jpa.dto.StudentMarksCount;
import telran.spring.jpa.dto.SubjectDto;
import telran.spring.jpa.entities.Mark;
import telran.spring.jpa.entities.Student;
import telran.spring.jpa.entities.Subject;
import telran.spring.jpa.repo.MarkRepository;
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
MarkRepository marks;
	@Override
	@Transactional
	public void addStudent(StudentDto studentDto) {
		Student student = new Student(studentDto.stid, studentDto.name);
		students.save(student);
		

	}

	@Override
	@Transactional
	public void addSubject(SubjectDto subjectDto) {
		Subject subject = new Subject(subjectDto.suid, subjectDto.subject);
		subjects.save(subject);

	}

	@Override
	@Transactional
	public void addMark(MarkDto markDto) {
		Student student = students.findById(markDto.stid).orElse(null);
		Subject subject = subjects.findById(markDto.suid).orElse(null);
		Mark mark = new Mark(markDto.mark, student, subject);
		marks.save(mark);

	}

	@Override
	@Transactional
	public void deleteMarks(String name, String subject) {
		marks.deleteMarks(name, subject);
		
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
	public List<String> bestStudentsSubject(int nStudents, String subject) {
		
		return marks.findTopBestStudentsSubject(nStudents, subject);
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
//		return marks.findTopMarksEncountered(subject, PageRequest.of(0, nMarks));
		return marks.findTopMarksEncountered(nMarks,subject);
	}

	@Override
	public List<IntervalMarks> getIntervalsMarks(int interval) {
		
		return marks.findIntervalsMarks(interval);
	}

	@Override
	@Transactional
	public void deleteStudent(String name) {
		
		students.deleteStudent(name);
		
		
	}
	@Override
	@Transactional
	public void deleteSubject(String subject) {
		
		subjects.deleteSubject(subject);
		
		
	}

	@Override
	@Transactional
	public void averagingSubjectMarks() {
		List<StudentMarkSubjectAvg> dataFromQuery = marks.findSubjectsMarksAvg();
		dataFromQuery.forEach(sms -> {
			String name = students.findById(sms.getStid()).orElse(null).getName();
			String subject = subjects.findById(sms.getSuid()).orElse(null).getSubject();
			marks.deleteMarks(name, subject);
			addMark(new MarkDto(sms.getStid(), sms.getSuid(), sms.getAvgMark()));
		});
		
		
	}


	


}
