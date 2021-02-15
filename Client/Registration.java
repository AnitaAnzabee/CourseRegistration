import java.io.Serializable;

/**
 * Provides data fields and methods to create a Java data-type, representing a
 * registration of students in a Java application.
 * @author Anita, Nolan, Ben
 *
 */
public class Registration implements Serializable{


	private static final long serialVersionUID = 1L;
	/**
	 * the ID of the registration that is the primary key of the reigstation in registration table of the database
	 */
	private int registerId;
	/**
	 * the ID of the section that is the primary key of the section in sections table of the database
	 */
	private int sectionId; 
	/**
	 * the ID of the student that is the primary key of the student in the student table of the database
	 */
	private int studentId;
	/**
	 * the ID of the course that is the primary key of the course in the course table of the database
	 */
	private int courseId;
	/**
	 * section number
	 */
	private int secNum; 
	/**
	 * name of the course
	 */
	private String courseName;
	
	/**
	 * constructs a Registration object with specific values for the datafield
	 * @param sectionId id of the section
	 * @param studentId id of the student
	 * @param courseId id of the course
	 * @param secNum section number
	 * @param courseName name of the course
	 */
	public Registration(int registerId, int sectionId, int studentId, int courseId, int secNum, String courseName) {
		this.setRegisterId(registerId);
		this.setSectionId(sectionId);
		this.setStudentId(studentId);
		this.setCourseId(courseId);
		this.setSecNum(secNum);
		this.setCourseName(courseName);
	}
	
	/**
	 * 
	 * @return the id of the section
	 */
	public int getSectionId() {
		return sectionId;
	}
	
	/**
	 * set the id of the section
	 * @param sectionId
	 */
	public void setSectionId(int sectionId) {
		this.sectionId = sectionId;
	}
	
	/**
	 * 
	 * @return the id of the student
	 */
	public int getStudentId() {
		return studentId;
	}
	
	/**
	 * set the id of the student
	 * @param studentId
	 */
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	
	/**
	 * 
	 * @return the course id
	 */
	public int getCourseId() {
		return courseId;
	}
	
	/**
	 * set the course id
	 * @param courseId
	 */
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
	
	/**
	 * 
	 * @return section number
	 */
	public int getSecNum() {
		return secNum;
	}
	
	/**
	 * set the section number
	 * @param secNum
	 */
	public void setSecNum(int secNum) {
		this.secNum = secNum;
	}
	
	/**
	 * 
	 * @return course name
	 */
	public String getCourseName() {
		return courseName;
	}
	
	/**
	 * set the course name
	 * @param courseName
	 */
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	
	/**
	 * 
	 * @return registration Id
	 */
	public int getRegisterId() {
		return registerId;
	}
	
	/**
	 * set the registration Id
	 * @param registerId
	 */
	public void setRegisterId(int registerId) {
		this.registerId = registerId;
	}
	
	@Override
	public String toString() {
		String object = "";
		object += getCourseName() + " L0" + getSecNum();
		return object;
	}
}
