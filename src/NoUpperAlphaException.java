/**
 * Exception class for password without no uppercase alphabetic character
 * @author Evan Solomon
 */
public class NoUpperAlphaException extends Exception {

	public NoUpperAlphaException() {
		super("The password must contain at least one uppercase alphabetic character");
	}

	public NoUpperAlphaException(String message) {
		super(message);

	}

	
}
