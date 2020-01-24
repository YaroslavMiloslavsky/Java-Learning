import java.util.ArrayList;
import java.util.Collections;

/**
 * The class that represents the polynomial itself, with all the needed methods
 * Please note that although the term 'power' is used, we refer to the exponent
 */
class Polynom {
	private ArrayList<Monom> polynom; // The polynomial representation of the data
	private double[] coef;	// The array of the coefficients - this will be needed for methods that require the data of the main ArrayList
	private int[] pow; 		// The array of the power(exponent) - this will be needed for methods that require the data of the main ArrayList
	
	/**
	 * The empty constructor of the class
	 */
	public Polynom() {
		polynom = new ArrayList<Monom>();
	}
	
	/**
	 * The constructor of the class, but this methods receives exponent array and coefficient array and makes the polynomial with the provided data
	 * @param coefficient The coefficients of the polynomial
	 * @param exponent	The exponent array of the polynomial
	 */
	public Polynom(double[] coefficient, int[] exponent) {
		coef = coefficient;
		pow = exponent;
		if(exponent.length != coefficient.length)
			throw new IllegalArgumentException("The power array must be equal to the coefficient array");
		
		polynom = new ArrayList<Monom>();
		Monom[] array = new Monom[exponent.length];	// array of monomials which is the polynomial starting position
		
		// the monomials are put in the array above in pairs in which they come bounded
		for(int i=0 ; i<array.length; i++)
			array[i] = new Monom(coefficient[i],exponent[i]);	
		
		bubbleSort(array); 	// we sort the polynomial by the exponent
		reverse(array); 	// we reverse the polynomial
		correct(array); 	// corrects the polynomial
	}
	
	/**
	 * The method takes a polynomial and sums all the monomials with the same exponent
	 * @param array The array of monomials that gets corrected
	 */
	private void correct(Monom[] array) {
		for(int i=0; i< array.length; i++) {
			if(i+1 < array.length) { 
				if(array[i].getPower() == array[i+1].getPower())
					array[i+1].setNumber(array[i].getNumber() + array[i+1].getNumber());
				else {
					polynom.add(array[i]);
				}	
			}
		}
		polynom.add(array[array.length-1]);
	}
	
	/**
	 * The method reverses the monomials array for the correct positioning of the exponents
	 * @param array The array to be reversed
	 */
	private void reverse(Monom[] array) {
		int start = 0;
		int end = array.length-1;
		
		while(start<=end) {
			Monom temp = array[start];
			array[start] = array[end];
			array[end] = temp;
			
			start++;
			end--;
		}
	}

	/**
	 * A regular bubble sort O(n^2) complexity
	 * With the size of polynomials, this is acceptable
	 * We could use any sort, but this is the simplest one to implement
	 * If time complexity was our goal, we would probably use merge-sort or even better - a base sort (linear sort)
	 * @param array The array to be sorted
	 */
	private void bubbleSort(Monom[] array) {		
		 int n = array.length; 
	        for (int i = 0; i < n-1; i++) 
	            for (int j = 0; j < n-i-1; j++) 
	                if (array[j].getPower() > array[j+1].getPower()) 
	                { 
	                    Monom temp = array[j]; 
	                    array[j] = array[j+1]; 
	                    array[j+1] = temp; 
	                } 	
	}

	/**
	 * equals method that overrides equals from Object class
	 */
	public boolean equals(Object array) {
		// if this object is what we compare
		if(array == this)
			return true;
		// if this object is not a polynomial we should not compare
		if(!(array instanceof Polynom))
			return false;
		
		Polynom arrayPolynom = (Polynom) array; // casting so we can use the Polynom class methods
		
		boolean state = true;
		for(int i=0 ; i< polynom.size()-1 ; i++) {
			if(this.polynom.get(i).getPower() != arrayPolynom.getMonom(i).getPower() || this.polynom.get(i).getNumber() != arrayPolynom.getMonom(i).getNumber())
				state = false;
		}
		return state;
	}
	
	/**
	 * This method gets the monom in a given index
	 * @param index The index of the monom we need
	 * @return The monom from the index that was given
	 */
	public Monom getMonom(int index) {
		return polynom.get(index);
	}
	
	/**
	 * This method gives access to the polynomial of this class - but only inside this class
	 * @return The polynomial that we write the data to
	 */
	private ArrayList<Monom> getPolynomial(){
		return this.polynom;
	}
	
