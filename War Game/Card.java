/**
 * This class represents the card - each card has a suit and a face
 */
public class Card {
	private final String face; // Face of cards (Ace, Deuce ....)
	private final String suit; // Suit of cards (Hearts, Diamonds ....)
	private int strength;
	
	/**
	 * The class constructor
	 * @param cardFace The face of the card to be created
	 * @param cardSuit The suit of the card to be created
	 */
	public Card(String cardFace, String cardSuit) {
		this.face = cardFace;
		this.suit = cardSuit;
	}
	
	 //Copy constructor
	public Card(Card card) {
		this.face = card.getFace();
		this.suit = card.getSuit();
		this.strength = card.getSternght();
	}
	
	/**
	 * Returns the suit of the card
	 * @return The suit
	 */
	private String getSuit() {
		return suit;
	}
	
	/**
	 * Returns the face of the card
	 * @return The face
	 */
	private String getFace() {
		return face;
	}
	
	/**
	 * Sets the strength of the card
	 * @param strength The value we give to the card
	 */
	public void setStrength(int strength) {
		this.strength = strength;
	}
	
	/**
	 * Gets the strength of the card
	 * @return The strength of the card
	 */
	public int getSternght() {
		return strength;
	}
	
	/**
	 * The toString overload
	 */
	public String toString() {
		return getFace() + " of " + getSuit() + " ";
	}

	
}
