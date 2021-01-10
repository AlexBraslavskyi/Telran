package telran.spring.jpa.entities;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="students")
public class Student {
@Id
int stid;
@Column(unique=true, nullable = false)
String name;
public Student() {
	
}
public Student(int stid, String name) {
	this.stid = stid;
	this.name = name;
}

	
}
