package telran.spring.jpa.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="marks")
public class Mark {
	@GeneratedValue
	@Id
	int id;
	int mark;
	@ManyToOne
	Student student;
	@ManyToOne
	Subject subject;
	public Mark(int mark, Student student, Subject subject) {
		this.mark = mark;
		this.student = student;
		this.subject = subject;
	}
	public Mark() {
		
	}
}
