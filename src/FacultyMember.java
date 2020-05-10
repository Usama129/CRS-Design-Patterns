import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;

public class FacultyMember implements Observer {
	
	ArrayList<Course> courses = new ArrayList<>();

	Scanner sc;
	
	public FacultyMember(ArrayList<CourseCatalog> courseTypes, ArrayList<Course> courses) {
		
		sc = new Scanner(System.in);
		
		for (Course c : courses)
			this.courses.add(c);
	}

	public void beginSession(int accessCode) {
		if (accessCode == 12345) {
			
			
			
			System.out.println("Enter the course code for which you would like to view enrolled students.");
			int c = sc.nextInt();
			printStudents(getCourseStudents(c));
		}
		else 
			System.out.println("You have entered an incorrect access code");
	}
	
	private ArrayList<Student> getCourseStudents(int courseCode) {
		for (Course course : courses) {
			if (course.getCourseCode() == courseCode)
				return course.getEnrolledStudents();
		}
		return null;
	}
	
	private void printStudents(ArrayList<Student> students) {
		if (students == null) {
			System.out.println("There is no course with the code you entered");
			return;
		}
		
		if (students.isEmpty()) {
			System.out.println("\nThere are no students enrolled in this course");
			return;
		}
		
		for (Student student : students) {
			System.out.println("Name: " + student.getName() + " ID: " + student.getId());
		}
	}
	
	public ArrayList<Course> getCourses() {
		return courses;
	}

	public void setCourses(ArrayList<Course> courses) {
		this.courses = courses;
	}

	@Override
	public void update(Observable o, Object arg) {
		courses = (ArrayList<Course>) arg;
	}

	
}
