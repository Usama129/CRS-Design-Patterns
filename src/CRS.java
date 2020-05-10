import java.util.ArrayList;
import java.util.Iterator;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public class CRS {
	
	ArrayList<CourseCatalog> courseTypes = new ArrayList<>();
	ArrayList<Course> mustCourses;
	Scanner sc;
	int opt;
	InputStream reader;
	FacultyMember faculty;
	
	public CRS(ArrayList<CourseCatalog> types, ArrayList<Course> musts, 
			FacultyMember faculty) throws IOException {
		super();
		this.faculty = faculty;
		this.courseTypes = types;
		this.mustCourses = musts;
		
		sc = new Scanner(System.in);
		
		reader = (new EnglishInputStream(new BufferedInputStream(System.in))); 
		// decorator pattern
		exec();
	}
	
	
	private void exec() throws IOException {
		do {
			System.out.println("\nWelcome to CRS."
					+ "\nPress 1 to register to a course as a student"
					+ "\nPress 2 to unregister from a course you are enrolled in"
					+ "\nPress 3 to view remaining quota of a course"
					+ "\nPress 4 if you are a faculty member"
					+ "\nPress 0 to exit from CRS");
			
			opt = sc.nextInt();
			sc.nextLine();
			
			if (opt == 1) {
				registerStudentToCourse();
				exec();
			}
			
			else if (opt == 2) {
				deregisterStudentFromCourse();
			}
			
			else if (opt == 3) {
				displayRemainingQuotaOfCourse();
			}
			
			else if (opt == 4) {
				
				System.out.println("Enter the 5-digit access code for faculty members");
				
				faculty.beginSession(sc.nextInt());
			}
			
		} while (opt != 0);
	}
	
	public void registerStudentToCourse() throws IOException { 
		
		System.out.println("Enter your name:");
		String name = "";
		char n = 0;
		do {
			name += n;
			n = (char)reader.read();
		} while (n != '\n');
		
		System.out.println("Enter your Student ID: ");
		int id = sc.nextInt();
		sc.nextLine();
		System.out.println("Enter the course code you are registering to: ");
		int courseCode = sc.nextInt();
		sc.nextLine();
		
		Student student = new Student(name, id);
		
		boolean enrolled = false;
		
		for (Course c : mustCourses) {
			if (c.getCourseCode() == courseCode) {
				enrolled = c.addStudent(student);
				if (enrolled)
					break;
			}
		}
		
		if (!enrolled) {
			for (CourseCatalog type : courseTypes) {
				if (type instanceof ElectiveCourses) {
					Iterator<Course> iterator = type.createIterator();
					while (iterator.hasNext()) {
						Course course = iterator.next();
						if (course.getCourseCode() == courseCode) {
							enrolled = course.addStudent(student);
							break;
						}
					}
				}
			}
		}
		
		if (enrolled)
			System.out.println("\nYou have successfully registered for the course - CTIS " + courseCode + "\n");
		else
			System.out.println("\nAn error occured. Your registration was not completed successfully.\n");
	}
	
	
	
	public void deregisterStudentFromCourse() {
		System.out.println("Enter your Student ID number");
		int id = sc.nextInt();
		System.out.println("Enter the course code you want to deregister from");
		int cCode = sc.nextInt();
		
		for (Course c : mustCourses) {
			if (c.getCourseCode() == cCode)
				if (c.removeStudent(id)) {
					System.out.println("\nYou have successfully deregistered from CTIS "+cCode);
					return;
				}
			}
		System.out.println("\nAn error occured. "
				+ "This may be because you were not enrolled in this course previously.");
		}
	
	
	// only elective courses can be added during runtime. must courses are stored statically as enum
	public boolean addElective(Course course) {
		for (CourseCatalog type : courseTypes) {
			if (type instanceof ElectiveCourses)
				return ((ElectiveCourses) type).addElective(course);
		}
		return false;
	}
	
	private void displayRemainingQuotaOfCourse() {
		System.out.println("Enter the code of the course for which you wish to "
				+ "see the remaining quota");
		int courseCode = sc.nextInt();
		
		for (Course c : mustCourses)
			if (c.getCourseCode() == courseCode)
				System.out.println("The remaining quota for CTIS " + courseCode
						+ " is " + c.getRemainingQuota() + " out of a total"
								+ " of " + c.getQuota());
	}
	
	public int getNumberofStudentsInCourse(int courseCode) {
		
		for (CourseCatalog type : courseTypes) {
			Iterator<Course> iterator = type.createIterator();
			while (iterator.hasNext()) {
				Course course = iterator.next();
				if (course.getCourseCode() == courseCode)
					return course.getNumberOfStudents();
			}
		}
		
		return -1; // course code not found
	}
	
}
