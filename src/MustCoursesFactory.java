import java.util.Enumeration;
import java.util.Vector;
import java.util.Iterator;

public class MustCoursesFactory extends CompositeComponent implements CourseCatalog {

	private Enumeration<Course> courses;
	
	public Enumeration<Course> getCourses() {
		return courses;
	}

	Vector<Course> courseObjects = new Vector<Course>();
	
	public MustCoursesFactory() {
		courseObjects.add(new Course("Algorithms and Data Structures", 152));
		courseObjects.add(new Course("Technical Mathematics with Programming", 164));
		courseObjects.add(new Course("Discrete Mathematics", 163));
		courseObjects.add(new Course("Principles of Software Engineering", 359));
		courses = courseObjects.elements();
	}
		
	@Override
	public Iterator<Course> createIterator() {
		return new EnumerationIterator(courses);
	}
	
	@Override 
	public String print() {
		String out = "";
		for (Course course: courseObjects) {
			out += "\nCTIS "+ course.getCourseCode() + " " + course.getCourseName();
		}
		return out;
	}

}
