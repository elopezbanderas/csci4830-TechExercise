package datamodel;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @since J2SE-1.8
 CREATE TABLE transcript (
  nuid INT NOT NULL,
  course_name varchar(15) NOT NULL,
  section int NOT NULL,
  semester varchar(8) NOT NULL,
  title varchar(50),
  credit_hours int,
  grade varchar(2),
  PRIMARY KEY (nuid, course_name, section, semester),
 */
@Entity
@Table(name = "Courses")
public class Course implements Serializable{
	
   /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

@Id //primary key
   @Column(name = "id")
   private Integer id;
   
   @Id //primary key
   @Column(name = "course_id")
   private String course_id;
   
   @Id //primary key
   @Column(name = "section")
   private String section;
   
   @Id //primary key
   @Column(name = "semester")
   private String semester;

   @Column(name = "title")
   private String title;

   @Column(name = "credit_hours")
   private Integer credit_hours;
   
   @Column(name = "grade")
   private String grade;

   public Course() {
   }

public Course(Integer nuid, String course_id, String section, String semester, String title, Integer credit_hours,
		String grade) {
	super();
	this.id = nuid;
	this.course_id = course_id;
	this.section = section;
	this.semester = semester;
	this.title = title;
	this.credit_hours = credit_hours;
	this.grade = grade;
}

public Integer getId() {
	return id;
}

public void setId(Integer nuid) {
	this.id = nuid;
}

public String getCourse_id() {
	return course_id;
}

public void setCourse_id(String course_id) {
	this.course_id = course_id;
}

public String getSection() {
	return section;
}

public void setSection(String section) {
	this.section = section;
}

public String getSemester() {
	return semester;
}

public void setSemester(String semester) {
	this.semester = semester;
}

public String getTitle() {
	return title;
}

public void setTitle(String title) {
	this.title = title;
}

public Integer getCredit_hours() {
	return credit_hours;
}

public void setCredit_hours(Integer credit_hours) {
	this.credit_hours = credit_hours;
}

public String getGrade() {
	return grade;
}

public void setGrade(String grade) {
	this.grade = grade;
}

@Override
public String toString() {
	return "Course [nuid=" + id + ", course_id=" + course_id + ", section=" + section + ", semester=" + semester
			+ ", title=" + title + ", credit_hours=" + credit_hours + ", grade=" + grade + "]";
}

   
}