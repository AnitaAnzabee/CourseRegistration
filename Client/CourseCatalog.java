import java.io.Serializable;
import java.util.ArrayList;
/**
 * Provides data fields and methods to create a Java data-type, representing a
 * CourseCatalog in a Java application.
 * @author Anita, Nolan, Ben
 *
 */
public class CourseCatalog implements Serializable {


	private static final long serialVersionUID = 1L;
	/**
	 * a list of courses
	 */
	private ArrayList<Course> courseList = new ArrayList<Course>(); 
	
	/**
	 * add a course to the courseList
	 * @param course the Course object that is being added to the list
	 */
	public void addCourse(Course course) {
		courseList.add(course);
	}
	
	/**
	 * 
	 * @return the courselist with added courses
	 */
	public ArrayList<Course> getCatalog() {
		return courseList;
	}
	
	@Override
	public String toString() {
		String object = "";
		object += "---------------------------------------------------\n";
		for(Course c : courseList) {
			object += c.toString();
			object += "---------------------------------------------------\n";
		}
		return object;
	}
}
