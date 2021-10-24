

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.UtilDBLopezBanderas;

/**
 * Servlet implementation class DeleteCourse
 */
@WebServlet("/DeleteCourse")
public class DeleteCourse extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	public DeleteCourse() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String nuid = request.getParameter("nuid").trim();
		String course_id = request.getParameter("course_id").trim();
		String section = request.getParameter("section").trim();
		String semester = request.getParameter("semester").trim();
		
		int deleteCourse = 0; //0 for success, other for failure for failure
		
		if(course_id.isEmpty() || section.isEmpty() || semester.isEmpty() ) {
			deleteCourse = 1;

		}
		
		else if(!UtilDBLopezBanderas.deleteCourse(nuid, course_id, section, semester)) {
			deleteCourse = 1;
	    	
		}

		response.sendRedirect("LogInStudent?nuid="+nuid+"&deleteCourse="+deleteCourse);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
