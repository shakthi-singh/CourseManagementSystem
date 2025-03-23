package com.example;

import com.example.model.CourseMark;
import com.example.model.CourseMarkKey;
import com.example.model.Student;
import com.example.model.TeachingAssistant;

import jakarta.persistence.Query;

import com.example.model.Course;
import com.example.model.Professor;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.sql.Statement;


public class DataUtil {

    private SessionFactory sessionFactory;


    // Function to establish connection with the database
    protected void setUp() throws Exception {
        // A SessionFactory is set up once for an application!
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
              .configure() // configures settings from hibernate.cfg.xml
              .build();
        try {
           sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
        }
        catch (Exception e) {
           // The registry would be destroyed by the SessionFactory, but we had trouble building the SessionFactory
           // so destroy it manually.
           System.out.println("Error in setUp: " + e);
           StandardServiceRegistryBuilder.destroy( registry );
        }
     }

    // Function to close the connection with the database
    protected void tearDown() throws Exception {
        if ( sessionFactory != null ) {
           sessionFactory.close();
        }
     }

    // Function to get all the students (uses Hibernate Query)
    public HashMap<Integer, Student> getStudents() {
        // Open a session
        Session session = sessionFactory.openSession();
        // Begin a transaction
        session.beginTransaction();
        // Get all students
        List<Student> result = session.createQuery( "from Student", Student.class ).list();
        // Commit the transaction
        session.getTransaction().commit();
        // Close the session
        session.close();

        HashMap<Integer, Student> students = new HashMap<>();
        for (Student student : result) {
            //System.out.println("Student: " + student.getId() + " " + student.getEmail());
            students.put(student.getId(), student);
        }

        return students;
    }

    // Function to get all the students (uses SQL Query)
    public HashMap<Integer, Student> getStudents_SQL() {
         // Open a session
         Session session = sessionFactory.openSession();
         // Begin a transaction
         session.beginTransaction();
         // Get all students
         Query q = session.createNativeQuery("SELECT * FROM Student", Student.class);
         List<Student> result = (List<Student>) q.getResultList();
         // Commit the transaction
         session.getTransaction().commit();
         // Close the session
         session.close();

         HashMap<Integer, Student> students = new HashMap<>();
         for (Student student : result) {
             System.out.println("Student: " + student.getId() + " " + student.getEmail());
             students.put(student.getId(), student);
         }
 
         return students;
 
    }

    // Function to get all the courses (uses Hibernate Query)
    public HashMap<String, Course> getCourses() {
        // Open a session
        Session session = sessionFactory.openSession();
        // Begin a transaction
        session.beginTransaction();
        // Get all courses
        List<Course> result = session.createQuery( "from Course", Course.class ).list();
        // Commit the transaction
        session.getTransaction().commit();
        // Close the session
        session.close();

        HashMap<String, Course> courses = new HashMap<>();
        for (Course course : result) {
            courses.put(course.getId(), course);
        }

        return courses;
    }

    // Function to get all the Professors (uses Hibernate Query)
    public HashMap<Integer, Professor> getProfessors() {
        // Open a session
        Session session = sessionFactory.openSession();
        // Begin a transaction
        session.beginTransaction();
        // Get all professors
        List<Professor> result = session.createQuery( "from Professor", Professor.class ).list();
        // Commit the transaction
        session.getTransaction().commit();
        // Close the session
        session.close();

        HashMap<Integer, Professor> professors = new HashMap<>();
        for (Professor professor : result) {
            professors.put(professor.getId(), professor);
        }

        return professors;
    }

    // Function to get all the transcript (uses Hibernate Query)
    public HashMap<CourseMarkKey, CourseMark> getTranscript() {
        // Open a session
        Session session = sessionFactory.openSession();
        // Begin a transaction
        session.beginTransaction();
        // Get all transcript
        List<CourseMark> result = session.createQuery( "from CourseMark", CourseMark.class ).list();
        // Commit the transaction
        session.getTransaction().commit();
        // Close the session
        session.close();
        
        HashMap<CourseMarkKey, CourseMark> transcript = new HashMap<>();
        for (CourseMark mark : result) {
            transcript.put(mark.getMarkId(), mark);
        }

        return transcript;
    }

