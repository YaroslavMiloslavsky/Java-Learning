import java.util.ArrayList;

public class Graph<E> {
	private ArrayList<E> data;
	
	public Graph() {
		data = new ArrayList<E>();
	}
	public void addObject(E obj) throws ObjectExistsException{
		if(data.contains(obj))
			throw new ObjectExistsException("This object already exists");
		data.add(obj);
	}
	public ArrayList<E> getArr(){
		return this.data;
	}
}
