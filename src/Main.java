import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Main {

	public static void main(String[] args) throws IOException {

		
		 ArrayList<CourseCatalog> courseTypes = new ArrayList<>();
		 courseTypes.add(new ElectiveCourses()); 
		 MustCoursesFactory musts = new MustCoursesFactory();
		 courseTypes.add(musts); 
		 
		 ArrayList<Course> mustList = Collections.list(musts.getCourses());
		 FacultyMember fm = new FacultyMember(courseTypes, mustList);
		 CRS crs = new CRS(courseTypes, mustList , fm); 
		
		
		 
		 

	}

}
