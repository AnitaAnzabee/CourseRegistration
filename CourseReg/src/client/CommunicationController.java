package client;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.NoSuchElementException;

import model.*;

/**
 * A class that does all of the communication with the server for the client application.
 * @author Nolan Chan
 * @author Benjamin Nielsen
 * @author Anita
 * @version 1.0
 * @since April 18, 2020
 */
public class CommunicationController {
	
	/**
	 * A Socket used for communication with the server
	 */
	private Socket aSocket;
	
	/**
	 * A BufferedReader for reading in Strings from the server
	 */
	private BufferedReader socketTextIn;
	
	/**
	 * A PrintWriter for writing Strings to the server
	 */
	private PrintWriter socketTextOut;
	
	/**
	 * A ObjectOutputStream used for writing objects to the server
	 */
	private ObjectOutputStream socketObjectOut;
	
	/**
	 * An ObjectInputStream used for reading objects from the server
	 */
	private ObjectInputStream socketObjectIn;
	
	/**
	 * Constructs a CommunicationController by connecting to a server with the supplied serverName and portNumber.
	 * Initializes objects to communicate with the server by getting appropriate IO streams from the Socket.
	 * @param serverName The name of the server to connect to
	 * @param portNumber The port number for the server to connect to
	 */
	public CommunicationController(String serverName, int portNumber) {
		try {
			aSocket = new Socket(serverName, portNumber);
			socketTextOut = new PrintWriter((aSocket.getOutputStream()), true);
			socketTextIn = new BufferedReader(new InputStreamReader(aSocket.getInputStream()));
			socketObjectOut = new ObjectOutputStream(aSocket.getOutputStream());
			socketObjectIn = new ObjectInputStream(aSocket.getInputStream());
		}catch (Exception e) {
			System.err.println(e);
		}
	}
	
	/**
	 * Attempts to authenticate the user logging in with the supplied username and password. Returns a null User if
	 * it could not be authenticated, otherwise, returns a User object with information used for communicating with the server.
	 * @param username The username of the user attempting to login
	 * @param password The password of the user attempting to login
	 * @return A null User object if they could not be authenticated, otherwise a User object with information about the User logging in
	 */
	public User checkAuthentication(String username, String password) {
		User user = null;
		try {
			sendMessage("authenticate");
			
			User userAuthentication = new User(username, password);
			socketObjectOut.writeObject(userAuthentication);
			user = (User)socketObjectIn.readObject();
		}catch (NoSuchElementException e) {
			System.err.println(e.getStackTrace());
		}catch (ClassNotFoundException e) {
			System.err.println(e.getStackTrace());
		}catch (IOException e) {
			System.err.println(e.getStackTrace());
		}
		return user;
	}
	
	/**
	 * Searches for a course in the database with the supplied courseName. Returns a null Course object if the Course could
	 * not be found, else returns a Course object containing information about the Course being searched.
	 * @param courseName The name of the course to search for
	 * @return A null Course object if the Course could not be found, otherwise a Course object containing information about the Course being searched
	 */
	public Course searchCourse(String courseName) {
		Course result = null;
		try{
			sendMessage("search");
		
			sendMessage(courseName);
			result = (Course)socketObjectIn.readObject();
		}catch (ClassNotFoundException e) {
			System.err.println(e.getStackTrace());
		}catch (IOException e) {
			System.err.println(e.getStackTrace());
		}
		
		return result;
	}
	
	/**
	 * Receives all Courses in the database from the server.
	 * @return A CourseCatalog containing all of the Courses in the database
	 */
	public CourseCatalog receiveAllCourses() {
		CourseCatalog receiveCourses = null;
		try{
			sendMessage("getAllCourses");
			
			receiveCourses = (CourseCatalog)socketObjectIn.readObject();
		}catch (ClassNotFoundException e){
			System.err.println(e.getStackTrace());
		}catch (IOException e) {
			System.err.println(e.getStackTrace());
		}
		return receiveCourses;
	}
	
