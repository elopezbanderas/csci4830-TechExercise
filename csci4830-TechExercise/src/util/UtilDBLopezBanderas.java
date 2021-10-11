/**
 */
package util;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import datamodel.Course;
import datamodel.Student;

import org.hibernate.HibernateException;
import org.hibernate.Query;
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

   public static List<Course> listCourses(int keyword) {
      List<Course> resultList = new ArrayList<Course>();

      Session session = getSessionFactory().openSession();
      Transaction tx = null;

      try {
         tx = session.beginTransaction();
         System.out.println((Course)session.get(Course.class, 1)); // use "get" to fetch data
        // Query q = session.createQuery("FROM Employee");
         List<?> courses = session.createQuery("FROM Course").list();
         for (Iterator<?> iterator = courses.iterator(); iterator.hasNext();) {
            Course course = (Course) iterator.next();
            if (course.getId()==(keyword)) {
               resultList.add(course);
            }
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

   public static void addCourse(String id, String course_id, String section, String semester, String title, String credit_hours, String grade) {
      Session session = getSessionFactory().openSession();
      Transaction tx = null;
      try {
         tx = session.beginTransaction();
         session.save(new Course(Integer.valueOf(id), course_id, section, semester, title, Integer.valueOf(credit_hours), grade));
         tx.commit();
      } catch (HibernateException e) {
         if (tx != null)
            tx.rollback();
         e.printStackTrace();
      } finally {
         session.close();
      }
   }
   
   public static void addStudent(String id, String first_name, String last_name) {
	      Session session = getSessionFactory().openSession();
	      Transaction tx = null;
	      try {
	         tx = session.beginTransaction();
	         session.save(new Student(Integer.valueOf(id), first_name, last_name));
	         tx.commit();
	      } catch (HibernateException e) {
	         if (tx != null)
	            tx.rollback();
	         e.printStackTrace();
	      } finally {
	         session.close();
	      }
	   }
}
