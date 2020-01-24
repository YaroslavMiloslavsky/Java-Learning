/**
 * Class for person with the needed interface
 * This is a standard class for the exercise
 * All the regular setters and getters 
 */
public class Person implements Comparable<Person> {
	private String firstName,lastName,id;
	private int birthYear;
	
	public Person(String firstName, String lastName, String id, int birthYear) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.id = id;
		this.birthYear = birthYear;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getFirstName() {
		return this.firstName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getLastName() {
		return this.lastName;
	}
	public void setID(String id) {
		this.id = id;
	}
	public String getId() {
		return this.id;
	}
	public void setBirthYear(int birthYear) {
		this.birthYear = birthYear;
	}
	public int getBirthYear() {
		return this.birthYear;
	}
	
	@Override
	/**
	 * Compares the objects (Person) alphabetically
	 */
	public int compareTo(Person o) {
		if(this.lastName.compareTo(o.getLastName())<0)
			return -1;
		if(this.lastName.compareTo(o.getLastName())>0)
			return 1;
		if(this.lastName.compareTo(o.getLastName()) == 0) {
			if(this.firstName.compareTo(o.firstName)<0)
				return -1;
			if(this.firstName.compareTo(o.firstName)>0)
				return 1;
		}
		return 0;
	}
	
	public String toString() {
		return String.format("name: %s %s%nID: %s%nyear of birth: %d%n",getFirstName(),getLastName(),getId(),getBirthYear());
	}

}
