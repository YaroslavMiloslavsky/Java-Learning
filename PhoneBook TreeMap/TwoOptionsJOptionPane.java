import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * This class was created for two option JOptionPane
 */
public class TwoOptionsJOptionPane {
	private JPanel multipleOptionPane;
	private JTextField fieldName;
	private JTextField fieldNumber;
	private String name,number;
	
	/**
	 * Constructor
	 * Sets the layout and the positions of all components
	 */
	public TwoOptionsJOptionPane() {
		fieldName = new JTextField(20);
		fieldNumber = new JTextField(10);
		multipleOptionPane = new JPanel();
		multipleOptionPane.setLayout(new GridLayout(2,2));
		multipleOptionPane.add(new JLabel("Name:"));
		multipleOptionPane.add(fieldName);
		multipleOptionPane.add(new JLabel("Number:"));
		multipleOptionPane.add(fieldNumber);
	}
	
	/**
	 * Prompt for the 'Add' option
	 * The prompt has name and number field that contain the information of the contact
	 */
	public void addPrompt(){
		int result = JOptionPane.showConfirmDialog(null, multipleOptionPane, 
	            "Add Contact", JOptionPane.OK_CANCEL_OPTION);
		if (result == JOptionPane.OK_OPTION)  {
	        name = fieldName.getText();
	        number = fieldNumber.getText();
	        fieldName.setText(null);
			fieldNumber.setText(null);
	      }
		if(result == JOptionPane.CANCEL_OPTION) {
			
		}
	}
	
	/**
	 * Prompt for the 'Update' option
	 * Works same as addPrompt but instead of adding contact, the function updates existing contact
	 */
	public void updatePrompt() {
		int result = JOptionPane.showConfirmDialog(null, multipleOptionPane, 
	            "Update Contact", JOptionPane.OK_OPTION);
		if (result == JOptionPane.OK_OPTION)  {
	        name = fieldName.getText();
	        number = fieldNumber.getText();
	        fieldName.setText(null);
			fieldNumber.setText(null);
	      }
		if(result == JOptionPane.CANCEL_OPTION) {
			
		}
	}

	/**
	 * @return The name field
	 */
	public String getNameField() {
		return this.name;
	}
	
	/**
	 * @return The number field
	 */
	public String getNumberField() {
		return this.number;
	}
	
	/**
	 * @param str The name to set
	 */
	public void setNameField(String str) {
		this.name = str;
	}
	
	/**
	 * @param str The phone number to set
	 */
	public void setNumberField(String str) {
		this.number = str;
	}
	
	
}
