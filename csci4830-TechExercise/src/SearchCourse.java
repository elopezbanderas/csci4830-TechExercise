

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datamodel.Course;
import util.UtilDBLopezBanderas;

@WebServlet("/SearchCourse")
public class SearchCourse extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public SearchCourse() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nuid = request.getParameter("nuid").trim();
        String grade = request.getParameter("grade").toUpperCase().trim();
        
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        String docType = "<!doctype html public \"-//w3c//dtd html 4.0 transitional//en\">\n"; //
        out.println(docType + //

  				"<html><head><title>Account Page</title>"+
  				"<link rel=\"stylesheet\" type=\"text/css\" href=\"style.css\" /></head>\n");
        
        out.println("<body><div id=\"container\">");
    	out.println("<div id=\"header\"><h1>Student Academic Tracker</h1></div>");
    	out.println("<div id=\"content\">");
    	out.println("<div id=\"nav\"><h3>Navigation</h3><ul>");
    	out.println("<li><a href=\"/csci4830-TechExercise/main.html\">Home Page</a> <br></li>");
    	out.println("<li><a href=\"/csci4830-TechExercise/LogInStudent?nuid="+nuid+"\">Back</a> <br></li></ul></div>");
    	
    	if((!grade.contentEquals("A+") && !grade.contentEquals("A") && !grade.contentEquals("A-") && !grade.contentEquals("B+") 
				&& !grade.contentEquals("B") && !grade.contentEquals("B-") && !grade.contentEquals("C+") && !grade.contentEquals("C")
				&& !grade.contentEquals("C-") && !grade.contentEquals("D+") && !grade.contentEquals("D") && !grade.contentEquals("D-")
				&& !grade.contentEquals("F")) || (grade.isEmpty())) {
    		
    		out.println("<div id=\"main\"><h2>No courses listed!</h2><p>You did not provide a valid letter grade.</p>");
      	  	out.println("<p>Try again with a valid letter grade, or click back to see your academic tracker.</p></div></div>");
      	  	out.println("</div><div id=\"footer\">Copyright</div></div></body></html>");

    		return;

    	}
    	  
        List<Course> listCourses = UtilDBLopezBanderas.listCoursesByGrade(nuid, grade);
        
        if(listCourses.size()==0) {
      	  
        	out.println("<div id=\"main\"><h2>No courses listed!</h2><p>You don't appear to have any courses with that letter grade.</p>");
      	  	out.println("<p>Try again with a different letter grade, or click back to see your academic tracker.</p></div></div>");
      	  	out.println("</div><div id=\"footer\">Copyright</div></div></body></html>");

    		return;
        }
        
        display(listCourses, out, grade);
     } 

     void display(List<Course> listCourses, PrintWriter out, String grade) {
  	  out.println("<div id=\"main\"><h2>Courses taken with a final letter grade of: "+grade+"</h2><ul>");
   	  
  	  out.println("<table><tr><th>Course</th><th>Semester</th><th>Credits</th><th>Final Grade</th></tr>");
        for (Course course : listCourses) { 
      	 out.println("<tr><td>" + course.getCourse_id()+"-"+course.getSection()+" : "+course.getTitle() + "</td>" + 
      			 	 "<td>" + course.getSemester() + "</td>" +
      			 	 "<td>" + course.getCredit_hours() + "</td>" +
      			 	 "<td>" + course.getGrade() + "</td></tr>");
          		
        }
        out.println("</table>");
        
        out.println("</div></div></div>");
        
        out.println("<div id=\"footer\">Copyright</div></div></body></html>");
     }

     protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
     }
  }