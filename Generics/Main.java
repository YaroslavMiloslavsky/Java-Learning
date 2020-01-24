import java.util.Scanner;
/**
 * @author Yaroslav Miloslavsky 
 */
public class Main {
	
	public static void main(String[] args) {
		/*
		 * This is the test for the SetsClass exercise
		 */
		TestProgram test = new TestProgram();
		test.beginTest();
		
		
		Scanner scanLine = new Scanner (System.in);
		System.out.println("\nPress enter to continue to the next test...");
		scanLine.nextLine();
		
		/*
		 * This is the Comparable exercise
		 */
		TestProgramPerson testPersonComparable = new TestProgramPerson();
		testPersonComparable.beginTest();
		System.out.println(testPersonComparable);
		
		scanLine.close();
	}

}
