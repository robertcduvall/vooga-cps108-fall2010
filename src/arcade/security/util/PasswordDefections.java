package arcade.security.util;

import java.util.*;

public class PasswordDefections {
	private boolean lettersOnly, numbersOnly;
	private int consecUpperLength, consecLowerLength, consecNumberLength;
	private int seqLetterLength, seqNumberLength, seqSymbolLength;
	private int repeatCharacters;
	private String password;

	public void checkDefections(String password) {
		this.password = password;
		resetParams();
		for (int i = 0; i < password.length(); i++) {
			char c = password.charAt(i);
			if (!Character.isLetter(c))
				lettersOnly = false;
			if (!Character.isDigit(c))
				numbersOnly = false;
		}
	}

	private void resetParams() {
		lettersOnly = true;
		numbersOnly = true;
		consecUpperLength = 0;
		consecLowerLength = 0;
		consecNumberLength = 0;
		seqLetterLength = 0;
		seqNumberLength = 0;
		seqSymbolLength = 0;
		repeatCharacters = 0;
	}

	public int repeatCharScore() {
		int totalScore = 0;
		int len = password.length();
		Map<Character, ArrayList<Integer>> chars = new HashMap<Character, ArrayList<Integer>>();
		for (int i = 0; i < len; i++) {
			char c = password.charAt(i);
			if (!chars.containsKey(c)) {
				ArrayList<Integer> posList = new ArrayList<Integer>();
				chars.put(c, posList);
			}
			chars.get(c).add(i);
			ArrayList<Integer> currentList = chars.get(c);
			int s = currentList.size();
			if (s > 1) {
				int last = currentList.get(s - 1);
				int nextToLast = currentList.get(s - 2);
				totalScore += (len / Math.abs(last - nextToLast));
			}
		}
		return totalScore;
	}

	public boolean lettersOnly() {
		return lettersOnly;
	}

	public boolean numbersOnly() {
		return numbersOnly;
	}

	public int consecUpperLength() {
		int currentLength = 0;
		int semaphore;
		for (int i = 0; i < password.length(); i++) {
			if (Character.isUpperCase(password.charAt(i))) {
				currentLength++;
				semaphore = 1;
			} else
				semaphore = 0;
			if (semaphore == 0 && currentLength > consecUpperLength) {
				consecUpperLength = currentLength;
				currentLength = 0;
			}
		}
		return consecUpperLength;
	}

	public int consecLowerLength() {
		int currentLength = 0;
		int semaphore;
		for (int i = 0; i < password.length(); i++) {
			if (Character.isLowerCase(password.charAt(i))) {
				currentLength++;
				semaphore = 1;
			} else
				semaphore = 0;
			if (semaphore == 0 && currentLength > consecLowerLength) {
				consecLowerLength = currentLength;
				currentLength = 0;
			}
		}
		return consecLowerLength;
	}

	public int consecNumberLength() {
		int currentLength = 0;
		int semaphore = 0;
		for (int i = 0; i < password.length(); i++) {
			if (Character.isDigit(password.charAt(i))) {
				currentLength++;
				semaphore = 1;
			} else
				semaphore = 0;
			if (semaphore == 0 && currentLength > consecNumberLength) {
				consecNumberLength = currentLength;
				currentLength = 0;
			}
		}
		return consecNumberLength;
	}

	public int repeatCharacters() {
		int currentLength = 0;
		int semaphore = 0;
		char previousChar = ' ';
		for (int i = 0; i < password.length(); i++) {
			if (password.charAt(i) == previousChar) {
				currentLength++;
				semaphore = 1;
			} else
				semaphore = 0;
			previousChar = password.charAt(i);
			if (semaphore == 0 && currentLength > repeatCharacters) {
				repeatCharacters = currentLength;
				currentLength = 0;
			}
		}
		return repeatCharacters;
	}

	public int seqLetterLength() {
		int previousChar = -2;
		int currentLength = 0;
		int semaphore = 0;
		for (int i = 0; i < password.length(); i++) {
			char c = password.charAt(i);
			if (Character.isLetter(c)) {
				c = Character.toLowerCase(c);
				int charIndex = c - 'a';
				if (charIndex == previousChar + 1) {
					currentLength++;
					semaphore = 1;
				} else
					semaphore = 0;
				previousChar = charIndex;
			} else
				semaphore = 0;
			if (semaphore == 0 && currentLength > seqLetterLength
					&& currentLength >= 3) {
				seqLetterLength = currentLength;
				currentLength = 0;
			}
		}
		return seqLetterLength;
	}

	public int seqNumberLength() {
		int previousNumber = -2;
		int currentLength = 0;
		int semaphore = 0;
		for (int i = 0; i < password.length(); i++) {
			char c = password.charAt(i);
			if (Character.isDigit(c)) {
				int numIndex = c - '0';
				if (numIndex == previousNumber + 1) {
					currentLength++;
					semaphore = 1;
				} else
					semaphore = 0;
				previousNumber = numIndex;
			} else
				semaphore = 0;
			if (semaphore == 0 && currentLength > seqNumberLength
					&& currentLength >= 3) {
				seqNumberLength = currentLength;
				currentLength = 0;
			}
		}
		return seqNumberLength;
	}

	public int seqSymbolLength() {
		int previousSymbol = -2;
		int currentLength = 0;
		int semaphore = 0;
		for (int i = 0; i < password.length(); i++) {
			char c = password.charAt(i);
			if (!Character.isLetterOrDigit(c)) {
				int symbolIndex = c - '!';
				if (symbolIndex == previousSymbol + 1) {
					currentLength++;
					semaphore = 1;
				} else
					semaphore = 0;
				previousSymbol = symbolIndex;
			} else
				semaphore = 0;
			if (semaphore == 0 && currentLength > seqSymbolLength
					&& currentLength >= 3) {
				seqSymbolLength = currentLength;
				currentLength = 0;
			}
		}
		return seqSymbolLength;
	}

}
