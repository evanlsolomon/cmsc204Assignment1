import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

/** Utility class for checking validity of passwords.
 * @author Evan Solomon
 */
public class PasswordCheckerUtility {

	/** Compare equality of two passwords
	 * @param password password string to be checked for
	 * @param passwordConfirm passwordConfirm string to be checked against password for
	 * @throws UnmatchedException thrown if not same (case sensitive)
	 */
	static void comparePasswords(String password, String passwordConfirm) throws UnmatchedException {
		if(!password.contentEquals(passwordConfirm)) throw new UnmatchedException();
	}

	/** Return true if valid password (follows all the following rules), returns false if an invalid password 1.
	 * At least 6 characters long - 2.
	 * At least 1 numeric character- 3.
	 * At least 1 uppercase alphabetic character - 4.
	 * At least 1 lowercase alphabetic character - 5.
	 * At least 1 special character - 6.
	 * No more than 2 of the same character in a sequence - Hello@123 – OK AAAbb@123 – not OK Aaabb@123 – OK
	 * @param password string to be checked for validity
	 * @return true if valid password (follows all rules from above), false if an invalid password
	 * @throws LengthException thrown if length is less than 6 characters
	 * @throws NoUpperAlphaException thrown if no uppercase alphabetic
	 * @throws NoLowerAlphaException thrown if no lowercase alphabetic
	 * @throws NoDigitException thrown if no digit
	 * @throws NoSpecialCharacterException thrown if does not meet SpecialCharacter requirement
	 * @throws InvalidSequenceException thrown if more than 2 of same character are in sequence
	 */
	static boolean isValidPassword(String password) throws
			LengthException,
			NoUpperAlphaException,
			NoLowerAlphaException,
			NoDigitException,
			NoSpecialCharacterException,
			InvalidSequenceException{
		boolean passesAllReqs = 
					isValidLength(password) &&
					hasUpperAlpha(password) &&
					hasLowerAlpha(password)&&
					hasDigit(password) &&
					hasSpecialChar(password) &&
					noSameCharInSequence(password);		
		 return passesAllReqs;
	}

	/** Checks if password is VALID and the length is NOT between 6-9 characters
	 * @param password string to be checked if weak password
	 * @return false if the password is valid and the length of password is NOT between 6 and 9 (inclusive).
	 * @throws WeakPasswordException if length of password is between 6 and 9 (inclusive), ALTHOUGH the password may be VALID.
	 */
	static boolean isWeakPassword(String password) throws WeakPasswordException {
		boolean isWeak = hasBetweenSixAndNineChars(password);
		if(isWeak)
			throw new WeakPasswordException();
		return isWeak;
	}

	/** This method will accept an ArrayList of passwords as the parameter and return an ArrayList with the status of any invalid passwords (weak passwords are not considered invalid).
	 * The ArrayList of invalid passwords will be of the following format: password BLANK message of the exception thrown
	 * @param passwords list of passwords
	 * @return ArrayList of invalid passwords in the correct format
	 */
	static ArrayList<String> getInvalidPasswords(ArrayList<String> passwords) {
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

	/** Compare equality of two passwords
	 * @param password password string to be checked for
	 * @param passwordConfirm passwordConfirm string to be checked against password for
	 * @return
	 */
	static boolean comparePasswordsWithReturn(String password, String passwordConfirm){
		return(password.contentEquals(passwordConfirm));	
	}


	/** Checks if the password contains 6 to 9 characters
	 * @param password password string to be checked for
	 * @return true if password contains 6 to 9 characters, false otherwise
	 */
	static boolean hasBetweenSixAndNineChars(String password) {
		boolean isGTESixAndLTENineChars = password.length() >= 6 && password.length() <=9;
		return isGTESixAndLTENineChars;
	}

	/** Checks the password Digit requirement - Password must contain a numeric character
	 * @param password password string to be checked for Digit requirement
	 * @return true if meet Digit requirement
	 * @throws NoDigitException thrown if does not meet Digit requirement
	 */
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

	/** Checks the password lowercase requirement - Password must contain at least one lowercase alpha character
	 * @param password password string to be checked for lowercase requirement
	 * @return true if meets lowercase requirement
	 * @throws NoLowerAlphaException thrown if does not meet lowercase requirement
	 */
	static boolean hasLowerAlpha(String password) throws NoLowerAlphaException {
		boolean hasLowerCase = !password.equals(password.toUpperCase());
		if(!hasLowerCase || !hasAlphaChar(password))
			throw new NoLowerAlphaException();
		return hasLowerCase;
	}

	/**Checks the password SpecialCharacter requirement - Password must contain a Special Character
	 * @param password password string to be checked for SpecialCharacter requirement
	 * @return true if meets SpecialCharacter requirement
	 * @throws NoSpecialCharacterException thrown if does not meet SpecialCharacter requirement
	 */
	static boolean hasSpecialChar(String password) throws NoSpecialCharacterException {
		String rgx = "[a-zA-Z0-9]*";
		Pattern pattern = Pattern.compile(rgx);
		Matcher matcher = pattern.matcher(password);
		boolean result = !matcher.matches();
		if (!result)
			throw new NoSpecialCharacterException();
		return (result);
	}


	/**Checks the password alpha character requirement - Password must contain an uppercase alpha character
	 * @param password password string to be checked for alpha character requirement
	 * @return true if meet alpha character requirement
	 * @throws NoUpperAlphaException thrown if does not meet alpha character requirement
	 */
	static boolean hasUpperAlpha(String password) throws NoUpperAlphaException {
		boolean hasUpperCase = !password.equals(password.toLowerCase());
		if(!hasUpperCase || !hasAlphaChar(password))
			throw new NoUpperAlphaException();
		return hasUpperCase;
	}

	/** Checks the password length requirement - The password must be at least 6 characters long
	 * @param password password string to be checked for length
	 * @return true if meets minimum length requirement
	 * @throws LengthException thrown if does not meet minimum length requirement
	 */
	static boolean isValidLength(String password) throws LengthException {
		boolean hasValidLength = password.length() >= 6;
		if(!hasValidLength)
			throw new LengthException();
		return hasValidLength;
	}

	/** Checks the password Sequence requirement - Password should not contain more than 2 of the same character in sequence
	 * @param password password string to be checked for Sequence requirement
	 * @return false if does NOT meet Sequence requirement
	 * @throws InvalidSequenceException thrown if doesn't meet the Sequence requirement
	 */
	static boolean noSameCharInSequence(String password) throws InvalidSequenceException {
		String rgx = ".*(.)\\1\\1.*";
		Pattern pattern = Pattern.compile(rgx);
		Matcher match = pattern.matcher(password);
        
		boolean result = match.matches();
		
        if (result)
        	throw new InvalidSequenceException();
		return !result;
	}


	/** Helper method for hasLowerAlpha and hasUpperAlpha.
	 * Checks to ensure the string has alphabetical characters.
	 * @param password password string to check
	 * @return true if alphabetical characters are present--false otherwise.
	 */
	private static boolean hasAlphaChar(String password) {
		String rgx = ".*[a-zA-Z].*";
        Pattern pattern = Pattern.compile(rgx);
        Matcher matcher = pattern.matcher(password);
        boolean result = matcher.matches();
		return result;
	}


}
