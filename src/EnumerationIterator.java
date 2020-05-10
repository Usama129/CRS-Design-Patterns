import java.util.Enumeration;
import java.util.Iterator;

public class EnumerationIterator implements Iterator<Course> {

	Enumeration<Course> myEnum;
	
	public EnumerationIterator(Enumeration<Course> myEnum) {
		super();
		this.myEnum = myEnum;
	}

	@Override
	public boolean hasNext() {
		return myEnum.hasMoreElements();
	}

	@Override
	public Course next() {
		return myEnum.nextElement();
	}

}
