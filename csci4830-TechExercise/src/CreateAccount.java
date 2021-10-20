import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.Info;
import util.UtilDBLopezBanderas;

@WebServlet("/CreateAccount")
public class CreateAccount extends HttpServlet implements Info {
	private static final long serialVersionUID = 1L;

	public CreateAccount() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String nuid = request.getParameter("nuid").trim();
		String firstName = request.getParameter("firstName").trim();
		String lastName = request.getParameter("lastName").trim();
		UtilDBLopezBanderas.addStudent(nuid, firstName, lastName);

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String title = "Account Created!";
		String docType = "<!doctype html public \"-//w3c//dtd html 4.0 transitional//en\">\n"; //

		out.println(docType + //

				"<html>\n" + "<head>\n" + "<style>\n" + "header {\n" + "background-color:black;\n" + "color:white;\n"
				+ "text-align:center;\n" + "padding:5px;}\n" + "nav {\n" + "line-height:30px;\n"
				+ "background-color:#eeeeee;\n" + "height:300px;\n" + "width:200px;\n" + "float:left;\n"
				+ "padding:5px;\n}" + "section {\n" + "width:350px;\n" + "float:left;\n" + "padding:10px;\n}"
				+ "footer {\n" + "background-color:black;\n" + "color:white;\n" + "clear:both;\n"
				+ "text-align:center;\n" + "padding:5px;}\n" + "</style>\n" + "</head>\n" +
				"<body>\n" + "<header>\n" + "<h1>" + title + "</h1>\n" + "</header>\n");

		out.println("<nav>\n<a href=/" + projectName + "/" + login + ">Log In</a> <br>");
		out.println("<a href=/" + projectName + "/" + main + ">Home Page</a> <br></nav>");

		out.println("<section>\n<h1>Student Account Info:</h1>");

		out.println("<ul>");
		out.println("<li> NUID: " + nuid);
		out.println("<li> First Name: " + firstName);
		out.println("<li> Last Name: " + lastName);
		out.println("</ul>\n</section>");

		out.println("<footer>Copyright</footer>\n</body>\n</html>");

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
