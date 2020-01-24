import java.util.Scanner;
/**
 * The main class asks the user for 2 polynomials and shows all the cases that were asked in the question 
 * @author Yaroslav Miloslavsky 
 *
 */
public class Main {
	static Scanner scan = new Scanner(System.in);
	static final int MAX = 1000; // We assume that polynomials won't be larger than that, otherwise just tune the number to your liking
	public static void main(String[] args) {
		
		// The data for all the main file	
		double[] polynom1NumArray1 = new double[MAX];
		double[] polynom1NumArray2 = new double[MAX];
		int[] polynomPowArray1 = new int[MAX];
		int[] polynomPowArray2 = new int[MAX];
		Polynom polynomial1 = new Polynom();
		Polynom polynomial2 = new Polynom();
		int i = 0;
		String choice = "";
		
		System.out.println("Welcome to the Polynom showcase!\n");
		System.out.println("Please enter the first polynom data - notice that you enter monomials");
	
		// please don't type anything but 'y' or 'n'
		while(!choice.equals("n")) { // first polynomial
			// the order is coefficient -> power
			System.out.println("Please enter a coefficient (double)");
			polynom1NumArray1[i] = scan.nextDouble();
			System.out.println("Please enter a power (int)");
			polynomPowArray1[i] = scan.nextInt();
			i++;
			System.out.println("Do you want to enter another monomial? y/n");
			choice = scan.next();
		}
		
		choice ="";
		i=0;
		System.out.println("\nPlease enter the second polynom data - notice that you enter monomials");
		while(!choice.equals("n")) { // second polynomial
			// the order is coefficient -> power
			System.out.println("Please enter a coefficient (double)");
			polynom1NumArray2[i] = scan.nextDouble();
			System.out.println("Please enter a power (int)");
			polynomPowArray2[i] = scan.nextInt();
			i++;
			System.out.println("Do you want to enter another monomial? y/n");
			choice = scan.next();
		}
		
		try { // the polynomials are made here from all the data that was provided
			polynomial1 = new Polynom(polynom1NumArray1,polynomPowArray1);
			polynomial2 = new Polynom(polynom1NumArray2,polynomPowArray2);
		}catch(IllegalArgumentException e) {
			System.out.println("Oops.. looks like you entered a negative power\nexiting program now.....");
		}
		System.out.println();
		
		try {
			// All the exercises are shown here 
			System.out.println("The polynoms are " + polynomial1 + " and " + polynomial2);
			System.out.print("The sum of these polynomials is: ");
			System.out.println((polynomial1.plus(polynomial2).getMonom(0).getNumber() == 0)? "0": polynomial1.plus(polynomial2));
			System.out.print("The difference of these polynomials is: ");
			System.out.println((polynomial1.minus(polynomial2).getMonom(0).getNumber() == 0)? "0": polynomial1.minus(polynomial2));
			System.out.println("The differential of " + polynomial1 + " is: " + polynomial1.differentiate());
			System.out.println("The differential of " + polynomial2 + " is: " + polynomial2.differentiate());
			System.out.println((polynomial1.equals(polynomial2))? "These polynomials are equal":"There polynomials are not equal");
		}catch(IllegalArgumentException e) {
			System.out.println("Oops.. errors\nexiting program now.....");
		}
	}
}
