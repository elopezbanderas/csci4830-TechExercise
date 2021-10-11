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

@WebServlet("/MyServlet")
public class MyServlet extends HttpServlet {
   private static final long serialVersionUID = 1L;

   public MyServlet() {
      super();
   }

   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      //response.setContentType("text/html");
      
      response.getWriter().append("Hello World!");

      // #1
      UtilDBLopezBanderas.addStudent("84103554", "eloy", "lopez banderas");
      UtilDBLopezBanderas.addCourse("84103554", "CSCI4830", "850", "FALL2021", "Intro to SoftWare Eng", "3", "A+" );
/*      
      // #2
      retrieveDisplayData(response.getWriter()); 
*/
   }
/*
   void retrieveDisplayData(PrintWriter out) {
      String title = "Database Result";
      String docType = "<!doctype html public \"-//w3c//dtd html 4.0 " + //
            "transitional//en\">\n"; //
      out.println(docType + //
            "<html>\n" + //
            "<head><title>" + title + "</title></head>\n" + //
            "<body bgcolor=\"#f0f0f0\">\n" + //
            "<h1 align=\"center\">" + title + "</h1>\n");
      out.println("<ul>");
      List<Course> listEmployees = UtilDBLopezBanderas.listEmployees();
      for (Course employee : listEmployees) {
         System.out.println("[DBG] " + employee.getId() + ", " //
               + employee.getName() + ", " //
               + employee.getAge() + ", " 
               + employee.getPhone());

         out.println("<li>" + employee.getId() + ", " //
               + employee.getName() + ", " //
               + employee.getAge() + ", " 
               + employee.getPhone() + "</li>");
      }
      out.println("</ul>");
      out.println("</body></html>");
   }
*/
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      doGet(request, response);
   }
}
