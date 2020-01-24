import java.security.SecureRandom;
import java.util.ArrayList;

/**
 * This is the class of the Cards Deck object which contains cards
 */
public class CardsDeck {
	private ArrayList<Card> deck; 											// ArrayList of Card objects
	private static final int NUMBER_OF_CARDS = 52;							// Constant number of cards
	private static final SecureRandom randomNumbers = new SecureRandom(); 	// Random number generator
	
	/**
	 * The class builder, creates a whole deck with all the 52 cards (Joker is absent by the rules of the game)
	 */
	public CardsDeck() {
		//	these two arrays were taken from the book and they represent the available cards
		String[] faces = {"Deuce","Three","Four","Five","Six","Seven","Eight","Nine","Ten","Jack","Queen","King","Ace"};
		String[] suits = {"Hearts","Diamonds","Clubs","Spades"};
		
		deck = new ArrayList<Card>();	// the deck that contains the cards
		
		// sets the cards in the deck
		for(int index = 0; index < NUMBER_OF_CARDS ; index++) {
			deck.add(new Card(faces[index%13], suits[index/13]));
		}
		
		// sets the value of each card
		int strength=0;
		for(Card card: deck)
			card.setStrength((strength++) % 13);
	}
	
	/**
	 * This function returns the current deck
	 * @return The current deck
	 */
	public ArrayList<Card> getDeck(){
		return deck;
	}
	
	/**
	 * This function shuffles the deck in randomised order
	 * In fact this function switches random cards in the deck (all of the cards are being switched to a random index)
	 */
	public void shuffle() {
		for(Card card: this.deck) {
			
			Card randomCard = deck.get(randomNumbers.nextInt(NUMBER_OF_CARDS));
			Card tempCard = card;
			
			int currentIndex = deck.indexOf(card);
			int randomIndex  = deck.indexOf(randomCard);
			
			deck.set(currentIndex, randomCard);
			deck.set(randomIndex, tempCard);			
		}
	}
	
	/**
	 * This function deals the top card and removes it from the deck
	 * @return	The top card
	 */
	public Card dealCard() {
		if(deck != null) {
			return deck.remove(0);
		}
		return null;	
	}

	/**
	 * clears the deck of all cards
	 */
	public void emptyDeck() {
		deck.clear();
	}
	
	/**
	 * adds a single card to the bottom of the deck
	 * @param card The card that being added
	 */
	public void addCard(Card card) {
		deck.add(card);
	}
	
	/**
	 * adds a single card to a given index
	 * @param index The index that the card is being placed in
	 * @param card The card that being added 
	 */
	public void addCard(int index,Card card) {
		deck.add(index,card);
	}
	
	/**
	 * adds multiple cards to the bottom of the deck
	 * @param cards	The cards that are being added
	 */
	public void addCard(ArrayList<Card> cards) {
		deck.addAll(cards);
	}
	
	/**
	 * removes a the specific card from the deck
	 * @param card The card that is being removed
	 */
	public void removeCard(Card card) {
		if(deck!= null)
			deck.remove(card);
	}
	
	/**
	 * removes a card in a given index
	 * @param index The index of the card that is being removed
	 */
	public void removeCard(int index) {
		if(deck!=null)
			deck.remove(index);
	}	
	
	/**
	 * removes listed cards from array
	 * @param cards The cards that are being removed
	 */
	public void removeCard(ArrayList<Card> cards) {
			cards.removeAll(cards);
	}
	
	/**
	 * divides the deck between 2 players
	 * the deck is being emptied in the process
	 * @param player1 The first player 
	 * @param player2 The second player
	 */
 	public void deckDivide(CardsDeck player1, CardsDeck player2) {
		ArrayList<Card> valuesToRemove= new ArrayList<Card>();
		int counter = 0;
		for(Card card: getDeck()) {
			
			valuesToRemove.add(card);
			if(counter % 2 == 0)
				player1.addCard(card);
			else
				player2.addCard(card);
			counter++;
		}
		this.deck.removeAll(valuesToRemove);
	}

 	/**
 	 * this function gives the size of the deck
 	 * @return The current size of the deck
 	 */
 	public int getSize() {
 		return deck.size();
 	}
 	
 	/**
 	 * this function checks if there is a next card
 	 * @return True if there is a next card, otherwise False
 	 */
 	public boolean hasNext() {
 		if(deck.size()>0) return true;
 		else return false;
 	}
	
	/**
	 * the toString function of this class
	 * returns the list of the cards in the deck
	 */
	public String toString() {
		int count=0;
		String strDeck = new String();
		for(Card card: this.deck) {
				strDeck += card.toString() + "\n";
				count++;
			}
		return strDeck + count;
	}
	
}
