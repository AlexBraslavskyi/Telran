package telran.spring.jpa.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import telran.spring.jpa.dto.IntervalMarks;
import telran.spring.jpa.dto.StudentMarksCount;
import telran.spring.jpa.entities.Mark;

public interface MarkRepositoty extends JpaRepository<Mark, Integer> {
	@Query(value="select name from students join marks"
			+ " on stid=student_stid group by name having avg(mark) >"
			+ " (select avg(mark) from marks) order by avg(mark) desc", nativeQuery = true)
	List<String> findBestStudents();
	/******************************************************************/
	@Query(value="select name from students join marks"
			+ " on stid=student_stid group by name "
			+ "  order by avg(mark) desc limit :n_students", nativeQuery = true)
	List<String> findTopBestStudents(@Param("n_students") int nStudents);
	/****************************************************************************/
	@Query(value="select name from students_marks_subjects where subject=:subject "
			+ "group by name having avg(mark) > (select avg(mark) from students_marks_subjects "
			+ "where subject=:subject) order by avg(mark)", nativeQuery = true)
	List<String> findBestStudentsSubject(@Param("subject") String subject);
	/*********************************************************************************/
	@Query(value="select name from students_marks_subjects where subject=:subject "
			+ "group by name order by avg(mark) desc limit :n_students "
			, nativeQuery = true)
	List<String> findTopBestStudentsSubject(@Param("n_students") int nStudents, @Param("subject") String subject);
	@Query(value="select name, count(mark) as marksCount from students left join marks on "
			+ "stid=student_stid group by name", nativeQuery = true)
	List<StudentMarksCount> findStudentsMarksCount();
	
	/***************/
	/* -Homework- */
	/**************/
	
	
	@Query(value = "select subject from subjects join marks on suid=subject_suid "
			+ "GROUP by subject order by sum(mark) desc", nativeQuery = true)
	List<String> findSubjectsHighestMarks();
	/*********************************************************************/
	@Query(value = "select subject from subjects join marks on suid=subject_suid "
			+ "GROUP by subject order by sum(mark) desc limit :n_subjects", nativeQuery = true)
	List<String> findTopSubjectsHighestMarks(@Param("n_subjects")int nSubjects);
	/********************************************************************/
	@Query(value = "select mark from marks join subjects s on suid=subject_suid where "
			+ "s.subject=:subject GROUP by mark order by count(mark) desc limit :n_marks",nativeQuery = true)
	List<Integer>findTopMarksEncountered(@Param("n_marks")int nMarks, @Param("subject")String subject);
	/********************************************************************/
	@Query(value="select (mark/ :m_interval) * :m_interval as min, "
			+ "(mark/ :m_interval)* :m_interval+ :m_interval-1 as max, count(*) as marksCount "
			+ "from marks "
			+ "group by min,max "
			+ "order by 1"
			,nativeQuery = true)
	List<IntervalMarks> findIntervalsMarks(@Param("m_interval")int interval);
}
