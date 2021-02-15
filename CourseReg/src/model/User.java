package model;
import java.io.Serializable;

/**
 * Provides data fields and methods to create a Java data-type, representing a
 * student in a Java application.
 * @author Anita, Nolan, Ben
 *
 */
public class User implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * student name
	 */
	private String name;
	/**
	 * Id of the student assigned as primary key in student table in database
	 */
	private int id;
	/**
	 * student password
	 */
	private String password;
	private String role;
	
	/**
	 * constructs a student object with specific name, id and password
	 * @param studentName name of the student
	 * @param studentId student id
	 * @param password student password
	 */
	public User (String name, int id, String password, String role) {
		this.name = name;
		this.id = id;
		this.password = password;
		this.role = role;
		
	}
	
	public User(String name, String password) {
		this.name = name;
		this.password = password;
	}
	
	/**
	 * 
	 * @return student name
	 */
	public String getName() {
		return name;
	}
	

	/**
	 * gets the student ID
	 * @return student id 
	 */
	public int getId() {
		return id;
	}
	
	
	/**
	 * gets the student passwrod
	 * @return student password
	 */
	public String getPassword() {
		return password;
	}



	public String getRole() {
		return role;
	}
}
