

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.UtilDBLopezBanderas;

/**
 * Servlet implementation class AddCourse
 */
@WebServlet("/AddCourse")
public class AddCourse extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	public AddCourse() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String nuid = request.getParameter("nuid").trim();
		String course_id = request.getParameter("course_id").trim();
		String section = request.getParameter("section").trim();
		String semester = request.getParameter("semester").trim();
		String title = request.getParameter("title").trim();
		String credit_hours = request.getParameter("credit_hours").trim();
		String grade = request.getParameter("grade").trim().toUpperCase();
		
		int addCourse = 0; //0 for success, other for failure for failure
		
		if(course_id.isEmpty() || section.isEmpty() || semester.isEmpty() || title.isEmpty() || credit_hours.isEmpty() || grade.isEmpty() ) {
			addCourse = 1;

		}
		
		if(!grade.contentEquals("A+") && !grade.contentEquals("A") && !grade.contentEquals("A-") && !grade.contentEquals("B+") 
				&& !grade.contentEquals("B") && !grade.contentEquals("B-") && !grade.contentEquals("C+") && !grade.contentEquals("C")
				&& !grade.contentEquals("C-") && !grade.contentEquals("D+") && !grade.contentEquals("D") && !grade.contentEquals("D-")
				&& !grade.contentEquals("F")) {
	    	addCourse = 1;


		}
		else if(!UtilDBLopezBanderas.addCourse(nuid, course_id, section, semester, title, credit_hours, grade)) {
			addCourse = 1;
	    	
		}

		response.sendRedirect("LogInStudent?nuid="+nuid+"&addCourse="+addCourse);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
