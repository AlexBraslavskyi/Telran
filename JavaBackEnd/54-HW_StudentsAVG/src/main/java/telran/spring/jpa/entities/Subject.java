package telran.spring.jpa.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name="subjects")
public class Subject {
	@Id
	int suid;
	@Column(unique=true, nullable = false)
	String subject;
	@OneToMany(mappedBy = "subject", cascade = CascadeType.REMOVE)
	@OnDelete(action = OnDeleteAction.CASCADE)
	List<Mark> marks;
	public Subject() {
		
	}
	public Subject(int suid, String subject) {
		this.suid = suid;
		this.subject = subject;
	}	
}
