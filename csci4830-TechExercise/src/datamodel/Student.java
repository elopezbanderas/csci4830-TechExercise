package datamodel;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @since J2SE-1.8
 CREATE TABLE transcript (
  id INT NOT NULL,
  first_name varchar(30),
  last_name varchar(30) NOT NULL,
  PRIMARY KEY (id));
 */
@Entity
@Table(name = "Students")
public class Student implements Serializable{
	
   /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

@Id //primary key
   @Column(name = "id")
   private Integer id;

   @Column(name = "first_name")
   private String first_name;
   
   @Column(name = "last_name")
   private String last_name;

   public Student() {
   }

public Student(Integer id, String first_name, String last_name) {
	super();
	this.id = id;
	this.first_name = first_name;
	this.last_name = last_name;
}

public Integer getId() {
	return id;
}

public void setId(Integer id) {
	this.id = id;
}

public String getFirst_name() {
	return first_name;
}

public void setFirst_name(String first_name) {
	this.first_name = first_name;
}

public String getLast_name() {
	return last_name;
}

public void setLast_name(String last_name) {
	this.last_name = last_name;
}

@Override
public String toString() {
	return "Student [id=" + id + ", first_name=" + first_name + ", last_name=" + last_name + "]";
}
   
   

}