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
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String docType = "<!doctype html public \"-//w3c//dtd html 4.0 transitional//en\">\n"; //

		out.println(docType + 
				"<html><head><title>Account Page</title>"+
				"<link rel=\"stylesheet\" type=\"text/css\" href=\"style.css\" /></head>\n");
		out.println("<body><div id=\"container\">");
    	out.println("<div id=\"header\"><h1>Student Academic Tracker</h1></div>");
    	out.println("<div id=\"content\">");
    	out.println("<div id=\"nav\"><h3>Navigation</h3><ul>");
    	out.println("<li><a href=\"/csci4830-TechExercise/main.html\">Home Page</a> <br></li>");
    	out.println("<li><a href=\"/csci4830-TechExercise/login.html\">Log In</a> <br></li>");
    	out.println("<li><a href=\"/csci4830-TechExercise/createAccount.html\">Create New Account</a> <br></li></ul></div>");
		
		String nuid = request.getParameter("nuid").trim();
		String firstName = request.getParameter("firstName").trim();
		String lastName = request.getParameter("lastName").trim();
		
		if(nuid.length() != 8 || firstName.length() == 0 || lastName.length()==0) {
	    	out.println("<div id=\"main\"><h2>Account Not Created!</h2><p>The account could not be created with the data you provided.</p>");
	    	out.println("<p>This may be because you did not type a valid 8-digit NUID or did not complete a field.</p><p>Please try creating the account again.</p></div></div>");
	    	out.println("</div><div id=\"footer\">Copyright</div></div></body></html>");

	  		return;
		}
		if(!UtilDBLopezBanderas.addStudent(nuid, firstName, lastName)) {
			out.println("<div id=\"main\"><h2>Account Not Created!</h2><p>The account could not be created with the data you provided.</p>");
	    	out.println("<p>This may be because the NUID you typed is already associated with an existing account.</p><p>Please try creating the account again or try logging in with your NUID.</p></div></div>");
	    	out.println("</div><div id=\"footer\">Copyright</div></div></body></html>");

	  		return;
		}
		
		out.println("<div id=\"main\"><h2>Account Successfully Created!</h2>");
		out.println("<ul>");
		out.println("<li> NUID: " + nuid);
		out.println("<li> First Name: " + firstName);
		out.println("<li> Last Name: " + lastName);
		out.println("</ul><p>Log in to your account with your NUID to begin your academic tracking!</p>");
    	out.println("</div></div>");
    	out.println("</div><div id=\"footer\">Copyright</div></div></body></html>");


	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