    // YOUR CODE GOES HERE
     public void populateTeachingAssistantTable() {
       try (Session session = sessionFactory.openSession()) { 
        //Open a session
           session.doWork(connection -> {
               String dropTableSQL = "DROP TABLE TeachingAssistant";//if running multiple times, drop the old teachingassistant table
      
               try (PreparedStatement dropStmt = connection.prepareStatement(dropTableSQL)) {
                   dropStmt.executeUpdate();
               } catch (SQLException e) {
                   System.err.println("Error dropping TeachingAssistant table: " + e.getMessage());
               }
               String createTATable = "CREATE TABLE TeachingAssistant ("
                    + "studentId INT PRIMARY KEY, "
                    + "firstName VARCHAR(255), "
                    + "lastName VARCHAR(255), "
                    + "email VARCHAR(255),"
                    + "courseId VARCHAR(255)"
                    + ")";
  
               try (PreparedStatement ps = connection.prepareStatement(createTATable)) {
                   ps.executeUpdate();
               } catch (SQLException e) {
                   System.err.println("Error creating TeachingAssistant table: " + e.getMessage());
               }
          
       // rows
                String insertQuery1 = "INSERT INTO TeachingAssistant (studentId, firstName, lastName, email, courseId) VALUES(9000, 'Lauren', 'Kelly', 'Lauren_Kelly@fake.com', 'edm304h1s')";
                String insertQuery2 = "INSERT INTO TeachingAssistant (studentId, firstName, lastName, email, courseId) " +
                                        "VALUES (9001, 'Mark', 'Ward', 'Mark_Ward@fake.com', 'mie100h1s')";
                String insertQuery3 = "INSERT INTO TeachingAssistant (studentId, firstName, lastName, email, courseId) " +
                                        "VALUES (9002, 'Rebecca', 'Ward', 'Rebecca_ward@fake.com', 'mie191h1s')";
                String insertQuery4 = "INSERT INTO TeachingAssistant (studentId, firstName, lastName, email, courseId) " +
                                        "VALUES (9003, 'Patrick', 'Wang', 'Patrick_Wang@fake.com', 'mie210h1s')";
                            
                try (Statement stmt = connection.createStatement()) {
                    stmt.executeUpdate(insertQuery1);
                    stmt.executeUpdate(insertQuery2);
                    stmt.executeUpdate(insertQuery3);
                    stmt.executeUpdate(insertQuery4);
                    connection.commit();//commit
                } catch (SQLException e) {
                    System.err.println("Error populating TeachingAssistant table: " + e.getMessage());
                }
     });
     
       }catch (Exception e) {
        e.printStackTrace();
    }
   }
   public HashMap<Integer, TeachingAssistant> getTeachingAssistant() {
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    List<TeachingAssistant> result = session.createQuery("from TeachingAssistant", TeachingAssistant.class).list();
    session.getTransaction().commit();
    session.close();
    HashMap<Integer, TeachingAssistant> teachingAssistants = new HashMap<>();
    for (TeachingAssistant ta : result) {
        teachingAssistants.put(ta.getId(), ta);
    }
    return teachingAssistants;
}

