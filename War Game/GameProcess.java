import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * This is the class where all the details are decided, all the game rules, all the turn conditions and the winner.
 * I took the rule set out of Wikipedia and assumed that the card strength rank is :A K Q J 10 9 8 7 6 5 4 3 2 (from strongest to weakest)
 */
public class GameProcess extends JPanel{
	private CardsDeck mainDeck ,player1, player2;	// These objects are the representations of the players and the main deck
	private Card player1Card, player2Card;			// These objects are the player's current card
	private ArrayList<Card> currentDeck;			// These object is the deck where the players put their cards during the match - the winner takes it
	private int turns;								// The turn counter
	private boolean player1Won, player2Won;			// The conditions of the winner (even though they are no very used, they are more for reader's convenience
	private JButton next;							// The button for the JPanel 
	private JLabel txtTurn, txtPlayer1, txtPlayer2, txtWinner, txtCard1Count, txtCard2Count;		// These are the labels for the JPanel the game is happening on
	
	/*
	 * The builder for this class, all the players are 'getting ready' for the game
	 * The cards deck is being shuffled and the cards in it are being given to the players (Like real life - 1 card goes to player1 the next to player2 etc...)
	 * Also, the JPanel is set here, the layout is being organised for the game.
	 */
	public GameProcess() {
		// new objects are created
		mainDeck = new CardsDeck();
		player1 = new CardsDeck();
		player2 = new CardsDeck();
		
		// the player cards are nullified
		player1Card = null;
		player2Card = null;
		// the winning condition is nullified
		player1Won = false;
		player2Won = false;
		// the turn counter is set to 0
		turns = 0;
		
		currentDeck = new ArrayList<Card>();
		// players represented by empty decks
		player1.emptyDeck();
		player2.emptyDeck();
		
		// shuffle and divide the main deck - main deck is being emptied in the process
		mainDeck.shuffle();
		mainDeck.deckDivide(player1,player2);
		
		// ******** GUI part *************
		this.setLayout(new GridLayout(8,8));
		next = new JButton("Next");
		next.addActionListener(new PressPerformed());		// this defines the "next" button behaviour
		txtTurn = new JLabel("Press next to proceed");
		txtPlayer1 = new JLabel();
		txtPlayer2 = new JLabel();
		txtWinner = new JLabel();
		
		// these are for the ease of debugging, if you wish to see the number of cards each player has, please uncomment the part at the end of the class
		txtCard1Count = new JLabel();
		txtCard2Count = new JLabel();

		// buttons are added in a convenient order
		add(txtTurn);
		add(txtPlayer1);
		add(txtCard1Count);
		add(txtPlayer2);
		add(txtCard2Count);
		add(txtWinner);
		add(next);
		
	}

	/**
	 * This class is in charge of the buttons behaviour
	 * There is only one button in our program which is "Next"
	 * Every time it is pressed, the game begins the next turn
	 * In the beginning the panel will be empty, once "Next" is pressed, the game starts
	 **/
	private class PressPerformed implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == next) {		// in our case this is the only button
				if(player1.getSize() > 0 && player2.getSize()>0) {		// a check to see that we are not out of the deck's boundaries 
					
					++turns; 		// counter of the current turn
					txtTurn.setText("Turn "+ turns);
					
					// players put cards in main deck
					player1Card = player1.dealCard();
					player2Card = player2.dealCard();
					currentDeck.add(player1Card);
					currentDeck.add(player2Card);
					Collections.shuffle(currentDeck);	// the deck is being shuffled to avoid infinite running games
					
					// We see what card each one has
					txtPlayer1.setText("Player 1 has " + player1Card);
					txtPlayer2.setText("Player 2 has " + player2Card);
				
					// we begin comparing
					if(player1Card.getSternght() > player2Card.getSternght()) { 		// if player 1 has stronger card he wins
						player1.addCard(currentDeck);
						player1Won = true;
						player2Won = false;
						txtWinner.setText("Player 1 won the round and took the " + currentDeck.size() +" cards !");
						currentDeck.clear();
					}
					else if(player2Card.getSternght() > player1Card.getSternght()) {	// if player 2 has stronger card
						player2.addCard(currentDeck);
						player1Won = false;
						player2Won = true;
						txtWinner.setText("Player 2 won the round and took the " + currentDeck.size() + " cards !");
						currentDeck.clear();
					}
					else // if the cards are equal
						while(player2Card.getSternght() == player1Card.getSternght()) {		// WAR mode starts
							JOptionPane.showMessageDialog(null, "This is war mode!\nEvery player pull out 3 cards!");		// we get dialog box about "war mode"
							for(int index = 0 ; index < 3; index++) { 
								if(player1.getSize()>0 && player2.getSize()>0) {
									//player1 puts down 3 cards
									currentDeck.add(player1Card = player1.dealCard());
									//player2 puts down 3 cards
									currentDeck.add(player2Card = player2.dealCard());
									// a dialog box of the players' cards
									JOptionPane.showMessageDialog(null,"Card "+ (index+1) +"\nPlayer 1 has " + player1Card + "\nPlayer 2 has " + player2Card );
								}
								else {		// if there are insufficient cards for a war game we will show every card we have
									while(player1.getSize() > 0 && player2.getSize() > 0) {
										//player1 puts down card
										currentDeck.add(player1Card = player1.dealCard());
										//player2 puts down card
										currentDeck.add(player2Card = player2.dealCard());
									}
									break;
								}
							}// end of for loop	
							Collections.shuffle(currentDeck);		// the deck is being shuffled to avoid infinite running games
							// comparing the 3rd card - same as the original comparison
							if(player1Card.getSternght() > player2Card.getSternght()) {
								player1.addCard(currentDeck);
								player1Won = true;
								player2Won = false;
								JOptionPane.showMessageDialog(null, "Player 1 won the round and took the " + currentDeck.size() +" cards !");
								txtWinner.setText("Player 1 won the round and took the " + currentDeck.size() +" cards !");
								// update the current cards of the players
								txtPlayer1.setText("Player 1 has " + player1Card);
								txtPlayer2.setText("Player 2 has " + player2Card);
								// clearing the deck that was given to the player
								currentDeck.clear();
							}
							else if(player2Card.getSternght() > player1Card.getSternght()) {
								player2.addCard(currentDeck);
								player1Won = false;
								player2Won = true;
								JOptionPane.showMessageDialog(null, "Player 2 won the round and took the " + currentDeck.size() +" cards !");
								txtWinner.setText("Player 2 won the round and took the " + currentDeck.size() + " cards !");
								// update the current cards of the players
								txtPlayer1.setText("Player 1 has " + player1Card);
								txtPlayer2.setText("Player 2 has " + player2Card);
								// clearing the deck that was given to the player
								currentDeck.clear();
							}
						}// end of while loop
					//}// end of WAR mode
					
							// if you wish to see the number of cards that each one has - uncomment
//							txtCard1Count.setText("Player 1 has "+ player1.getSize()+ " Cards ");
//							txtCard2Count.setText("Player 2 has "+ player2.getSize()+ " Cards ");
					
							currentDeck.clear();	// clear the current deck for the next turn to begin	
				}// end of game loop
				
				// the winner is announced
				if(player1.getSize() == 0 || player2.getSize() == 0) { 
					JOptionPane.showMessageDialog(null,(player1Won == true && player2Won == false)? "Player 1 has won the game!":"Player 2 has won the game!" );
				}
			}
		}
	}
}
