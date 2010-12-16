package arcade.security.util.userserviceutil;

import arcade.lobby.model.Profile;
import arcade.lobby.model.ProfileSet;
import arcade.security.util.userserviceutil.PrivilegeMap;
import arcade.security.util.DataHandler;
import java.util.HashMap;
import java.util.Map;

/**
 * This class provides a way of accessing information about the currently logged
 * in user.
 * 
 * @author Meng Li, Jiaqi Yan, Nick Hawthorne
 * 
 */
public class CurrentUser implements IUser {

	private DataHandler userHandler = DataHandler.getInstance("User");
	private DataHandler privilegeHandler = DataHandler
			.getInstance("Privileges");
	private String currentUserName = "guest"; // default value
	private int currentUserID = 0; // default value
	private boolean isLoggedIn;
	private String privilege;
	private HashMap<String, Boolean> currentMap = new HashMap<String, Boolean>();
	private static Profile profile = ProfileSet.getCurrentProfile();
	private String userType = "guest";

	// remove all the static

	/**
	 * Logs in the user with this userId
	 * 
	 * @param userId
	 *            the user to log in
	 */
	public void setAsLogined(int userId) {
		profile = ProfileSet.getCurrentProfile();
		isLoggedIn = true;
		currentUserName = profile.getUserName();
		currentUserID = userId;// profile.getUserId();
		userType = "loginuser";// //TODO:
		userHandler.setLoggedIn(currentUserID);
		initializePrivileges(currentUserID);

	}
	
	
	public Map<String,Boolean> getUserPrivilegeMap(){
		return currentMap;
	}

	/**
	 * Logs out the current user
	 */
	public void setLoggedOut() {
		isLoggedIn = false;
		userHandler.setLoggedOut(currentUserName);
		setToDefaultPrivileges();
	}

	/**
	 * Sets the current users privileges to the defaults
	 */
	private void setToDefaultPrivileges() {
		currentMap = new HashMap<String, Boolean>();
		privilege = PrivilegeMap.getGuestPrivilege();
	}

	/**
	 * Gets the privileges for the user from the database
	 * 
	 * @param userId
	 *            the user to lookup
	 */
	private void initializePrivileges(int userId) {
		privilege = privilegeHandler.getPrivileges(userId);
		for (int i = 0; i < privilege.length(); i++) {
			boolean isAllowed = (privilege.charAt(i) == '0') ? false : true;
			String name = PrivilegeMap.getPrivilegeString(i);
			currentMap.put(name, isAllowed);
		}
	}

	/**
	 * Checks if the current user is logged in
	 * 
	 * @return true if the user is logged in
	 */
	public boolean isLoggedIn() {
		return isLoggedIn;
		// return (userHandler.isLoggedIn(currentUserName).equals("true"));
	}

	/**
	 * Gets the user type of the current user
	 * 
	 * @return the current user's type
	 */
	public String getUserType() {
		return userType;
		// return userHandler.getUserType(userId);
	}

	/**
	 * Checks whether this user has access to a specific privilege
	 * 
	 * @param privilegeName
	 *            the privilege to check for
	 * @return true if the user has access to that privilege
	 */
	public boolean isPrivilegeAllowed(String privilegeName) {
		return (currentMap.get(privilegeName));
	}

	/**
	 * Returns the current user's userID
	 * 
	 * @return
	 */
	public int getUserID() {
		return currentUserID;
	}

	/**
	 * Returns the current user's username
	 * 
	 * @return
	 */
	public String getUserName() {
		return currentUserName;
	}

	// **from Profile class
	/**
	 * Changes the user's name
	 * 
	 * @param first
	 *            the new first name
	 * @param last
	 *            the new last name
	 */
	public void setName(String first, String last) {
		profile.setName(first, last);

	}

	/**
	 * Changes the user's birthday
	 * 
	 * @param date
	 *            the new birthday
	 */
	public void setBirthday(String date) {
		profile.setBirthday(date);
	}

	/**
	 * Changes the user's email
	 * 
	 * @param email
	 *            the new email
	 */
	public void setEmail(String email) {
		profile.setEmail(email);
	}

	/**
	 * Changes the user's avatar
	 * 
	 * @param avatarURL
	 *            the new location of the avatar
	 */
	public void setAvatar(String avatarURL) {
		profile.setAvatar(avatarURL);
	}

	/**
	 * Changes the user's userName
	 * 
	 * @param userName
	 *            the new userName
	 */
	public void setUserName(String userName) {
		profile.setUserName(userName);
	}

	/**
	 * Returns the user's full name.
	 * 
	 * @return
	 */
	public String getFullName() {
		return profile.getFullName();
	}

	/**
	 * Returns the user's first name.
	 * 
	 * @return
	 */
	public String getFirstName() {
		return profile.getFirstName();
	}

	/**
	 * Returns the user's last name.
	 * 
	 * @return
	 */
	public String getLastName() {
		return profile.getLastName();
	}

	/**
	 * Returns the user's birthday.
	 * 
	 * @return
	 */
	public String getBirthday() {
		return profile.getBirthday();
	}

	/**
	 * Returns the user's email address.
	 * 
	 * @return
	 */
	public String getEmail() {
		return profile.getEmail();
	}

	/**
	 * Returns the user's avatar location.
	 * 
	 * @return
	 */
	public String getAvatar() {
		return profile.getAvatar();
	}

	/**
	 * Returns the user's date of joining the arcade.
	 * 
	 * @return
	 */
	public void setJoinDate(Long joinDate) {
		profile.setJoinDate(joinDate);
	}

	/**
	 * Returns the user's date of joining as a Long.
	 * 
	 * @return
	 */
	public Long getJoinDate() {
		return profile.getJoinDate();
	}

}
