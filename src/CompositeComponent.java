
public abstract class CompositeComponent {

	protected String name;
	protected int id;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public String print() {
		return "CompositeComponent [name=" + name + ", id=" + id + "]";
	}
	
}
