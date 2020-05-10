import java.util.ArrayList;
import java.util.Observable;

public class Course extends Observable {

	private String courseName;
	private int courseCode;
	private boolean mustCourse;
	final static int quota = 10;
	
	private ArrayList<Student> enrolledStudents = new ArrayList<>();


	public Course(String courseName, int courseCode) {
		super();
		this.courseName = courseName;
		this.courseCode = courseCode;
	}
	

	public String getCourseName() {
		return courseName;
	}
	
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	
	public int getCourseCode() {
		return courseCode;
	}
	
	public void setCourseCode(int courseCode) {
		this.courseCode = courseCode;
	}
	
	public boolean addStudent(Student student) {
		boolean success = false;
		if (enrolledStudents.size() < quota) 
			success = enrolledStudents.add(student);
		if (success) {
			setChanged();
	        notifyObservers(enrolledStudents);
		}
		return success;
	}
	
	public boolean removeStudent(int id) {
		boolean success = false;
		for (Student s : enrolledStudents) {
			if (s.getId() == id)
				success = enrolledStudents.remove(s);
		}
		if (success) {
			setChanged();
	        notifyObservers(enrolledStudents);
		}
		return success;
	}
	
	public int getNumberOfStudents() {
		return enrolledStudents.size();
	}

	public boolean isMustCourse() {
		return mustCourse;
	}

	public void setMustCourse(boolean mustCourse) {
		this.mustCourse = mustCourse;
	}
	
	public ArrayList<Student> getEnrolledStudents() {
		return enrolledStudents;
	}
	
	public static int getQuota() {
		return quota;
	}
	
	public int getRemainingQuota() {
		return quota - getNumberOfStudents();
	}
	
}
