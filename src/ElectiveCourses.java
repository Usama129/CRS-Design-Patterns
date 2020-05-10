import java.util.ArrayList;
import java.util.Iterator;

public class ElectiveCourses implements CourseCatalog {

	private ArrayList<Course> courses = new ArrayList<>();
	
	@Override
	public Iterator<Course> createIterator() {
		return courses.iterator();
	}
	
	public boolean addElective(Course course) {
		return courses.add(course);
	}
	
}
