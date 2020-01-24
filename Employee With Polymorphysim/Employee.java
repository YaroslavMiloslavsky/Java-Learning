import java.util.Calendar;
/**
 * This is and abstract class for employee template
 * Nothing special here, mostly standard getters and setters
 */

public abstract class Employee {
	private final String firstName;
	private final String lastName;
	private final String socialSecurityNumber;
	private final BirthDate date;
	
	public Employee(String firstname, String lastName, String socialSecurityNumber, int day, int month, int year) {
		this.date = new BirthDate(day, month, year);
		this.firstName = firstname;
		this.lastName  = lastName;
		this.socialSecurityNumber = socialSecurityNumber;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public String getSocialSecurityNumber() {
		return socialSecurityNumber;
	}
	
	public String getBirthDay() {
		return this.date.toString();
	}
	
	public int getMonth() {
		return this.date.getMonth()-1;
	}
	
	public int getAge() {
		Calendar c = Calendar.getInstance();
		int currentyear = c.get(Calendar.YEAR);
		return currentyear - date.getYear(); // calculates the employee age
	}
	
	@Override
	public String toString() {
		return String.format("%s %s %d years old%nsocial security number: %s;%ndate of birth: %s", getFirstName(),getLastName(),getAge(),getSocialSecurityNumber(),getBirthDay().toString());
	}
	
	public abstract double earnings(); // this must be implemented in all sub classes
}
