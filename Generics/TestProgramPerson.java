/**
 * A test to the second program as asked in Ex1.c
 */
public class TestProgramPerson {
	private SetsClass<Person> personSet;
	
	public TestProgramPerson() {
		personSet = new SetsClass<Person>();
	}
	/**
	 * I used names that are convenient to check the alphabetical order
	 * You may change as you see fit
	 */
	public void beginTest() {
		personSet.insert(new Person("Josh","Smith","12345",1990));
		personSet.insert(new Person("Andjey","Skapolski","23454",1954));
		personSet.insert(new Person("Adam","Bedameniev","90876",1992));
		personSet.insert(new Person("Vasja","Atramick","23422",1980));
		personSet.insert(new Person("Valodja","Atramick","33454",1976));
		
		System.out.println();
		for(Person person : personSet.getObjectList())
			System.out.println(person);
		
	}

	public String toString() {
		return String.format("The smallest person lexicographically is %n%s", MinimalElement.minElement(this.personSet));
	}
}
