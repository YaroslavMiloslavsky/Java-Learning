import java.util.Comparator;

/**
 * This class is the monomial structures 
 * This class implements the Comparable interface so we can compare between members of this class
 */
 public class Monom implements Comparable<Monom>{
	private double number;	// The number -> the coefficient
	private int	power;		// The Exponent
	
	/**
	 * The constructor of the class
	 * @param number The coefficient
	 * @param power	 The exponent
	 */
	public Monom(double number, int power) {
		if(power <0)
			throw new IllegalArgumentException("The power can't be negative number"); // The lengths of these arrays must be the same

		this.number = number;
		this.power = power;
	}
	
	/**
	 * Gets the coefficient
	 * @return the coefficient
	 */
	public double getNumber() {
		return this.number;
	}
	/**
	 * Gets the exponent
	 * @return The exponent
	 */
	public int getPower() {
		return this.power;
	}
	/**
	 * Sets the coefficient
	 * @param number The coefficient that we'd like to set
	 */
	public void setNumber(double number) {
		this.number = number;
	}
	/**
	 * Sets the exponent
	 * @param power The exponent that we'd like to set
	 */
	public void setPower(int power) {
		this.power = power;
	}
	
	/**
	 * The toString method that overrides the method from Object class
	 */
	public String toString() {	
		if(getNumber() == 0)
			return "";
		if(getNumber() == 1 && getPower() == 0)
			return String.format("%s%d", (getNumber()>0)? "+":"", 1); // x^0 = 1
		if(getNumber() == 1)
			return String.format("%sx^%d", (getNumber()>0)? "+":"", getPower()); // 1x^y = x^y
		if(getPower() == 0)
			return String.format("%s%.1f", (getNumber()>0)? "+":"",getNumber()); //if there is no x -> x^0
		if(getPower() == 1)
			return String.format("%s%.1fx", (getNumber()>0)? "+":"",getNumber()); //if there is only x -> x^1
		else 
			return String.format("%s%.1fx^%d",(getNumber()>0)?"+":"", getNumber(), getPower()); //normal monom -> x^2>>
	}
	
	/**
	 * The comparator of the interface so we can compare objects from the same class
	 */
	public int compareTo(Monom anotherMonom) {
        return Comparators.POWER.compare(this, anotherMonom);
    }

	static class Comparators {
		// This method sets that we compare the Monomials by their exponents
        static Comparator<Monom> POWER = new Comparator<Monom>() {
            @Override
            public int compare(Monom o1, Monom o2) {
                return o1.power - o2.power;
            }
        };
	}

}
