package arcade.security.util;

import arcade.core.Arcade;
import arcade.lobby.model.ProfileSet;
import arcade.lobby.view.ProfilePanel;
import arcade.security.util.userserviceutil.PrivilegeMap;
import arcade.security.util.userserviceutil.CurrentUser;
import arcade.security.util.userserviceutil.UserServiceFactory;

/**
 * A model object for the login panel.
 * 
 * @author Meng Li, Jiaqi Yan, Nick Hawthorne
 * 
 */
public class LogInHandler {
	public static DataHandler dataHandler = DataHandler.getInstance("User");

	/**
	 * Checks a username/password combination to see if it exists in the
	 * database, and logs that user in if it does.
	 * 
	 * @param username
	 *            The username to check
	 * @param password
	 *            The password to check
	 * @return true if the login is sucessful.
	 */
	public static boolean successfulLogin(String username, String password) {
		int userId = getUserId(username);
		if (userId < 1)
			return false;
		if (isPasswordValid(userId, password)) {
			// Arcade.refreshRight(); //from arcade group
			ProfileSet.setUser(userId);
			ProfileSet.getCurrentProfile().setUserName(username);
			return true;
		}
		return false;
	}

	/**
	 * Sets the current user by userID.
	 * 
	 * @param userId
	 *            The user to set as current
	 */
	public static void setCurrentUser(int userId) {
		PrivilegeMap.loadMap();
		CurrentUser user = UserServiceFactory.getCurrentUser();
		user.setAsLogined(userId);
	}

	/**
	 * Gets the userId for a specific user.
	 * 
	 * @param username
	 *            The user in question
	 * @return The user's userID
	 */
	public static int getUserId(String username) {
		return dataHandler.getUserId(username);
	}

	/**
	 * Checks if a user is an administrator.
	 * 
	 * @param username
	 *            The user to check
	 * @return true if the user is an administrator
	 */
	public static boolean isAdmin(String username) {
		return dataHandler.isAdmin(username).equals("Administrator");
	}

	/**
	 * Checks a password to see if it corresponds to a userID.
	 * 
	 * @param userId
	 *            The userID to check
	 * @param password
	 *            The password to check
	 * @return true if they match
	 */
	public static boolean isPasswordValid(int userId, String password) {
		String validPassword = dataHandler.getPassword(userId);
		PasswordHasher hasher = new PasswordHasher();
		String hashedPassword = hasher.encrypt(String.valueOf(password));
		return validPassword.compareTo(hashedPassword) == 0;
	}
}
