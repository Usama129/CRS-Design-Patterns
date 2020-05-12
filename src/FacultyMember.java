import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;

public class FacultyMember extends CompositeComponent implements Observer {
	
	ArrayList<Course> courses = new ArrayList<>();

	Scanner sc;
	
	public FacultyMember(ArrayList<CourseCatalog> courseTypes, ArrayList<Course> courses) {
		
		sc = new Scanner(System.in);
		
		for (Course c : courses)
			this.courses.add(c);
	}

	public void beginSession(int accessCode) {
		if (accessCode == 12345) {
			
			
			
			System.out.println("\nYou have authorized as a Faculty Member.");
			
			char opt = 1;
			while (opt != 0) {
				System.out.println("\nPress A to view the complete list of currently offered courses and the "
						+ "students enrolled in each course\nPress 0 to end your session as a Faculty Member");
				opt = sc.next().charAt(0);
				
				if ((int)'a' == opt || (int)'A' == opt) {
					System.out.println(print());
				}
				else if (opt == '0') {
					break;
				}
				else 
					System.out.println("\nInvalid option.");
			}
		}
		else 
			System.out.println("You have entered an incorrect access code");
	}
	
	
	@Override 
	public String print() {
		String out = "";
		for (Course course: courses) {
			out += "\nCTIS "+ course.getCourseCode() + " " + course.getCourseName();
			for (Student student : course.getEnrolledStudents()) {
				out += student.print();
			}
		}
		return out;
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
