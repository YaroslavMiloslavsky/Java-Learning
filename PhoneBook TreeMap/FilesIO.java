import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Map.Entry;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 * Class to handle writing and saving to a file
 * All the data is stored as binary data
 * Also, a file chooser was implemented so the navigation through the files will be convenient
 */
public class FilesIO {
	
	/**
	 * Saves to a file
	 * Please note, as format was not specified in the work-book, use at your discretion
	 * .txt is recommended
	 * @param contactsInfo The list to be saved
	 * @throws IOException If the file does not exist
	 */
	public void saveToFile(ContactsList contactsInfo) throws IOException{
		JFileChooser fc = new JFileChooser();
		fc.showSaveDialog(null);
		File newFile = fc.getSelectedFile();
		if(newFile == null) return;
		newFile.createNewFile();
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(newFile)); // A stream to the file
		for(Entry<String, PersonInfo> entry : contactsInfo.getMap().entrySet())
			out.writeObject(entry.getValue());
		
		out.close();
	}
	
	/**
	 * Loads a list to a file
	 * @param list The list to be loaded
	 * @throws IOException If the list does not exist
	 */
	public void readFromFile(ContactsList list) throws IOException{
		JFileChooser fc = new JFileChooser();
		fc.showOpenDialog(null);
		File loadFile = fc.getSelectedFile();
		if(loadFile == null) return;
		ObjectInputStream in = new ObjectInputStream(new FileInputStream(loadFile)); // A stream to the file
		list.clearContactList();
		try {
			while(true) {
				list.addContact((PersonInfo)in.readObject()); // Write to file as objects
			}
		}catch(EOFException e) { // Ends when EOF encountered
			JOptionPane.showMessageDialog(null, String.format("List '%s' was loaded", loadFile.getName()));
		}
		 catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		} catch (AlreadyExistsContactException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		in.close();
	}
}
