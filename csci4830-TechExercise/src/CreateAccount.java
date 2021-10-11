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

   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      String nuid = request.getParameter("nuid").trim();
      String firstName = request.getParameter("firstName").trim();
      String lastName = request.getParameter("lastName").trim();
      UtilDBLopezBanderas.addStudent(nuid, firstName, lastName);

      response.setContentType("text/html");
      PrintWriter out = response.getWriter();
      String title = "Account Created!";
      String docType = "<!doctype html public \"-//w3c//dtd html 4.0 transitional//en\">\n"; //
      out.println(docType + //
            "<html>\n" + //
            "<head><title>" + title + "</title></head>\n" + //
            "<body bgcolor=\"#f0f0f0\">\n" + //
            "<h1 align=\"center\">" + title + "</h1>\n");
      out.println("<ul>");
      out.println("<li> NUID: " + nuid);
      out.println("<li> First Name: " + firstName);
      out.println("<li> Last Name: " + lastName);
      out.println("</ul>");
      out.println("<a href=/" + projectName + "/" + searchWebName + ">Search Data</a> <br>");
      out.println("</body></html>");
   }

   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      doGet(request, response);
   }
}
