import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.regex.Matcher;


public class PasswordCheckerUtility {
	
	//default constructor
	public PasswordCheckerUtility() {
		
	}
	
	//Compare equality of two passwords	
	public static void comparePasswords(String password, String passwordConfirm) throws UnmatchedException {
		if(!password.contentEquals(passwordConfirm)) throw new UnmatchedException();
	}
	
	//Public methods
	public static boolean isValidPassword(String password) throws Exception {
		boolean passesAllReqs = 
					isValidLength(password) &&
					hasUpperAlpha(password) &&
					hasLowerAlpha(password)&&
					hasDigit(password) &&
					hasSpecialChar(password) &&
					noSameCharInSequence(password);		
		 return passesAllReqs;
	}
	
	public static boolean isWeakPassword(String password) throws WeakPasswordException {
		boolean isWeak = hasBetweenSixAndNineChars(password);
		if(isWeak)
			throw new WeakPasswordException();
		return isWeak;
	}
	
	public static ArrayList<String> getInvalidPasswords(ArrayList<String> passwords) {
		ArrayList<String> invalidPasswords = new ArrayList<String>();
		for(String password : passwords) {
			try{
				isValidPassword(password);
			}catch(Exception e) {
				invalidPasswords.add(password + " -> " + e.getMessage());
			}
		}
		return invalidPasswords;
	}
	
	
	public static boolean comparePasswordsWithReturn(String password, String passwordConfirm) {	
		return(password.contentEquals(passwordConfirm));	
	}
	
	
	
	//methods -- password requirements
	static boolean hasBetweenSixAndNineChars(String password) {
		boolean isGTESixAndLTENineChars = password.length() >= 6 && password.length() <=9;
		return isGTESixAndLTENineChars;
	}
	
	static boolean hasDigit(String password) throws NoDigitException {
		String rgx = ".*[0-9]+.*";
		Pattern pattern = Pattern.compile(rgx);
		Matcher matcher = pattern.matcher(password);
		boolean result = matcher.matches();
		if(!result) {
			throw new NoDigitException();
		}
		return result;
	}

	static boolean hasLowerAlpha(String password) throws NoLowerAlphaException {
		boolean hasLowerCase = !password.equals(password.toUpperCase());
		if(!hasLowerCase || !hasAlphaChar(password))
			throw new NoLowerAlphaException();
		return hasLowerCase;
	}
	
	static boolean hasSpecialChar(String password) throws NoSpecialCharacterException {
		String rgx = "[a-zA-Z0-9]*";
		Pattern pattern = Pattern.compile(rgx);
		Matcher matcher = pattern.matcher(password);
		boolean result = !matcher.matches();
		if (!result)
			throw new NoSpecialCharacterException();
		return (result);
	}
	
	static boolean hasUpperAlpha(String password) throws NoUpperAlphaException {
		boolean hasUpperCase = !password.equals(password.toLowerCase());
		if(!hasUpperCase || !hasAlphaChar(password))
			throw new NoUpperAlphaException();
		return hasUpperCase;
	}
	
	static boolean isValidLength(String password) throws LengthException {
		boolean hasValidLength = password.length() >= 6;
		if(!hasValidLength)
			throw new LengthException();
		return hasValidLength;
	}

	static boolean noSameCharInSequence(String password) throws InvalidSequenceException {
		String rgx = ".*(.)\\1\\1.*";
		Pattern pattern = Pattern.compile(rgx);
		Matcher match = pattern.matcher(password);
        
		boolean result = match.matches();
		
        if (result)
        	throw new InvalidSequenceException();
		return !result;
	}
	
	static boolean hasAlphaChar(String password) {
		String rgx = ".*[a-zA-Z].*";
        Pattern pattern = Pattern.compile(rgx);
        Matcher matcher = pattern.matcher(password);
        boolean result = matcher.matches();
		return result;
	}


}
