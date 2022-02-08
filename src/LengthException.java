/**
 * Exception class for password less than 6 characters
 * @author Evan Solomon
 */
public class LengthException extends Exception {

	public LengthException() {
		super("The password must be at least 6 characters long");
	}

	public LengthException(String message) {
		super(message);
		
	}

	
}
