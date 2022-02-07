import org.junit.*;
import org.junit.jupiter.api.BeforeAll;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;


/**
 * STUDENT tests for the methods of PasswordChecker
 * @author Evan Solomon
 *
 */
public class PasswordCheckerTest_STUDENT {

	private ArrayList<String> validPasswordsArray;
	private ArrayList<String> invalidPasswordsArray;



	@Before
	public void setUp() {
		validPasswordsArray = new ArrayList<>(Arrays.asList("a1A#b1Bc1Cd1D", "Hello@123", "4heB#rex7", "4saleHe!", "myPassword2%"));
		invalidPasswordsArray = new ArrayList<>(Arrays.asList("334455BB", "Im2cool4U", "george2ZZZ", "4sale", "bertha22", "4wardMarch",
				"august30", "Applesxx", "aa11b", "pilotProject", "myPassword",
				"myPassword2"));
	}

	@After
	public void tearDown() {
		validPasswordsArray = null;
	}

	/**
	 * Test if the password is less than 6 characters long.
	 * This test should throw a LengthException for second case.
	 */
	@Test()
	public void testIsValidPasswordTooShort() throws Exception {
		assertTrue(PasswordCheckerUtility.isValidPassword("a1A#b1Bc1Cd1D"));

		assertThrows(LengthException.class, () -> {
						PasswordCheckerUtility.isValidPassword("a1A#b");
					});
	}
	
	/**
	 * Test if the password has at least one uppercase alpha character
	 * This test should throw a NoUpperAlphaException for second case
	 */
	@Test
	public void testIsValidPasswordNoUpperAlpha() throws Exception {
		assertTrue(PasswordCheckerUtility.isValidPassword("August#1030"));

		assertThrows(NoUpperAlphaException.class, () -> {
						PasswordCheckerUtility.hasUpperAlpha("august#1030");
					});
	}


	/**
	 * Test if the password has at least one lowercase alpha character
	 * This test should throw a NoLowerAlphaException for second case
	 */
	@Test
	public void testIsValidPasswordNoLowerAlpha() throws Exception {
		assertTrue(PasswordCheckerUtility.isValidPassword("August#1030"));

		assertThrows(NoLowerAlphaException.class, () -> {
			PasswordCheckerUtility.hasLowerAlpha("AUGUST#1030");
		});
	}

	/**
	 * Test if the password is a valid password, but is too short to be secure.
	 * This test should throw a InvalidSequenceException for second case
	 */
	@Test
	public void testIsWeakPassword() throws Exception {
		assertTrue(PasswordCheckerUtility.isValidPassword("Hello6#olleH"));

		assertThrows(WeakPasswordException.class, () -> {
					PasswordCheckerUtility.isWeakPassword("Hello6#");
		});
	}

	/**
	 * Test if the password has more than 2 of the same character in sequence
	 * This test should throw a InvalidSequenceException for second case
	 */
	@Test
	public void testIsValidPasswordInvalidSequence() throws Exception {
		assertTrue(PasswordCheckerUtility.isValidPassword("Hello@123"));
		assertThrows(InvalidSequenceException.class, () -> {
			PasswordCheckerUtility.isValidPassword("Helllo@123");
		});
	}

	/**
	 * Test if the password has at least one digit
	 * One test should throw a NoDigitException
	 */
	@Test
	public void testIsValidPasswordNoDigit() throws Exception {
		assertTrue(PasswordCheckerUtility.isValidPassword("myPassword2#"));

		assertThrows(NoDigitException.class, () -> {
			PasswordCheckerUtility.isValidPassword("myPassword#");
		});
	}

	/**
	 * Test correct passwords
	 * This test should not throw an exception
	 */
	@Test
	public void testIsValidPasswordSuccessful() throws Exception {
		for(String password: validPasswordsArray){
			assertTrue(PasswordCheckerUtility.isValidPassword(password));
		}
	}

	/**
	 * Test the invalidPasswords method
	 * Check the results of the ArrayList of Strings returned by the validPasswords method
	 */
	@Test
	public void testInvalidPasswords() {
		ArrayList<String> results = PasswordCheckerUtility.getInvalidPasswords(invalidPasswordsArray);
		int expectedCountInvalidPasswords = invalidPasswordsArray.size();
		assertEquals(expectedCountInvalidPasswords, results.size());

	}
}
