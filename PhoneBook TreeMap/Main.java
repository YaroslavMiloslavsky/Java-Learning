import java.awt.Color;

import javax.swing.JFrame;

/**
 * This Ex. represents a simple phone book that can add,remove,update and search contacts in a cotact list (so called a phone book)
 * Also, there is the ability to load and save a contact list to the phone book
 * The phone book has a simple but a nice GUI
 *
 */
public class Main {

	public static void main(String[] args) throws IlegalPhoneNumberException, AlreadyExistsContactException{
		/*
		 * As simple as it looks
		 * We set a JFrame and new JPanel that represents the contact list
		 */
		PhoneBookGUI gui = new PhoneBookGUI();
		JFrame frame = new JFrame("Phone Book");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(gui);
        frame.setSize(500, 500);
        frame.setBackground(Color.black);
        frame.setVisible(true);	
	}
}
