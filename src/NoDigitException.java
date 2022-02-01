public class NoDigitException extends Exception {

	//Write documentation here!!!!!
	
	public NoDigitException() {
		super("The password must contain at least one digit");
	}
	
	public NoDigitException(String message) {
		super(message);
	}
}
