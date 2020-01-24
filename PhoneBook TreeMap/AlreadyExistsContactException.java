/**
 * Exception for already existing contact
 */
public class AlreadyExistsContactException extends Exception {
	public AlreadyExistsContactException(String msg) {
		super(msg);
	}
}
