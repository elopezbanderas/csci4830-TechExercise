/**
 */
package util;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

import datamodel.Course;
import datamodel.Student;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * @since JavaSE-1.8
 */
public class UtilDBLopezBanderas {
   static SessionFactory sessionFactory = null;

   public static SessionFactory getSessionFactory() {
      if (sessionFactory != null) {
         return sessionFactory;
      }
      Configuration configuration = new Configuration().configure();
      StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
      sessionFactory = configuration.buildSessionFactory(builder.build());
      return sessionFactory;
   }
   
   public static List<Student> getAccount(String keyword) {
	   	  List<Student> resultList = new ArrayList<Student>();
	      Session session = getSessionFactory().openSession();
	      Transaction tx = null; 
	      int nuid = Integer.valueOf(keyword);

	      try {
	         tx = session.beginTransaction();
	         
	         List<?> accounts = session.createQuery("FROM Student WHERE id="+Integer.toString(nuid)).list();
	         for (Iterator<?> iterator = accounts.iterator(); iterator.hasNext();) {
	             Student student = (Student) iterator.next();
	             resultList.add(student);
	          }
	         
	         tx.commit();
	      } catch (HibernateException e) {
	         if (tx != null)
	            tx.rollback();
	         e.printStackTrace();
	      } finally {
	         session.close();
	      }
	      return resultList;
	   }

   public static List<Course> listCourses() {
      List<Course> resultList = new ArrayList<Course>();

      Session session = getSessionFactory().openSession();
      Transaction tx = null;  // each process needs transaction and commit the changes in DB.

      try {
         tx = session.beginTransaction();
         List<?> courses = session.createQuery("FROM Course").list();
         for (Iterator<?> iterator = courses.iterator(); iterator.hasNext();) {
            Course course = (Course) iterator.next();
            resultList.add(course);
         }
         tx.commit();
      } catch (HibernateException e) {
         if (tx != null)
            tx.rollback();
         e.printStackTrace();
      } finally {
         session.close();
      }
      return resultList;
   }

   public static List<Course> listCourses(String keyword) {
      List<Course> resultList = new ArrayList<Course>();
      int nuid = Integer.valueOf(keyword);

      Session session = getSessionFactory().openSession();
      Transaction tx = null;

      try {
         tx = session.beginTransaction();
        // System.out.println((Course)session.get(Course.class, 1)); // use "get" to fetch data
        // Query q = session.createQuery("FROM Employee");
         List<?> courses = session.createQuery("FROM Course WHERE id="+Integer.toString(nuid)).list();
         for (Iterator<?> iterator = courses.iterator(); iterator.hasNext();) {
            Course course = (Course) iterator.next();
            resultList.add(course);
            
         }
         tx.commit();
      } catch (HibernateException e) {
         if (tx != null)
            tx.rollback();
         e.printStackTrace();
      } finally {
         session.close();
      }
      return resultList;
   }
   
   public static List<Course> listCoursesByGrade(String nuid, String grade) {
	      List<Course> resultList = new ArrayList<Course>();

	      Session session = getSessionFactory().openSession();
	      Transaction tx = null;

	      try {
	         tx = session.beginTransaction();
	         List<?> courses = session.createCriteria(Course.class).add(Restrictions.eq("id", Integer.valueOf(nuid)))
	        		 .add(Restrictions.eq("grade", grade)).list();
	         for (Iterator<?> iterator = courses.iterator(); iterator.hasNext();) {
	            Course course = (Course) iterator.next();
	            resultList.add(course);
	            
	         }
	         tx.commit();
	      } catch (HibernateException e) {
	         if (tx != null)
	            tx.rollback();
	         e.printStackTrace();
	      } finally {
	         session.close();
	      }
	      return resultList;
	   }

   public static boolean addCourse(String id, String course_id, String section, String semester, String title, String credit_hours, String grade) {
      Session session = getSessionFactory().openSession();
      Transaction tx = null;
      boolean result = true;
      try {
         tx = session.beginTransaction();
         session.save(new Course(Integer.valueOf(id), course_id, section, semester, title, Integer.valueOf(credit_hours), grade));
         tx.commit();
      } catch (HibernateException e) {
    	 result = false;
         if (tx != null)
            tx.rollback();
         e.printStackTrace();
      } finally {
         session.close();
      }
      return result;
   }
   
   public static boolean deleteCourse(String id, String course_id, String section, String semester) {
	      Session session = getSessionFactory().openSession();
	      Transaction tx = null;
	      boolean result = true;
	      try {
	         tx = session.beginTransaction();
	         
	         Course course = (Course) session.createCriteria(Course.class).add(Restrictions.eq("id", Integer.valueOf(id)))
	        		 .add(Restrictions.eq("course_id", course_id))
	        		 .add(Restrictions.eq("section", section)).uniqueResult();
	         
	         session.delete(course);

	         tx.commit();
	      } catch (HibernateException e) {
	    	 result = false;
	         if (tx != null)
	            tx.rollback();
	         e.printStackTrace();
	      } catch(java.lang.IllegalArgumentException e) {
	    	  result = false;
		      if (tx != null)
		         tx.rollback();
		      e.printStackTrace();
	      }
	      finally {
	         session.close();
	      }
	      return result;
	   }
   
   public static boolean addStudent(String id, String first_name, String last_name) {
	      Session session = getSessionFactory().openSession();
	      Transaction tx = null;
	      boolean result = true;
	      try {
	         tx = session.beginTransaction();
	         session.save(new Student(Integer.valueOf(id), first_name, last_name));
	         tx.commit();
	      } catch (HibernateException e) {
	    	 result = false;
	         if (tx != null)
	            tx.rollback();
	         e.printStackTrace();
	      } finally {
	         session.close();
	      }
	      return result;
	   }
}
