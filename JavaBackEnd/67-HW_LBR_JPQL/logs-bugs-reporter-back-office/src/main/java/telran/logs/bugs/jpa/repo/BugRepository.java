package telran.logs.bugs.jpa.repo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import telran.logs.bugs.dto.BugStatus;
import telran.logs.bugs.dto.EmailBugsCount;
import telran.logs.bugs.dto.Seriousness;
import telran.logs.bugs.dto.SeriousnessBugCount;
import telran.logs.bugs.jpa.entities.Bug;

public interface BugRepository extends JpaRepository<Bug, Long> {

	List<Bug> findByProgrammerId(long programmerId);

	List<Bug> findByStatus(BugStatus status);

	List<Bug> findByStatusNotAndDateOpenBefore(BugStatus status, LocalDate dateOpen);

	@Query("select programmer.email as email, count(b) as count from Bug b right join b.programmer programmer "
			+ "group by email order by count(b) desc")
	List<EmailBugsCount> emailBugsCounts();

	@Query(value = "select b.programmer.name as name from Bug b group by name order by count(b) desc")
	List<String> programmersMostBug(Pageable pageable);

	@Query(value = "select b.programmer.name as name from Bug b group by name order by count(b) asc")
	List<String> programmersLeastBug(Pageable pageable);

	@Query("select b.seriousness as seriousness, count(b) as count from Bug b group by seriousness order by count(b) desc")
	List<SeriousnessBugCount> seriousnessBugCount();

	@Query("select b.seriousness as s from Bug b group by s order by count(b) desc")
	List<Seriousness> seriousnessMostBugs(Pageable pageable);

}
