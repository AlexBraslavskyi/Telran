package telran.spring.jpa.repo;


import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import telran.spring.jpa.dto.IntervalMarks;
import telran.spring.jpa.dto.StudentMarkSubjectAvg;
import telran.spring.jpa.dto.StudentMarksCount;
import telran.spring.jpa.entities.Mark;
public interface MarkRepository extends JpaRepository<Mark, Integer> {
	public static final EntityManager em = null;
	@Query(value="select name from students join marks"
			+ " on stid=student_stid group by name having avg(mark) > "
			+ " (select avg(mark) from marks) order by avg(mark) desc", nativeQuery = true)
//	@Query(value="select s.name from Student s join Mark m "
//			+ " on s=m.student group by s.name having avg(m.mark) > "
//			+ " (select avg(m.mark) from m) order by avg(m.mark) desc")
	List<String> findBestStudents();
	/******************************************************************/
	@Query(value="select name from students join marks"
			+ " on stid=student_stid group by name "
			+ "  order by avg(mark) desc limit :n_students", nativeQuery = true)
	List<String> findTopBestStudents(@Param("n_students") int nStudents);
	/****************************************************************************/
	@Query(value="select name from students_marks_subjects where subject=:subject "
			+ "group by name having avg(mark) >= (select avg(mark) from students_marks_subjects "
			+ "where subject=:subject) order by avg(mark)", nativeQuery = true)
	List<String> findBestStudentsSubject(@Param("subject") String subject);
	/*********************************************************************************/
	@Query(value="select name from students_marks_subjects where subject=:subject "
			+ "group by name order by avg(mark) desc limit :n_students "
			, nativeQuery = true)
	List<String> findTopBestStudentsSubject(@Param("n_students") int nStudents, @Param("subject") String subject);
	@Query(value="select name, count(mark) as marksCount from students left join marks on "
			+ "stid=student_stid group by name", nativeQuery = true)
//	  Long countMarksGroupByStudentNameAndSubjectSubject(@Param("student_name") String name,
//			    @Param("subject_name") String subject);
	List<StudentMarksCount> findStudentsMarksCount();
	/***********************************************************************/
	
	
	/*****************/
	/*      HW       */
	/*****************/
	@Query(value="select subject from marks_subjects group by subject order by sum(mark) desc limit :n_subjects", nativeQuery = true)
List<String> findTopSubjectsHighestMarks(@Param("n_subjects")int nSubjects);
/**********************************************************************************/
@Query(value="select subject from marks_subjects group by subject having sum(mark) >"
		+ " (select avg(total) from (select sum(mark) as total from marks_subjects group by subject )as totals) order by sum(mark) desc", nativeQuery = true)
List<String> findSubjectsHighestMarks();
/***************************************************************************************************/
@Query(value="select mark from marks_subjects where subject=:subject group by mark"
		+ " order by count(*) desc, mark asc limit :n_marks", nativeQuery=true)
List<Integer>findTopMarksEncountered(@Param("n_marks")int nMarks, @Param("subject") String subject);
//@Query(value = "select m.mark from Mark m where m.subject.subject=:subject group by m.mark "
//	    + "order by count(*) desc,m.mark desc")
//    List<Integer> findTopMarksEncountered(@Param("subject") String subject, Pageable pageable);
/********************************************************************************************************/
@Query(value="select mark/:interval * :interval as min, :interval * (mark/:interval + 1) - 1 as max, count(*) as occurrences from " + 
		" marks group by min, max  order by min", nativeQuery = true)
//@Query(value = "select m.mark/:interval * :interval as minVal, :interval * (m.mark/:interval + 1) - 1 as maxVal, "
//	    + "count(*) as occurrences " + "from  Mark m group by minVal, maxVal order by minVal")
List<IntervalMarks> findIntervalsMarks(@Param("interval")int interval);

/***********************************************************************************/
@Modifying
@Query(value="delete from marks where student_stid=(select stid from students where "
		+ "name=:name) and subject_suid=(select suid from subjects where subject=:subject)",
		nativeQuery = true)
//@Query(value="delete from Mark  m where m.student=(select s from Student s where "
//		+ "s.name=:name) and m.subject=(select su from Subject su where su.subject=:subject)")
//void deleteByStudentStidAndSubjectSuid(@Param("stid") int stid, @Param("suid") int suid);
void deleteMarks(String name, String subject);
/*******************************************************************/
@Query(value="select stid, suid,  avg(mark) as avgMark "
		+ "from students_marks_subjects group by stid, suid having count(*) > 1", nativeQuery = true)
//@Query(value = "SELECT  m.student.stid AS stid , m.subject.suid AS suid, COUNT(*) AS count,"
//	    + "ROUND(AVG(m.mark)) AS avgMark  FROM Mark AS m  GROUP BY m.student.stid,m.subject.suid "
//	    + "HAVING COUNT(*) > 1")
List<StudentMarkSubjectAvg> findSubjectsMarksAvg();

}
