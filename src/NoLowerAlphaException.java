/**
 * Exception class for password without lowercase alphabetic characters
 * @author Evan Solomon
 */
public class NoLowerAlphaException extends Exception {

	public NoLowerAlphaException() {
		super("The password must contain at least one lowercase alphabetic character");
	}

	public NoLowerAlphaException(String message) {
		super(message);
		
	}



}
