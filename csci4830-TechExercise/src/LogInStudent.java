import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datamodel.Course;
import datamodel.Student;
import util.Info;
import util.UtilDBLopezBanderas;

@WebServlet("/LogInStudent")
public class LogInStudent extends HttpServlet implements Info {
   private static final long serialVersionUID = 1L;

   public LogInStudent() {
      super();
   }

   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      String nuid = request.getParameter("nuid").trim();

      response.setContentType("text/html");
      PrintWriter out = response.getWriter();
      
      String docType = "<!doctype html public \"-//w3c//dtd html 4.0 transitional//en\">\n"; //
      out.println(docType + //

				"<html><head><title>Account Page</title>"+
				"<link rel=\"stylesheet\" type=\"text/css\" href=\"style.css\" /></head>\n");
      
      List<Student> ListAccount = UtilDBLopezBanderas.getAccount(nuid);
      
      if(ListAccount.size()==0) {
    	  out.println("<body><div id=\"container\">");
    	  out.println("<div id=\"header\"><h1>Student Academic Tracker</h1></div>");
    	  out.println("<div id=\"content\">");
    	  out.println("<div id=\"nav\"><h3>Navigation</h3><ul>");
    	  out.println("<li><a href=\"/csci4830-TechExercise/main.html\">Home Page</a> <br></li>");
    	  out.println("<li><a href=\"/csci4830-TechExercise/createAccount.html\">Create New Account</a> <br></li></ul></div>");
    	  out.println("<div id=\"main\"><h2>Account Does Not Exist!</h2><p>The NUID you submitted does not correspond to any existing student account.</p>");
    	  out.println("<p>Try logging in again with a different NUID, or create a new student account.</p></div></div>");
    	  out.println("</div><div id=\"footer\">Copyright</div></div></body></html>");

  		  return;
      }
    
      Student student = ListAccount.get(0);
      
      out.println("<body><div id=\"container\">");
	  out.println("<div id=\"header\"><h1>" + student.getFirst_name()+ "'s Academic Tracker" + "</h1></div>");
	  out.println("<div id=\"content\">");
	  out.println("<div id=\"nav\"><h3>Navigation</h3><ul>");
	  out.println("<li><a href=\"/csci4830-TechExercise/main.html\">Home Page</a> <br></li>");
	  out.println("<li><a href=\"/csci4830-TechExercise/addCourse.jsp?nuid="+ nuid +"\">Add Course</a> <br></li></ul></div>");
      
      List<Course> listCourses = UtilDBLopezBanderas.listCourses(nuid);
      
      if(listCourses.size()==0) {  
    	  out.println("<div id=\"main\"><h2>Courses Taken:</h2><ul><p>You have not registered any courses!</p>");
    	  out.println("<p>Click Add Course to begin your academic tracking!</p></div></div>");
    	  out.println("</div><div id=\"footer\">Copyright</div></div></body></html>");

  		  return;
      }
      
      
      display(listCourses, out);
   } 

   void display(List<Course> listCourses, PrintWriter out) {
	  double gpa = 0;
	  double totalCredits = 0;
	  double totalPoints = 0;
	  int failCount = 0;
	  out.println("<div id=\"main\"><h2>Courses Taken:</h2><ul>");
 	  
	  out.println("<table><tr><th>Course</th><th>Semester</th><th>Credits</th><th>Final Grade</th></tr>");
      for (Course course : listCourses) {
    	 double points = 0;;
    	 
    	 switch(course.getGrade()) {
    	 	case "A+":
    	 		points = 4.0;
    	 		break;
    	 	case "A":
    	 		points = 4.0;
    	 		break;
    	 	case "A-":
    	 		points = 3.67;
    	 		break;
    	 	case "B+":
    	 		points = 3.33;
    	 		break;
    	 	case "B":
    	 		points = 3.0;
    	 		break;
    	 	case "C+":
    	 		points = 2.33;
    	 		break;
    	 	case "C":
    	 		points = 2.0;
    	 		break;
    	 	case "C-":
    	 		points = 1.67;
    	 		break;
    	 	case "D+":
    	 		points = 1.33;
    	 		break;
    	 	case "D":
    	 		points = 1.0;
    	 		break;
    	 	case "D-":
    	 		points = 0.67;
    	 		break;
    	 	case "F":
    	 		points = 0;
    	 		failCount++;
    		
    	 }
    	 totalCredits += course.getCredit_hours();
    	 totalPoints += points * course.getCredit_hours();
    	 
    	 out.println("<tr><td>" + course.getCourse_id()+"-"+course.getSection()+" : "+course.getTitle() + "</td>" + 
    			 	 "<td>" + course.getSemester() + "</td>" +
    			 	 "<td>" + course.getCredit_hours() + "</td>" +
    			 	 "<td>" + course.getGrade() + "</td></tr>");
        		
      }
      gpa = totalPoints/totalCredits;
      out.println("</table>");
      
      out.println("<h2>Summary:</h2><ul>");
      out.println("<table><tr><th>GPA</th><th>Classes Passed:</th><th>Total Classes:</th></tr>");
      out.println("<tr><td>" + gpa + "</td>" + 
    		  	"<td>" + (listCourses.size()-failCount) + "</td>" + 
    		  	"<td>" + listCourses.size()+ "</td></tr>");
      out.println("</table>");
      
      out.println("</div></div>");
      out.println("</div><div id=\"footer\">Copyright</div></div></body></html>");
   }

   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      doGet(request, response);
   }
}