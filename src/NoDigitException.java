/**
 * Exception class for password without any digits
 * @author Evan Solomon
 */
public class NoDigitException extends Exception {

	public NoDigitException() {
		super("The password must contain at least one digit");
	}
	
	public NoDigitException(String message) {
		super(message);
	}
}
