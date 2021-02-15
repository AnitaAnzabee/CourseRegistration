import java.io.Serializable;

/**
 * Provides data fields and methods to create a Java data-type, representing a
 * student in a Java application.
 * @author Anita, Nolan, Ben
 *
 */
public class Student implements Serializable {

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
	
	/**
	 * constructs a student object with specific name, id and password
	 * @param studentName name of the student
	 * @param studentId student id
	 * @param password student password
	 */
	public Student (String studentName, int studentId, String password) {
		this.setName(studentName);
		this.setId(studentId);
		this.setPassword(password);
	}

	/**
	 * 
	 * @return student name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * sets the student name
	 * @param name student name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * gets the student ID
	 * @return student id 
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * sets the student id
	 * @param id student id
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * gets the student passwrod
	 * @return student password
	 */
	public String getPassword() {
		return password;
	}
	
	/**
	 * set student password
	 * @param password student password
	 */
	public void setPassword(String password) {
		this.password = password;
	}
}
