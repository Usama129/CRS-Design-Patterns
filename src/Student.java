
public class Student extends CompositeComponent {
	
	public Student(String name, int id) {
		super();
		this.name = name;
		this.id = id;
	}

	
	public String print() {
		return "\nName: " + name + "ID: " + id + "\n";
	}
	
}
