import java.io.Serializable;
/**
 * Class to represent a contact 
 */
public class PersonInfo implements Serializable{
	private String contactName, phoneNumber; 
	
	/**
	 * Constructor 
	 * @param contactName The name of the contact
	 * @param phoneNumber The phone number of the contact
	 * @throws IlegalPhoneNumberException If the phone number is illegal
	 * @throws IlegalNameException If the name is Illegal 
	 * @throws NumberFormatException If the phone number consists of chars other then digit
	 */
	public PersonInfo(String contactName, String phoneNumber) throws IlegalPhoneNumberException, IlegalNameException, NumberFormatException{
		this.contactName = contactName;
		if(!isPhoneNumber(phoneNumber))
			throw new NumberFormatException("Phone number must consists only of digits");
		if(phoneNumber.length()!=10)
			throw new IlegalPhoneNumberException("Phone number must contain 10 digits");
		if(contactName.length()<1)
			throw new IlegalNameException("Name cannot be empty");
		this.phoneNumber = phoneNumber;
	}
	
	/**
	 * A test if the String for the phone number consists of digits
	 * @param number The number we examine
	 * @return True if the number consists only of digits
	 */
	private boolean isPhoneNumber(String number) {
		boolean flag = true;
		try {
			/* This is done to determine that the number is all digit composed
			 * Integers are too large to parse in one piece 
			 */
			Integer.parseInt(number.substring(0,number.length()/2));
			Integer.parseInt(number.substring(number.length()/2,number.length()));
		}catch(NumberFormatException e) {
			flag = false;
		}
		return flag;
	}
	
	/**
	 * Sets the contact name
	 * @param name The name of contact to set
	 */
	public void setContactName(String name)
	{
		this.contactName = name;
	}
	/**
	 * Sets the contact number
	 * @param number The number we wish to set
	 * @throws IlegalPhoneNumberException If the number contains not only digits
	 */
	public void setPhoneNumber(String number) throws IlegalPhoneNumberException {
		if(phoneNumber.length()!=10)
			throw new IlegalPhoneNumberException("Phone number must contain 10 digits");
		this.phoneNumber = number;
	}
	
	/**
	 * @return The contact name
	 */
	public String getContactName() {
		return this.contactName;
	}
	
	/**
	 * @return The phone number
	 */
	public String getPhoneNumber() {
		return this.phoneNumber;
	}
	
	/**
	 * Represents the contact as a string
	 */
	public String toString() {
		return String.format("%15s \t %2s-%2s-%2s%n", getContactName(),
				getPhoneNumber().substring(0,3),getPhoneNumber().substring(3,6),getPhoneNumber().substring(6,10));
	}

}

