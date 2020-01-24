import java.util.ArrayList;
import java.util.Iterator;
/**
 * Generic type class for generic sets
 * @param <E>
 */

public class SetsClass<E> {
	private ArrayList<E> objectsList; // The ArrayList that is used to store the generic data
	
	/**
	 * Constructor
	 */
	public SetsClass(){
		objectsList = new ArrayList<E>(); 
	}
	
	/**
	 * Constructor that receives an array and makes a set with its objects
	 * @param array The array that contains the objects
	 */
	public SetsClass(E[] array) {
		objectsList = new ArrayList<E>(); 
		
		for(E element : array) {
			if(!objectsList.contains(element))
				objectsList.add(element);
		}
	}
	
	/**
	 * This function is for encapsulation
	 * @return The set of objects we store
	 */
	public ArrayList<E> getObjectList(){
		return this.objectsList;
	}
	
	/**
	 * Intersects this set with the given set
	 * The changes are done to this set
	 * @param subset The set to be intersected with
	 */
	public void intersect(SetsClass<E> subset){
		ArrayList<E> toRemove = new ArrayList<E>();
		// if element is not present in this set, add it
		for(E element : this.getObjectList())
			if(!subset.getObjectList().contains(element))
				toRemove.add(element);
		
		this.getObjectList().removeAll(toRemove);	 // remove all the elements from the first array		
			
	}
	
	/**
	 * Unifies this set with a given set
	 * The changes are done to this set
	 * @param subset The set to be unified with
	 */
	public void union(SetsClass<E> subset) {
		ArrayList<E> toAdd = new ArrayList<E>();
		// add all the elements from the given subset to a new array
		for(E element : subset.getObjectList())
			toAdd.add(element);
		// if this element already exists in this set, remove it
		for(E element : this.getObjectList())
			if(this.getObjectList().contains(element))
				toAdd.remove(element);
		// add all the union set
		this.getObjectList().addAll(toAdd);
	}
	
	/**
	 * Checks if a given set is subset of this set
	 * @param subset The subset to check
	 * @return True if the given set is a subset
	 */
	public boolean isSubset(SetsClass<E> subset) {
		boolean isSubset = true;
		for(E element : subset.getObjectList()) 
			if(!this.getObjectList().contains(element))
				isSubset=false;		
		return isSubset;
	}
	
	/**
	 * Checks if a given element is a member of this set
	 * @param element The element to inspect
	 * @return True if the element is a member of this set
	 */
	public boolean isMember(E element) {
		return this.getObjectList().contains(element);
	}
	
	/**
	 * Inserts element in this set if the element does not exist
	 * @param element The element to be added to the set
	 */
	public void insert(E element) {
		if(!this.getObjectList().contains(element))
			this.getObjectList().add(element);
	}
	
	/**
	 * Deletes a given element if it exists
	 * @param element The element to delete
	 */
	public void delete(E element) {
		if(this.getObjectList().contains(element))
			this.getObjectList().remove(element);
	}
	
	/**
	 * @return iterator of this type
	 */
	public Iterator<E> iterator(){
		return this.getObjectList().iterator();
	}
	
	/**
	 * Prints the elements of this set
	 */
	public String toString() {
		String con = new String();
		for(E element: objectsList)
			con += element + ", ";
		
		return con.substring(0,con.length()-2);
	}
	
}
