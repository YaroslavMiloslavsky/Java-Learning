/**
 * Exception for an illegal phone number (not a digit, less or more than 10 digits....)
 */
public class IlegalPhoneNumberException extends Exception {
	public IlegalPhoneNumberException(String msg) {
		super(msg);
	}
}
