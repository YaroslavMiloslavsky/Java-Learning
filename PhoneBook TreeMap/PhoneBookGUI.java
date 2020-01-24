import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * This is the GUI class for the phone book
 * Here all the functionality implemented
 */
public class PhoneBookGUI extends JPanel {
	private JButton cmdAdd, cmdRemove,cmdSearch, cmdUpdate, cmdSave, cmdLoad;
	private JTextArea  txtMain; 
	private ContactsList phoneBookList;
	private FilesIO filesUtils;
	private TwoOptionsJOptionPane doubleOptionPane;
	
	/**
	 * The constructor for the GUI
	 * Consists of 3 panels and adds the functionality for each button 
	 */
	public PhoneBookGUI() {
		phoneBookList = new ContactsList();
		filesUtils = new FilesIO();
		doubleOptionPane = new TwoOptionsJOptionPane();
		cmdAdd = new JButton("Add");
		cmdRemove = new JButton("Remove");
		cmdUpdate = new JButton("Update");
		cmdSearch = new JButton("Search");
		cmdSave = new  JButton("Save");
		cmdLoad = new JButton("Load");
		txtMain = new JTextArea( 20,35);
		JScrollPane scroll = new JScrollPane(txtMain);
		
		// The control listener
		ControlListener cmd = new ControlListener();
		cmdAdd.addActionListener(cmd);
		cmdRemove.addActionListener(cmd);
		cmdUpdate.addActionListener(cmd);
		cmdSearch.addActionListener(cmd);
		cmdSave.addActionListener(cmd);
		cmdLoad.addActionListener(cmd);
		
		// Main area for the text
		JPanel textArea = new JPanel();
		txtMain.setEditable(false);
		txtMain.setFont(txtMain.getFont().deriveFont(Font.BOLD, 14f));
		textArea.add(scroll);
		
		// Control scheme - north
		JPanel filesControls = new JPanel();
		filesControls.add(cmdSave);
		filesControls.add(cmdLoad);
		
		// Control scheme - south
		JPanel controls = new JPanel();
		controls.setLayout(new GridLayout());
		controls.add(cmdAdd);
		controls.add(cmdRemove);
		controls.add(cmdUpdate);
		controls.add(cmdSearch);
		
		// Sets all the panels together
		this.setLayout(new BorderLayout());
		this.add(controls, BorderLayout.SOUTH);
		this.add(filesControls, BorderLayout.NORTH);
		this.add(textArea, BorderLayout.CENTER);
	}
	
	/**
	 * A private class that is responsible for all the JButton functionality
	 */
	private class ControlListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == cmdAdd) {
				// A pop up with name and phone field that adds to phoneBookList
				doubleOptionPane.addPrompt();
				String name = doubleOptionPane.getNameField();
				String number = doubleOptionPane.getNumberField();
				if(name != null && number!= null) {
					try {
						PersonInfo newContact = new PersonInfo(name, number);
						phoneBookList.addContact(newContact);
						JOptionPane.showMessageDialog(null, name + " was added");
					} catch (IlegalPhoneNumberException ex) {
						JOptionPane.showMessageDialog(null, ex.getMessage());
					} catch (AlreadyExistsContactException ex) {
						JOptionPane.showMessageDialog(null, ex.getMessage());
					} catch (IlegalNameException ex) {
						JOptionPane.showMessageDialog(null, ex.getMessage());
					} catch (NumberFormatException ex) {
						JOptionPane.showMessageDialog(null, ex.getMessage());
					}
				}
			}
			if(e.getSource() == cmdRemove) {
				// Prompt asking how to search? by name or phone and removes the contact
				Object[] possibleValues = { "Name" , "Phone number"};
				Object selectedValue = JOptionPane.showInputDialog(null,
				"Remove by", "Remove  Option",
				JOptionPane.INFORMATION_MESSAGE, null,
				possibleValues, possibleValues[0]);
				
				if(selectedValue== possibleValues[0]) {
					// prompt for name and remove
					String name = JOptionPane.showInputDialog(null,"Please enter a name");
					if(name == null) return;
					try {
						PersonInfo toRemove = phoneBookList.searchByName(name);
						phoneBookList.removeContact(toRemove);
						JOptionPane.showMessageDialog(null, toRemove.getContactName() + " was removed");
					} catch (DoesNotExistContactException ex) {
						JOptionPane.showMessageDialog(null, ex.getMessage());
					} catch (NumberFormatException ex) {
						JOptionPane.showMessageDialog(null, ex.getMessage());
					}
				}
				// prompt for number and remove
				if(selectedValue== possibleValues[1]) {
					String number = JOptionPane.showInputDialog(null,"Please enter a phone number");
					if(number == null) return;
					try {
						PersonInfo toRemove = phoneBookList.searchByNumber(number);
						if(toRemove!=null) {
							phoneBookList.removeContact(toRemove);
							JOptionPane.showMessageDialog(null, toRemove.getContactName() + " was removed");
						}
						
					} catch (DoesNotExistContactException ex) {
						JOptionPane.showMessageDialog(null, ex.getMessage());
					} 
				}if(selectedValue == null)
				       return;
			}
			if(e.getSource() == cmdUpdate) {
				// prompt double selection prompt and update by name
				doubleOptionPane.updatePrompt();
				String name = doubleOptionPane.getNameField();
				String number = doubleOptionPane.getNumberField();
				if(name != null && number!= null) {
						PersonInfo toUpdate;
						try {
							toUpdate = phoneBookList.searchByName(name);
							phoneBookList.updateContactPhoneNumber(toUpdate, number);
							JOptionPane.showMessageDialog(null, toUpdate.getContactName() + " was updated");
						} catch (DoesNotExistContactException ex) {
							JOptionPane.showMessageDialog(null, ex.getMessage());
						} catch (IlegalPhoneNumberException ex) {
							JOptionPane.showMessageDialog(null, ex.getMessage());
						} catch (IlegalNameException ex) {
							JOptionPane.showMessageDialog(null, ex.getMessage());
						} catch (NumberFormatException ex) {
							JOptionPane.showMessageDialog(null, ex.getMessage());
						} catch (AlreadyExistsContactException ex) {
							JOptionPane.showMessageDialog(null, ex.getMessage());
						}
				}
			}
			if(e.getSource() == cmdSearch) {
				//asks for name and give phone number
				String name = JOptionPane.showInputDialog(null,"Please enter a name");
				if(name == null) return;
				try {
					PersonInfo toSearch = phoneBookList.searchByName(name);
					JOptionPane.showMessageDialog(null,String.format("%s has the number %s", toSearch.getContactName(),toSearch.getPhoneNumber())); 
				} catch (DoesNotExistContactException ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
			}
			if(e.getSource() == cmdSave) {
				try {
					filesUtils.saveToFile(phoneBookList);
				} catch (IOException ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
				
			}
			if(e.getSource() == cmdLoad) {
				try {
					filesUtils.readFromFile(phoneBookList);
				}catch (IOException ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}catch(NullPointerException ex){
					JOptionPane.showMessageDialog(null, "Cancel");
				}
			}
			txtMain.setText(phoneBookList.toString()); // Sends all the needed information to the main JTextArea
			doubleOptionPane.setNameField(null);
			doubleOptionPane.setNumberField(null);
		}
	}
}
