package arcade.security.model;


import arcade.security.util.LogInHandler;

public class LoginProcess implements IModel{
	
	/**
	 * Model object for the Login panel Used in conjunction with
	 * security.view.LogInPanel and security.control.LogInPanelControl.
	 * 
	 * @author Meng Li, Jiaqi Yan, Nick Hawthorne
	 * 
	 */
	public LoginProcess(){

	}
		/**
	 * Checks a username and password to see if they are in the database.
	 * 
	 * @param username
	 *            - the username to check
	 * @param password
	 *            - the password to check
	 * @return true if the login is successful
	 */
	public boolean isSuccessfulLogin(String username, String password){
		if(username.contains(" "))return false;
		return LogInHandler.successfulLogin(username, password);
	}
	/**
	 * Logs a user into the Arcade
	 * 
	 * @param username
	 *            the user to be logged in
	 */
	public void setUserAsLogined(int userId){
		LogInHandler.setCurrentUser(userId);
	}

	/**
	 * Checks whether a user is an administrator
	 * 
	 * @param username
	 *            - the user to check for
	 * @return true if the user is an administrator
	 */
	public boolean isAdmin(String username) {
		return LogInHandler.isAdmin(username);
	}
	
}
