/**
 * Exception for illegal name, for example when the name field is empty
 */
public class IlegalNameException extends Exception {
	public IlegalNameException(String msg) {
		super(msg);
	}
}
