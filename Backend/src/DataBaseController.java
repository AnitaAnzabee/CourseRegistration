import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.ArrayList;
import java.util.Random;
/**
 * This class provieds the data fields and methods to access the SQL database using 
 * querys.
 * 
 * @author Anita Rezaee, Nolan Chan, Ben Nielsen
 * @version 1.0
 * @since April 9, 2020
 */
public class DataBaseController implements Credentials  {

	    /**
	     * allows a conncection with the database 
	     * executes SQL statements and gives access to database tables 
	     * used to send queries and updates to the database
	     */
		private Connection conn;
		
		/**
		 * maintains a cursor pointingto its current row of data.
		 * represents database results.
		 */
		private ResultSet rs;

		/**
		 * constructs a DataBaseController object.
		 * initializes the connection with database.
		 */
		public DataBaseController() {
			initializeConnection();
		}
		
		/**
		 * creates a driver and registers it with DriverManager
		 * opens a connection to the databse using database credentials
		 */
		
		public void initializeConnection() {
			try {
				// Register JDBC driver
				Driver driver = new com.mysql.cj.jdbc.Driver();
				DriverManager.registerDriver(driver);
				// Open a connection
				conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
			} catch (SQLException e) {
				System.out.println("Problem");
				e.printStackTrace();
			}

		}
		
