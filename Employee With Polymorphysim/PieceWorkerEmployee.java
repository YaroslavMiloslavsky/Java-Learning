/**
 * Piece worker employee class
 * Usual setters and getters
 * Earnings method implemented
 */
public class PieceWorkerEmployee extends Employee {
	private int numberOfPieces; 	// number of pieces that were made
	private double pricePerPiece; 	// price of each piece that was made
	
	
	public PieceWorkerEmployee(String firstname, String lastName, String socialSecurityNumber, int numberOfPieces, double pricePerPiece, int day, int month,
			int year) {
		super(firstname, lastName, socialSecurityNumber, day, month, year);
		
		// range checking 
		if(numberOfPieces < 1)
			throw new IllegalArgumentException("Number of pieces must be >= 1");
		if(pricePerPiece < 0.0)
			throw new IllegalArgumentException("Price per piece must be >= 0.0");
		
		this.numberOfPieces = numberOfPieces;
		this.pricePerPiece = pricePerPiece;	
	}
	
	public void setNumberOfPieces(int numberOfPieces) {
		if(numberOfPieces < 1)
			throw new IllegalArgumentException("Number of pieces must be >= 1");
		this.numberOfPieces = numberOfPieces;
	}
	
	public void setPricePerPiece(double pricePerPiece) {
		if(pricePerPiece < 0.0)
			throw new IllegalArgumentException("Price per piece must be >= 0.0");
		this.pricePerPiece = pricePerPiece;
	}
	
	public int getNumberOfPieces() {
		return this.numberOfPieces;
	}
	
	public double getPricePerPiece() {
		return this.pricePerPiece;
	}
	
	public double getTotalWage() {
		return getNumberOfPieces() * getPricePerPiece();
	}
	
	@Override
	public double earnings() {
		return getTotalWage();
	}
	
	@Override
	public String toString() {
		return String.format("piece worker employee: %s%n%s: %d; %s: $%,.2f",super.toString(), "pieces made", getNumberOfPieces(),"price per piece",getPricePerPiece());
	}

}
