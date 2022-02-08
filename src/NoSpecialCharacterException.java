/**
 * Exception class for password without special characters
 * @author Evan Solomon
 */
public class NoSpecialCharacterException extends Exception {
	
	public NoSpecialCharacterException() {
		super("The password must contain at least one special character");
	}

	public NoSpecialCharacterException(String message) {
		super(message);
	}
	
}
