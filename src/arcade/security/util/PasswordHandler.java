package arcade.security.util;

//import arcade.security.resourcesbundle.*;
/**
 * The scoring algorithm is referenced from http://www.passwordmeter.com/.
 * Checks the robustness of the password. 
 * @author: Jiaqi Yan 
 */

public class PasswordHandler {
	private String password;
	private int score;
	private int length;
	private PasswordStats stats;
	private PasswordDefections defections;

	public PasswordHandler() {
		stats = new PasswordStats();
		defections = new PasswordDefections();
	}

	public int getScore(String password) {
		this.password = password;
		length = password.length();
		score = 0;
		calcAdditions();
		calcDeductions();
		return score;
	}
	
	
	
	public boolean passwordCorrect(String password){
		this.password  = password;
		return true;
	}

	private void calcAdditions() {
		stats.checkPassword(password);
		score += length * 4;
		score += (length - stats.numUpperCaseLetters()) * 2;
		score += (length - stats.numLowerCaseLetters()) * 2;
		score += stats.numNumbers() * 4;
		score += stats.numSymbols() * 6;
		score += stats.numMiddleNumberOrSymbols() * 2;
		// potentially specified requirements
		// score+=stats.numRequirements();
	}

	private void calcDeductions() {
		defections.checkDefections(password);
		if (defections.lettersOnly())
			score -= length;
		if (defections.numbersOnly())
			score -= length;
		/*
		 * Calculate increment deduction based on proximity to identical
		 * characters Deduction is incremented each time a new match is
		 * discovered Deduction amount is based on total password length divided
		 * by the difference of distance between currently selected match
		 */
		score -= (defections.repeatCharScore());
		
		score -= (defections.consecUpperLength() * 2);
		score -= (defections.consecLowerLength() * 2);
		score -= (defections.consecNumberLength() * 2);
		score -= (defections.seqLetterLength() * 3);
		score -= (defections.seqNumberLength() * 3);
		score -= (defections.seqSymbolLength() * 3);

	}

}
