/**
 * Exception class for password that is valid but 'weak' because it is less than 10 characters long
 * @author Evan Solomon
 */
public class WeakPasswordException extends Exception {

	public WeakPasswordException() {
		super("The password is OK but weak - it contains fewer than 10 characters");
	}

	public WeakPasswordException(String message) {
		super(message);
		
	}

	

}