     // Part 4 1. 
    public void query1() {
        try (Session session = sessionFactory.openSession()) { 
            session.doWork(connection -> {
                String query = "SELECT MAX(grade) AS highest_grade FROM Transcript WHERE courseId = 'MIE302H1S'";
                String csvFileName = "query1.csv";
                try (PreparedStatement dropStmt = connection.prepareStatement(query)) {
                    ResultSet resultSet = dropStmt.executeQuery();
                    FileWriter csvWriter = new FileWriter(csvFileName);
                    csvWriter.append("highest_grade\n");//header
            if (resultSet.next()) {
                csvWriter.append(resultSet.getString("highest_grade")).append("\n");
            }
            csvWriter.flush();
        }                   
            catch (SQLException  | IOException e) {
                System.err.println("Error executing query1: " + e.getMessage());
            }
      });
        }catch (Exception e) {
         e.printStackTrace();
     }
    }
    //2. 
    public void query2() {
        try (Session session = sessionFactory.openSession()) { 
            session.doWork(connection -> {
                String query = "SELECT courseName FROM Course WHERE MID(courseId, 4, 1) = '1'";
                String csvFileName = "query2.csv";
                try (PreparedStatement dropStmt = connection.prepareStatement(query)) {
                    ResultSet resultSet = dropStmt.executeQuery();
                    FileWriter csvWriter = new FileWriter(csvFileName);
                    csvWriter.append("courseName\n");
            while (resultSet.next()) {//using while to get all first year courses, and add each to csv
                String courseName = resultSet.getString("courseName");
                csvWriter.append(courseName).append("\n");
            }
            csvWriter.flush();
        }                   
            catch (SQLException  | IOException e) {
                System.err.println("Error executing query2: " + e.getMessage());
            }
      });
        }catch (Exception e) {
         e.printStackTrace();
     }
    }
    public void query3() {
        try (Session session = sessionFactory.openSession()) { 
            session.doWork(connection -> {
                String query = "SELECT CONCAT(firstName, ' ', lastName) AS fullName " +
                       "FROM Professor " +
                       "WHERE profId IN ( " +
                       "    SELECT profId " +
                       "    FROM Teaches " +
                       "    GROUP BY profId " +
                       "    HAVING COUNT(courseId) = 1 " +
                       ")";
                String csvFileName = "query3.csv";
                try (PreparedStatement dropStmt = connection.prepareStatement(query)) {
                    ResultSet resultSet = dropStmt.executeQuery();
                    FileWriter csvWriter = new FileWriter(csvFileName);
                    csvWriter.append("courseName\n");
            while (resultSet.next()) {
                String fullName = resultSet.getString("fullName");
                csvWriter.append(fullName).append("\n");
            }
            csvWriter.flush();
        }                   
            catch (SQLException  | IOException e) {
                System.err.println("Error executing query3: " + e.getMessage());
            }
      });
        }catch (Exception e) {
         e.printStackTrace();
     }
    }
    public void query4() {
        try (Session session = sessionFactory.openSession()) { 
            session.doWork(connection -> {
                String query = "SELECT CONCAT(Student.firstName, ' ', Student.lastName) AS fullName " +
                       "FROM Student " +
                       "WHERE studentId IN ( " +
                       "    SELECT studentId " +
                       "    FROM Transcript " +
                       "    WHERE courseId IN ( " +
                       "        SELECT courseId " +
                       "        FROM Teaches " +
                       "        WHERE profId = ( " +
                       "            SELECT profId " +
                       "            FROM Professor " +
                       "            WHERE firstName = 'Patrick' AND lastName = 'Ward' " +
                       "        ) " +
                       "    ) " +
                       ")";
                String csvFileName = "query4.csv";
                try (PreparedStatement dropStmt = connection.prepareStatement(query)) {
                    ResultSet resultSet = dropStmt.executeQuery();
                    FileWriter csvWriter = new FileWriter(csvFileName);
                    csvWriter.append("courseName\n");
            while (resultSet.next()) {
                String fullName = resultSet.getString("fullName");
                csvWriter.append(fullName).append("\n");
            }
            csvWriter.flush();
        }                   
            catch (SQLException  | IOException e) {
                System.err.println("Error executing query4: " + e.getMessage());
            }
      });
        }catch (Exception e) {
         e.printStackTrace();
     }
    }
    public void query5() {
        try (Session session = sessionFactory.openSession()) { 
            session.doWork(connection -> {
                String query = "SELECT CONCAT(firstName, ' ', lastName) AS fullName " +
                       "FROM Student " +
                       "WHERE studentId NOT IN ( " +
                       "    SELECT DISTINCT studentId " +
                       "    FROM Transcript " +
                       "    WHERE courseId IN ( " +
                       "        SELECT courseId " +
                       "        FROM Teaches " +
                       "        WHERE profId = ( " +
                       "            SELECT profId " +
                       "            FROM Professor " +
                       "            WHERE firstName = 'Kelsey' AND lastName = 'Lewis' " +
                       "        ) " +
                       "    ) " +
                       ")";
                String csvFileName = "query5.csv";
                try (PreparedStatement dropStmt = connection.prepareStatement(query)) {
                    ResultSet resultSet = dropStmt.executeQuery();
                    FileWriter csvWriter = new FileWriter(csvFileName);
                    csvWriter.append("courseName\n");
            while (resultSet.next()) {
                String fullName = resultSet.getString("fullName");
                csvWriter.append(fullName).append("\n");
            }
            csvWriter.flush();
        }                   
            catch (SQLException  | IOException e) {
                System.err.println("Error executing query5: " + e.getMessage());
            }
      });
        }catch (Exception e) {
         e.printStackTrace();
     }
    }
    public void query6() {
        try (Session session = sessionFactory.openSession()) { 
            session.doWork(connection -> {
                String query = "WITH JasonCourses AS ( " +
                       "    SELECT courseId, grade " +
                       "    FROM Transcript " +
                       "    WHERE studentId = ( " +
                       "        SELECT studentId " +
                       "        FROM Student " +
                       "        WHERE firstName = 'Jason' AND lastName = 'Martinez' " +
                       "    ) " +
                       "), OtherStudentAverages AS ( " +
                       "    SELECT courseId, AVG(grade) AS otherAverage " +
                       "    FROM Transcript " +
                       "    WHERE courseId IN (SELECT courseId FROM JasonCourses) " +
                       "      AND studentId != ( " +
                       "          SELECT studentId " +
                       "          FROM Student " +
                       "          WHERE firstName = 'Jason' AND lastName = 'Martinez' " +
                       "      ) " +
                       "    GROUP BY courseId " +
                       "), Comparison AS ( " +
                       "    SELECT AVG(JasonCourses.grade) AS jasonAverage, " +
                       "           AVG(OtherStudentAverages.otherAverage) AS othersAverage " +
                       "    FROM JasonCourses " +
                       "    LEFT JOIN OtherStudentAverages " +
                       "    ON JasonCourses.courseId = OtherStudentAverages.courseId " +
                       ") " +
                       "SELECT " +
                       "    CASE " +
                       "        WHEN jasonAverage > othersAverage THEN 'Yes' " +
                       "        ELSE 'No' " +
                       "    END AS isJasonHigher " +
                       "FROM Comparison";

                String csvFileName = "query6.csv";
                try (PreparedStatement dropStmt = connection.prepareStatement(query)) {
                    ResultSet resultSet = dropStmt.executeQuery();
                    FileWriter csvWriter = new FileWriter(csvFileName);
                    csvWriter.append("courseName\n");
            while (resultSet.next()) {
                String isJasonHigher = resultSet.getString("isJasonHigher");
                csvWriter.append(isJasonHigher).append("\n");
            }
            csvWriter.flush();
        }                   
            catch (SQLException  | IOException e) {
                System.err.println("Error executing query6: " + e.getMessage());
            }
      });
        }catch (Exception e) {
         e.printStackTrace();
     }
}
    public void query7() {
    try (Session session = sessionFactory.openSession()) { 
        session.doWork(connection -> {
            String query = "WITH HumCentStudents AS ( " +
                       "    SELECT DISTINCT studentId " +
                       "    FROM Transcript " +
                       "    WHERE courseId = ( " +
                       "        SELECT courseId " +
                       "        FROM Course " +
                       "        WHERE courseName = 'Hum.Cent.Syst.Design' " +
                       "    ) " +
                       "), FailedCourses AS ( " +
                       "    SELECT studentId " +
                       "    FROM Transcript " +
                       "    WHERE grade <= 49 " +
                       "      AND courseId != ( " +
                       "          SELECT courseId " +
                       "          FROM Course " +
                       "          WHERE courseName = 'Hum.Cent.Syst.Design' " +
                       "      ) " +
                       ") " +
                       "SELECT CONCAT(Student.firstName, ' ', Student.lastName) AS fullName " +
                       "FROM Student " +
                       "WHERE studentId IN ( " +
                       "    SELECT DISTINCT studentId " +
                       "    FROM HumCentStudents " +
                       "    INTERSECT " +
                       "    SELECT DISTINCT studentId " +
                       "    FROM FailedCourses " +
                       ")";

            String csvFileName = "query7.csv";
            try (PreparedStatement dropStmt = connection.prepareStatement(query)) {
                ResultSet resultSet = dropStmt.executeQuery();
                FileWriter csvWriter = new FileWriter(csvFileName);
                csvWriter.append("courseName\n");
        while (resultSet.next()) {
            String fullName = resultSet.getString("fullName");
                csvWriter.append(fullName).append("\n");
        }
        csvWriter.flush();
    }                   
        catch (SQLException  | IOException e) {
            System.err.println("Error executing query7: " + e.getMessage());
        }
  });
    }catch (Exception e) {
     e.printStackTrace();
 }
}

}

