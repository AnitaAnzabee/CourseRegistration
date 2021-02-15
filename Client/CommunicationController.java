import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.NoSuchElementException;

public class CommunicationController {
	private Socket aSocket;
	private BufferedReader socketTextIn;
	private PrintWriter socketTextOut;
	private ObjectOutputStream socketObjectOut;
	private ObjectInputStream socketObjectIn;
	
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
	
	public void sendMessage(String message) {
		socketTextOut.println(message);
		socketTextOut.flush();
	}

}