package arcade.security.util.userserviceutil;

import java.util.*;

import arcade.security.util.userserviceutil.PrivilegeMap;
import arcade.security.util.DataHandler;

/**
 * This class contains all the actions that administrators are allowed to
 * perform with regards to other users.
 * 
 * @author Meng Li, Jiaqi Yan, Nick Hawthorne
 * 
 */
public class Admin {
	private static DataHandler userHandler = DataHandler.getInstance("User");
	private static DataHandler privilegeHandler = DataHandler
			.getInstance("Privileges");

	private static List<String> loggedInUsers = new ArrayList<String>();
	private static char[] currentPrivileges;

	/**
	 * Refreshes the list of currently logged in users
	 */
	public static void refreshInfo() {
		loggedInUsers = userHandler.getLoggedInUsers();
	}

	/**
	 * Allows a specific user access to a specific privilege
	 * 
	 * @param username
	 *            the user whose privileges are to be changed
	 * @param privilegeName
	 *            the privilege to allow access to
	 */
	public static void allowAccess(String username, String privilegeName) {
		int index = UserServiceFactory.getPrivilegeMap().getPrivilegeIndex(privilegeName);
		currentPrivileges[index] = '1';
		privilegeHandler.setUserPrivilege(username,
				String.valueOf(currentPrivileges));
	}

	/**
	 * Denies a specific user access to a specific privilege
	 * 
	 * @param username
	 *            the user whose privileges are to be changed
	 * @param privilegeName
	 *            the privilege to deny access to
	 */
	public static void denyAccess(String username, String privilegeName) {
		int index = UserServiceFactory.getPrivilegeMap().getPrivilegeIndex(privilegeName);
		currentPrivileges[index] = '1';
		privilegeHandler.setUserPrivilege(username,String.valueOf(currentPrivileges));
	}

	/**
	 * Checks whether a user has access to a certain privilege
	 * 
	 * @param username
	 *            the user to check for
	 * @param privilegeName
	 *            the privilege to check for
	 * @return true if the user has access
	 */
	public static boolean hasAccess(String username, String privilegeName) {
		loadUserPrivileges(username);
		int index = UserServiceFactory.getPrivilegeMap().getPrivilegeIndex(privilegeName);
		return (currentPrivileges[index] == '1');
	}

	/**
	 * Loads a user's privileges
	 * 
	 * @param username
	 *            the user whose privileges are to be loaded
	 */
	private static void loadUserPrivileges(String username) {
		currentPrivileges = privilegeHandler.getPrivileges(username)
				.toCharArray();
	}

	/**
	 * Logs a user out of the system.
	 * 
	 * @param username
	 *            the user to be logged out
	 */
	public static void kickOutUser(String username) {
		userHandler.setLoggedOut(username);
	}

	/**
	 * Gets a List of all the logged in users.
	 * 
	 * @return a List of logged in users
	 */
	public static List<String> getAllLoggedInUsrs() {
		return loggedInUsers;
	}

}