	/**
	 * This methods returns the size of the polynomial we maintain
	 * @return The size of the polynomial
	 */
	public int getSize() {
		return this.polynom.size();
	}
	
	/**
	 * This method differentiate the data in the polynom
	 * This method does not implement shallow copy, instead this method uses the arrays we stored from the constructor
	 * So the actions are performed on a fresh copy of the polynomial ArrayList<Monom> polynom
	 * @return Differentiated new Polynom
	 */
	public Polynom differentiate() {
		Polynom temp = new Polynom();
		Polynom newThisPoly = new Polynom(this.coef, this.pow);
		for(Monom mon : newThisPoly.getPolynomial()) {
			if(mon.getPower() != 0) {
				mon.setNumber(mon.getPower()*mon.getNumber());
				mon.setPower(mon.getPower()-1);
				temp.getPolynomial().add(mon);
			}
			else {
				mon.setNumber(0);
				temp.getPolynomial().add(mon);
			}	
		}
		return temp;
	}
	
	/*
	 * Auxiliary methods
	 * These 2 methods are solely for the new copy of the polynomial so we can alter a new copy of the ArrayList polynom
	 */
	private double[] getCoef() {
		return this.coef;
	}
	
	private int[] getPow() {
		return this.pow;
	}
	/*
	 * End of auxiliary methods
	 */
	
	/**
	 * This methods sums two polynomials 
	 * @param p The polynomial we are about the sum with
	 * @return A new copy of the sum of the polynomials
	 */
	public Polynom plus(Polynom p) {
		Polynom temp = new Polynom();
		Polynom newThisPoly = new Polynom(this.coef, this.pow);
		Polynom newOtherPoly = new Polynom(p.getCoef(),p.getPow());
		
		// adding this polynomial to the array
		for(Monom mon : newThisPoly.getPolynomial())
			temp.getPolynomial().add(mon);
		
		// adding the second polynomial array
		for(Monom mon :newOtherPoly.getPolynomial())
			temp.getPolynomial().add(mon);
		
		// sort in reverse oreder
		Collections.sort(temp.getPolynomial() , Monom.Comparators.POWER);
		
		ArrayList<Monom> toRemove = new ArrayList<Monom>();
		for(int i=0 ; i< temp.getSize()-1 ; i++) {
			if( temp.getMonom(i).getPower() == temp.getMonom(i+1).getPower()) {
				toRemove.add(temp.getMonom(i));
				temp.getMonom(i+1).setNumber(temp.getMonom(i+1).getNumber() + temp.getMonom(i).getNumber());
			}
		}
		temp.getPolynomial().removeAll(toRemove);
		Collections.reverse(temp.getPolynomial());
		return temp;
	}
	
	/**
	 * This method reverses a Polynom and sums this Polynomial with the negative one
	 * Similar to the function addi &reg1, &reg2, -(int)
	 * @param p The polynom we change the signs to the negative ones
	 * @return A new copy that is the difference of the 2 polynomials
	 */
	public Polynom minus(Polynom p) {
		Polynom temp = new Polynom();
		Polynom newThisPoly = new Polynom(this.coef, this.pow);
		Polynom newOtherPoly = new Polynom(p.getCoef(),p.getPow());
		
		// adding this polynomial to the array
		for(Monom mon : newThisPoly.getPolynomial())
			temp.getPolynomial().add(mon);
		
		// adding the second polynomial array - but reverse the sign
		for(Monom mon : newOtherPoly.getPolynomial()) {
			mon.setNumber(-(mon.getNumber()));
			temp.getPolynomial().add(mon);
		}
			
		// sort in reverse order
		Collections.sort(temp.getPolynomial() , Monom.Comparators.POWER);
		
		ArrayList<Monom> toRemove = new ArrayList<Monom>();
		for(int i=0 ; i< temp.getSize()-1 ; i++) {
			if( temp.getMonom(i).getPower() == temp.getMonom(i+1).getPower()) {
				toRemove.add(temp.getMonom(i));
				temp.getMonom(i+1).setNumber(temp.getMonom(i+1).getNumber() + temp.getMonom(i).getNumber());
			}
		}
		temp.getPolynomial().removeAll(toRemove);
		Collections.reverse(temp.getPolynomial());
		return temp;
	}
	
	/**
	 * The toString method we override from Object class
	 */
	public String toString() {
		String polyStr = new String();
		
		for(Monom monom: polynom) 
			polyStr += monom.toString();
		
		if(polynom.get(0).getNumber()>0)
			polyStr = polyStr.substring(1);
		return polyStr;
	}
}
