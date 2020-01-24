/**
 * Exception for non existing contact
 * For example if we are trying to remove a non existing contact
 *
 */
public class DoesNotExistContactException extends Exception {
	public DoesNotExistContactException(String msg) {
		super(msg);
	}
}