		/**
		 * will close the connection to database
		 */
		public void close() {
			try {
				// rs.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		/**
		 * using the given name and password of the student a query is sent to 
		 * the database to see if a student with the specefic name and password 
		 * exists on the database student table.
		 * @param name the student's name
		 * @param password the student's password used for login 
		 * @return the result provided by databse from the student table
		 */
		public ResultSet validateLogin(String name, String password) {
			ResultSet result=null;
			try {
				String query= "SELECT * From student where name=? and pass=?";
				PreparedStatement pStat= conn.prepareStatement(query);
				pStat.setString(1, name);
				pStat.setString(2, password);
				result=pStat.executeQuery();
				if(result.next()) {
				
					return result;
				}
			}catch(SQLException e) {
				e.printStackTrace();
				return null;
			}
			return null;
		}
		
		/**
		 * using the coursename a query is sent to the database to find 
		 * the course with the specific name and it's sections on 
		 * course and sections table in database.
		 * @param courseName the name of the course
		 * @return the result provided by database from the course and sections tables
		 */
		public ResultSet searchCourse ( String courseName) {
			ResultSet result=null;
			try {
				if(!courseName.equals("")) {
					String query = "SELECT course.courseID, courseName, sectionID, capacity, "
							+ "secNum FROM course JOIN sections" +
							"ON sections.courseID=course.courseID where courseName=?";
					PreparedStatement pStat = conn.prepareStatement(query);
					pStat.setString(1, courseName);
					result = pStat.executeQuery();
				}
			}catch (SQLException e) {
				e.printStackTrace();
			}
			
			return result;
		}
		
		/**
		 * using the courseID a query is sent to the database to find the prereq of 
		 * the course on the prereq table.
		 * @param courseID the ID of the course with the prereqs
		 * @return the prereq courses found on the prereq table using the courseID
		 */
		
		public ResultSet searchPreReq( int courseID) {
			ResultSet result = null;
			try {
				if(courseID!=0) {
					String query= "SELECT course.courseID, courseName FROM prereq JOIN course ON "
							+ "prereq.prereqID=course.courseID where prereq.courseID=?";
					PreparedStatement pStat = conn.prepareStatement(query);
					pStat.setInt(1, courseID);
					result = pStat.executeQuery();
				}
			}catch (SQLException e) {
				e.printStackTrace();
			}
			return result;
		}
		
		/**
		 * a query is sent to get all the courses with their sections from the course and sections tables
		 * @return the result provided by the database including information about all the courses 
		 * in the database
		 */
		public ResultSet showCatalog() {
			ResultSet result = null;
			try {
				String query = "SELECT course.courseID, courseName, sectionID, capacity, secNum FROM "
						+ "course JOIN sections ON sections.courseID=course.courseID";
				PreparedStatement pStat = conn.prepareStatement(query);
				result = pStat.executeQuery();
			}catch (SQLException e) {
				e.printStackTrace();
			}
			return result;
		}
		
		/**
		 * a query is sent to add the student in the registration table and the course student is registring for
		 * the registration will be added to the registration table if the section has not reached its capacity
		 * or the student has not registered for more than 6 courses 
		 * once the registration is added to the table the capacity of the course section will be reduced using the 
		 * sectionId to find the section in the sections table
		 * @param sectionId the section that the student wants to register for
		 * @param studentId the ID of the student registering for the course
		 * @return true if the registration is successful or false if not
		 */
		public Boolean addToRegistration(int sectionId, int studentId) {
			try {
				int registrationCount= countNumberOfRegistrations(studentId);
			    int capacity= sectionCapacity(sectionId);
				if(capacity > 0 && registrationCount <6) {
					String query="INSERT INTO registration (sectionID,studentID) VALUES(?,?)";
					PreparedStatement pStat = conn.prepareStatement(query);
					pStat.setInt(1, sectionId);
					pStat.setInt(2, studentId);
					pStat.executeQuery();
					decrementCapacity(sectionId);
				}
				else {
					return false;
				}
						
			}catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
			return true;
		}
		
		/**
		 * a query is sent to count the rows of the registration table where the studentId matches the 
		 * studentID in the registration table
		 * @param studentId the id of the student 
		 * @return the number of registrations of the student
		 */
		private int countNumberOfRegistrations(int studentId) {
			ResultSet result = null;
			int registrationNum=0;
			try {
				String query= "SELECT studentID, COUNT(*) FROM registration Where studentID=?";
				PreparedStatement pStat = conn.prepareStatement(query);
				pStat.setInt(1, studentId);
				result=pStat.executeQuery();
				registrationNum = result.getInt(2);
			}catch (SQLException e) {
				e.printStackTrace();	
			}
			return registrationNum;
		}
		
		/**
		 * a query is sent to get the capacity of the section in sections table
		 * @param sectionId the ID of the course section
		 * @return the capacity of that section
		 */
		private int sectionCapacity(int sectionId) {
			int capacity=0;
			try {
				String query="SELECT capacity FROM sections where sectionID=? ";
				PreparedStatement pStat = conn.prepareStatement(query);
				pStat.setInt(1, sectionId);
				ResultSet result=pStat.executeQuery();
				capacity=result.getInt(3);
			}catch (SQLException e) {
				e.printStackTrace();	
			}
			return capacity;
		
		}
		
		/**
		 * a query is sent to delete a registration from registration table using the registrationId
		 * once the registration is deleted, the sectionId will be used to find th section that 
		 * the student is removing their reigstration from in the secions table to increase the capacity 
		 * of the section.
		 * @param registrationId the id of the registration that will be deleted
		 * @param sectionId the id of the section that the student removing their reigstration
		 * @return true if the registration is deleted from registration table. return false otherwise 
		 */
		public Boolean deleteFromRegistration(int registrationId, int sectionId) {
			try {
				String query="DELETE FROM registration Where registerID=?";
				PreparedStatement pStat = conn.prepareStatement(query);
				pStat.setInt(1, registrationId);
				pStat.executeQuery();
				incrementCapacity(sectionId);
			}catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
			return true;
		}
		
		/**
		 * a query is sent to get the registration information of the student using studentId
		 * @param studentId the student id used for finding the student registration information
		 * on registration table 
		 * @return the registration information found from registration table
		 */
		public ResultSet showRegistration(int studentId) {
			ResultSet result = null;
			try {
				String query = "SELECT registration.registerID, registration.sectionID, registration.studentID, "
						+ "sections.courseID, sections.secNUm, course.courseName" + 
						"FROM registration JOIN sections ON sections.sectionID= registration.sectionID" + 
						"JOIN course ON course.courseID=sections.courseID Where studentID=?";
				PreparedStatement pStat = conn.prepareStatement(query);
				pStat.setInt(1, studentId);
				result = pStat.executeQuery();
			}catch (SQLException e) {
				e.printStackTrace();
			}
			return result;
		}
		
		/**
		 * a query is sent to increase the capacity of the section after the registration is 
		 * deleted from registration table
		 * @param sectionId the id of the section that is used to find the section on the sections table
		 */
		private void incrementCapacity(int sectionId) {
			try {
				String query="UPDATE sections SET capacity=capacity+1 WHERE sectionID=?";
				PreparedStatement pStat = conn.prepareStatement(query);
				pStat.setInt(1, sectionId);
				pStat.executeUpdate();
			}catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		/**
		 * a query is sent to decrease the capacity of the section after the registration is 
		 * added to the registration table 
		 * @param sectionId the ID of the section that is used to find the section on the 
		 * sections table
		 */
		private void decrementCapacity(int sectionId) {
			try {
				String query="UPDATE sections SET capacity=capacity-1 WHERE sectionID=?";
				PreparedStatement pStat = conn.prepareStatement(query);
				pStat.setInt(1, sectionId);
				pStat.executeUpdate();
			}catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
}
