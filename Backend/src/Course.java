import java.io.Serializable;
import java.util.ArrayList;

/**
 * Provides data fields and methods to create a Java data-type, representing a
 * course in a Java application.
 * @author Anita, Nolan, Ben
 *
 */
public class Course implements Serializable {


	private static final long serialVersionUID = 1L;
	/**
	 * name of the course
	 */
	private String courseName;
	/**
	 * id of the course assigend as primary key in course table in database
	 */
	private int courseId;
	/**
	 * array of courseoffering for the course
	 */
	private ArrayList<CourseOffering> sections;
	
	/**
	 * constructs a course object with specific name and id
	 * @param name of the course
	 * @param id of the course
	 */
	public Course(String name, int id) {
		courseName = name;
		courseId = id;
		sections = new ArrayList<CourseOffering>();
		
	}
	
	/**
	 * adds a courseoffering to the courseoffering list
	 * @param section
	 */
	public void addSection(CourseOffering section) {
		sections.add(section);
	}
	
	/**
	 * 
	 * @return the courseoffering list
	 */
	public ArrayList<CourseOffering> getSections(){
		return sections;
	}
	
	/**
	 * get the course id
	 * @return course id
	 */
	public int getCourseId() {
		return courseId;
	}
	
	/**
	 * gets the course name
	 * @return name of the course
	 */
	public String getCourseName() {
		return courseName;
	}
}
