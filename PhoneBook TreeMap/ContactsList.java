import java.util.Comparator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import javax.swing.JOptionPane;
/**
 * This class is the contact list
 * The list is implemented via a TreeMap so that all the actions on the list will be at most O(log n)
 * Also, the list is set to be sorted alphabetically
 * Please try to give consistent names for the contacts, try to give them the same amount of words, otherwise, the GUI may shift the text to be less convenient
 */
public class ContactsList {
	private Map<String, PersonInfo> contactInfo; // The Map that will become a TreeMap
	
	/**
	 * The constructor sets the Map as a TreeMap,
	 * And gives it a Comparator so all entries will be sorted with ease and good complexity
	 */
	public ContactsList() {
		contactInfo = new TreeMap<String, PersonInfo>(new Comparator<String>() {
		    public int compare(String a, String b) {
		        return a.toLowerCase().compareTo(b.toLowerCase());
		    }
		});
	}
	
	/**
	 * Returns the TreeMap
	 * This was made to help the encapsulation
	 * @return The contact list
	 */
	public Map<String,PersonInfo> getMap(){
		return this.contactInfo;
	}
	
	/**
	 * Clears the contact list
	 */
	public void clearContactList() {
		this.contactInfo.clear();
	}
	
	/**
	 * Searches for a contact in a list by name
	 * @param name The name we search the contact by
	 * @return The contact that has the name we searched
	 * @throws DoesNotExistContactException If the list does not contain the searched name
	 */
	public PersonInfo searchByName(String name) throws DoesNotExistContactException{
		if(!contactInfo.containsKey(name))
			throw new DoesNotExistContactException(name + " does not exists");
		
		return getMap().get(name);
	}
	
	/**
	 * Searches for a contact in a list by phone number
	 * @param number The number we search the contact by
	 * @return The contact that has the number we searched if one exists
	 */
	public PersonInfo searchByNumber(String number) {		
		for(Entry<String, PersonInfo> entry : this.getMap().entrySet()) {
			if(entry.getValue().getPhoneNumber().equals(number))
			{
				return getMap().get(entry.getValue().getContactName());
			}
		}
		JOptionPane.showMessageDialog(null, "this number belongs to no one on the list");
		return null;
	}
	
	/**
	 * Adds a new contact to the list
	 * @param info The contact we want to add
	 * @throws AlreadyExistsContactException If the key that is the name, already exists
	 */
	public void addContact(PersonInfo contact) throws AlreadyExistsContactException{
		if(contactInfo.containsKey(contact.getContactName()))
			throw new AlreadyExistsContactException(contact.getContactName() + " already exists");
		if(numberAlredyExists(contact.getPhoneNumber()))
			throw new AlreadyExistsContactException(contact.getPhoneNumber() + " already exists");
		contactInfo.put(contact.getContactName(), contact);
	}
	
	/**
	 * This is inner class function to find if the number already exists
	 * @param number The number (value) we search to check
	 * @return True if the number already in the list (as a value)
	 */
	private boolean numberAlredyExists(String number) {
		Boolean exists = false;
		
		for(Entry<String, PersonInfo> entry : this.getMap().entrySet())
			if(entry.getValue().getPhoneNumber().equals(number))
				exists = true;
		return exists;
	}
	/**
	 * Removes the contact 
	 * @param contact The contact we wish to remove
	 * @throws DoesNotExistContactException If the contact does not exist
	 */
	public void removeContact(PersonInfo contact) throws DoesNotExistContactException{
		if(!contactInfo.containsKey(contact.getContactName()))
			throw new DoesNotExistContactException(contact.getContactName() + " does not exists");
		
		contactInfo.remove(contact.getContactName());
	}
	
	/**
	 * Updates the phone number of the given person
	 * @param contact The contact we wish to update 
	 * @param number The number we wish to update to
	 * @throws DoesNotExistContactException If the contact does not exist
	 * @throws IlegalPhoneNumberException If the number we are trying to update is illegal as a phone number
	 * @throws IlegalNameException If the name is illegal (empty for example)
	 * @throws AlreadyExistsContactException If the number is already taken by another contact
	 */
	public void updateContactPhoneNumber(PersonInfo contact , String number) throws DoesNotExistContactException, IlegalPhoneNumberException, IlegalNameException, AlreadyExistsContactException{
		if(!contactInfo.containsKey(contact.getContactName()))
			throw new DoesNotExistContactException(contact.getContactName() + " does not exists");
		if(numberAlredyExists(number) && !contact.getPhoneNumber().equals(number))
			throw new AlreadyExistsContactException("This number already exists");
		
		if(number.length() != 10)
			throw new IlegalPhoneNumberException("Phone number must contain 10 digits");
			
			this.contactInfo.put(contact.getContactName(), new PersonInfo(contact.getContactName(),number));
	}
	
	/**
	 * Prints the contact list
	 */
	public String toString() {
		String str = new String();
		for(Entry<String, PersonInfo> entry : contactInfo.entrySet())
			str+= String.format("%s",entry.getValue());
//		return str.substring(0,str.length()-1);
		return str;
	}
}