	/**
	 * Receives all of the Courses that the user is registered in from the server.
	 * @param studentId The id number of the student searching for their registered courses
	 * @return An ArrayList of Registration objects which contains the courses registered by the user
	 */
	public ArrayList<Registration> receiveRegisteredCourses(int studentId) {
		ArrayList<Registration> receiveRegisteredCourses = null;
		try{
			sendMessage("getRegisteredCourses");
			
			sendMessage(studentId + "");
			receiveRegisteredCourses = (ArrayList<Registration>)socketObjectIn.readObject();
		}catch (ClassNotFoundException e) {
			System.err.println(e.getStackTrace());
		}catch (IOException e) {
			System.err.println(e.getStackTrace());
		}
		return receiveRegisteredCourses;
	}
	
	/**
	 * Registers a User with userId in a Section with sectionId.
	 * @param userId The id number of the student registering for a Course Section
	 * @param sectionId The id number of the Section that the user is registering for
	 * @return A String containing information if the registration was successful or not
	 */
	public String registerCourse(String userId, String sectionId) {
		String result = null;
		try {
			sendMessage("register");
			
			sendMessage(sectionId);
			sendMessage(userId);
			result = socketTextIn.readLine();
		}catch (NoSuchElementException e) {
			System.err.println(e.getStackTrace());
		}catch (IOException e) {
			System.err.println(e.getStackTrace());
		}
		
		return result;
	}
	
	/**
	 * Removes a Course registration for the user by sending the registration id of the registration wanting to be removed
	 * from the student and the section id of the Section that the user is removing their registration for.
	 * @param registrationId The id number of the registration to be removed
	 * @param sectionId The id number of the Section that the user wants to remove their registration for
	 * @return A String containing information about if the registration was successfully removed
	 */
	public String removeRegistration(String registrationId, String sectionId) {
		String result = null;
		try{
			sendMessage("removeRegistration");
			
			sendMessage(registrationId);
			sendMessage(sectionId);
			result = socketTextIn.readLine();
			System.out.println(result);
		}catch (NumberFormatException e) {
			System.err.println(e.getStackTrace());
		}catch (NoSuchElementException e) {
			System.err.println(e.getStackTrace());
		}catch (IOException e) {
			System.err.println(e.getStackTrace());
		}
		return result;
	}
	
	/**
	 * Adds a Course to the database with the supplied courseName, courseNumber, sectionNumber, and sectionCapacity
	 * @param courseName The name of the course to create
	 * @param courseNumber The number associated with the course to create
	 * @param sectionNumber The number of Sections to create for the course
	 * @param sectionCapacity The capacity of each Section being created
	 * @return A String containing information if the course was successfully added to the database
	 */
	public String addCourse(String courseName, int courseNumber, int sectionNumber, int sectionCapacity) {
		String result = null;
		try {
			sendMessage("addCourse");
			
			Course newCourse = new Course(courseName + courseNumber, 0);
			for(int i = 0; i < sectionNumber; i++) {
				CourseOffering newOffering = new CourseOffering(0, sectionCapacity, i + 1);
				newCourse.addSection(newOffering);
			}
			socketObjectOut.writeObject(newCourse);
			
			result = socketTextIn.readLine();
		}catch (NoSuchElementException e) {
			System.err.println(e.getStackTrace());
		}catch (IOException e) {
			System.err.println(e.getStackTrace());
		}
		return result;
	}
	
	/**
	 * Sends a message and the userId to the server to notify it that the user has logged out from the client.
	 * @param userId The user id of the user logging out
	 * @return A String containing information if the logout was successful on the server end
	 */
	public String logout(int userId) {
		String result = null;
		try {
			sendMessage("logout");
			
			sendMessage(userId + "");
			result = socketTextIn.readLine();
		}catch (IOException e) {
			System.err.println(e.getStackTrace());
		}
		return result;
	}
	
	/**
	 * Sends a String to the server.
	 * @param message The String to send to the server
	 */
	public void sendMessage(String message) {
		socketTextOut.println(message);
		socketTextOut.flush();
	}

}
