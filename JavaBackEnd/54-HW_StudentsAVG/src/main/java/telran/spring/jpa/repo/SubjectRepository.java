package telran.spring.jpa.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import telran.spring.jpa.entities.Student;
import telran.spring.jpa.entities.Subject;

public interface SubjectRepository extends JpaRepository<Subject, Integer> {
	@Query(value="select * from subject where subject=:subject", nativeQuery = true)
	Student findBySubject(@Param("subject")String subject);
	
	/*******************************************************************************/
	@Modifying
	@Query(value="delete from subjects where subject=:subject", nativeQuery = true)
	void deleteSubject(@Param("subject")String subject);
}
