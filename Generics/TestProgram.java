import java.util.Scanner;

/**
 * A test to the second program as asked in Ex1.b
 */
public class TestProgram {
	private SetsClass<Integer> set1;
	private SetsClass<Integer> set1Clone; // this is the set for set1 operations so set1 won't be changed permanently
	private SetsClass<Integer> set2;
	private SetsClass<Integer> set3;
	private SetsClass<Integer> set4;
	private final int MAX = 10;
	private Scanner scan = new Scanner(System.in);
	
	/**
	 * Constructor
	 * Fills 3 groups with random numbers from 0-100
	 */
	public TestProgram() {
		
		set1 = new SetsClass<Integer>();
		set1Clone = new SetsClass<Integer>();
		set2 = new SetsClass<Integer>();
		set3 = new SetsClass<Integer>();
		set4 = new SetsClass<Integer>();

		while(set1.getObjectList().size()<MAX) {
			Integer randomInteger = getRandomNumber();
			set1.insert(randomInteger);
			set1Clone.insert(randomInteger);
		}
		while(set2.getObjectList().size()<MAX)
			set2.insert(getRandomNumber());
		while(set3.getObjectList().size()<MAX)
			set3.insert(getRandomNumber());
	}
	
	/**
	 * The test begins here
	 */
	public void beginTest() {
		this.printSets(); // prints the 3 sets
		this.unionFirstAndSecond(); // makes a union of the first and the second set
		try {
		this.intersectFirstAndThird(); // intersects the first and the third set
		}catch (StringIndexOutOfBoundsException e) {
			System.out.println("The intersect of set 1 and set 3 is empty"); // if the intersection is empty
		}
		this.createSetAndCheck(); // create the 4th set and checks what is needed by the work book
		this.lastBigTask(); // the last task in the work book - check if the new number belongs to the first set -> add the number to the second set -> delete the numebr from the thirs set
	}
	
	private Integer getRandomNumber() {
		final int max=100 , min = 0;
		return (int)(Math.random() * ((max - min) + 1)) + min;
	}
	
	private void printSets() {
		System.out.println("set 1 : " + set1);
		System.out.println("set 2 : " + set2);
		System.out.println("set 3 : " + set3);
	}
	
	private void unionFirstAndSecond() {
		set1Clone.union(set2);
		System.out.println("The union of set 1 and set 2 is : " + set1Clone);
	}
	
	private void intersectFirstAndThird() {
		set1Clone.intersect(set3);
		System.out.println("The intersect of set 1 and set 3 is : " + set1Clone);
	}
	
	private void createSetAndCheck() {
		System.out.println("Please enter 2 Integers");
		for(int i = 0 ; i<2 ; i++)
			set4.insert(scan.nextInt());
		System.out.println("new set : " + set4);

		if(set1.isSubset(set4))
			System.out.println("The new set is sub set of set 1");
		if(set2.isSubset(set4))
			System.out.println("The new set is sub set of set 2");
		if(set3.isSubset(set4))
			System.out.println("The new set is sub set of set 3");
		if(!set1.isSubset(set4) && !set2.isSubset(set4) && !set3.isSubset(set4))
			System.out.println("The new set is not a subset of any other set");
	}
	
	private void lastBigTask() {
		System.out.println("Please enter a number");
		Integer number = scan.nextInt();
		System.out.println((set1.getObjectList().contains(number))? "the number belongs to set 1":"the number does not belong to set 1");
		if(!set2.getObjectList().contains(number)) {
			set2.insert(number);
			System.out.println("Set 2 after addition : " + set2);
		}
		else System.out.println("Set 2 already contains " + number);
		
		if(set3.getObjectList().contains(number)) {
			set3.delete(number);
			System.out.println("Set 3 after deletion : " + set3);
		}
		else System.out.println("Nothing to delete, set 3 doesn't contain " + number);
	}
}
