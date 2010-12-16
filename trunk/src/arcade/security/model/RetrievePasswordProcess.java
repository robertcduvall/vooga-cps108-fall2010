package arcade.security.model;

import arcade.security.util.DataHandler;

/**
 * Model object for the forgotten password panel. Used in conjunction with
 * security.view.RetrievePasswordPanel and
 * security.control.RetrievePasswordPanelControl.
 * 
 * @author Meng Li, Jiaqi Yan, Nick Hawthorne
 * 
 */
public class RetrievePasswordProcess implements IModel {

	private static DataHandler dataHandler = DataHandler.getInstance("User");

	/**
	 * Constructor for the forgotten password model object
	 */
	public RetrievePasswordProcess() {

	}

	/**
	 * Checks whether the user's answer to the question is correct.
	 * 
	 * @param userNameInput
	 *            - the user requesting the information
	 * @param userAnswerInput
	 *            - the user's answer to the question
	 * @return true if the user correctly answered the question
	 */
	public boolean isAnswerMatched(String userNameInput, String userAnswerInput) {
		if (userNameInput.contains(" "))
			return false;
		return !(getPasswordQuestionAnswer(userNameInput).equals("false") || !getPasswordQuestionAnswer(
				userNameInput).equals(userAnswerInput));
	}

	/**
	 * Gets a specific user's password
	 * 
	 * @param username
	 *            the user to check for
	 * @return the user's password
	 */
	public String getPassword(String username) {
		return dataHandler.getPassword(dataHandler.getUserId(username));
	}

	/**
	 * Gets the answer to a specific user's password question
	 * 
	 * @param username
	 *            the user to check for
	 * @return the user's answer to their secret question
	 */
	public String getPasswordQuestionAnswer(String username) {
		return dataHandler.getPasswordQuestionAnswer(username);
	}

}
