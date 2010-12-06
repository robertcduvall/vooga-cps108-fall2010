package arcade.security.util;

/**
 * Helper class for password checking
 * 
 * @author Jiaqi Yan
 * 
 */
public class PasswordStats {

	// private String password;
	private int upperCaseLetters, lowerCaseLetters, symbols, numbers,
			middleNumber_Symbols;
	private int numRequirementsSatisfied;
	private boolean hasUpperCase, hasLowerCase, hasNumber, hasSymbols;
	private boolean requirementsSatisfied;

	public void checkPassword(String word) {
		resetStats();
		checkRequirements();
		for (int i = 0; i < word.length(); i++) {
			char c = word.charAt(i);
			if (Character.isUpperCase(c))
				upperCaseLetters++;
			if (Character.isLowerCase(c))
				lowerCaseLetters++;
			if (Character.isDigit(c))
				numbers++;
			if (!Character.isLetterOrDigit(c))
				symbols++;
			if (i != 0 && i != word.length() - 1 && !Character.isLetter(c))
				middleNumber_Symbols++;
		}
	}

	private void checkRequirements() {
		if (hasUpperCase)
			numRequirementsSatisfied++;
		if (hasLowerCase)
			numRequirementsSatisfied++;
		if (hasNumber)
			numRequirementsSatisfied++;
		if (hasSymbols)
			numRequirementsSatisfied++;
		requirementsSatisfied = (numRequirementsSatisfied == 4) ? true : false;
	}

	public boolean requirementsSatisfied() {
		return requirementsSatisfied;
	}

	public int numRequirements() {
		return numRequirementsSatisfied;
	}

	private void resetStats() {
		upperCaseLetters = 0;
		lowerCaseLetters = 0;
		symbols = 0;
		numbers = 0;
		middleNumber_Symbols = 0;
		numRequirementsSatisfied = 0;
		hasUpperCase = false;
		hasLowerCase = false;
		hasNumber = false;
		hasSymbols = false;
	}

	public int numUpperCaseLetters() {
		return upperCaseLetters;
	}

	public int numLowerCaseLetters() {
		return lowerCaseLetters;
	}

	public int numNumbers() {
		return numbers;
	}

	public int numSymbols() {
		return symbols;
	}

	public int numMiddleNumberOrSymbols() {
		return middleNumber_Symbols;
	}
}
