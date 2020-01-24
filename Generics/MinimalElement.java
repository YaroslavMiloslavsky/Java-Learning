import java.util.Iterator;
/**
 * This class implements the function from Ex1.c 
 */
public class MinimalElement {
	
	/**
	 * Returns the minimal element of a given set of type E alphabetically
	 * @param <E> The type of the set
	 * @param set The set we check
	 * @return The minimal element of the set
	 */
	public static <E extends Comparable<E>> E minElement(SetsClass<E> set) {
		if(set == null)
			return null;
		Iterator<E> itr = set.iterator();
		E min = itr.next();
		
		while(itr.hasNext()) {
			E next = itr.next();
			if(next.compareTo(min)<0)
				min = next;
		}
		return min;
	}
}
