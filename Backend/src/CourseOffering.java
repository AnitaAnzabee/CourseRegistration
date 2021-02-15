import java.io.Serializable;

/**
 * Provides data fields and methods to create a Java data-type, representing a
 * CourseOffering in a Java application.
 * @author Anita, Nolan, Ben
 *
 */
public class CourseOffering implements Serializable {


	private static final long serialVersionUID = 1L;
	/**
	 * the id of the section assigned as primary key in sections table in database
	 */
	private int sectionId;
	/**
	 * capactiy of the section
	 */
	private int capacity;
	/**
	 * number of the section
	 */
	private int sectionNum;
	
	/**
	 * constructs a CourseOffering object given the specific values for sectionId, capacity,
	 * and sectionNum
	 * @param id secion id
	 * @param cap capacity of the section
	 * @param num section number
	 */
	public CourseOffering(int id, int cap, int num) {
		sectionId = id;
		capacity = cap;
		sectionNum = num;
	}
	
	/**
	 * returs a section id
	 * @return sectionId
	 */
	public int getSectionId() {
		return sectionId;
	}
	
	/**
	 * returns the capacity of the course
	 * @return capacity
	 */
	public int getSectionCapacity() {
		return capacity;
	}
	
	/**
	 * returns the section number
	 * @return sectionNum
	 */
	public int getSectionNum() {
		return sectionNum;
	}
}
