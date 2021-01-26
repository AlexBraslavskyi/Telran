package telran.spring.jpa.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import telran.spring.jpa.entities.Subject;

public interface SubjectRepository extends JpaRepository<Subject, Integer> {
	@Modifying
	@Query(value="delete from subjects where subject=:subject", nativeQuery = true)
//	  void deleteByStudentNameAndSubjectSubject(@Param("name") String name, @Param("subject") String subject);
//	@Query(value="delete s from Subject s where s=:subject")
	void deleteSubject(@Param("subject")String subject);
}
