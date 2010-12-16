package arcade.security.util.userserviceutil;

import java.util.*;
import arcade.security.util.DataHandler;

/**
 * This class represents a map of privileges. Each User has one of these, and it
 * determines what that user can and cannot do.
 * 
 * @author Meng Li, Jiaqi Yan, Nick Hawthorne
 * 
 */
public class PrivilegeMap {

	private static DataHandler mapHandler = DataHandler
			.getInstance("PrivilegeMap");
	private static Map<Integer, String> privilegeMap = new HashMap<Integer, String>();
	private static Map<String, Integer> privilegeIndex = new HashMap<String, Integer>();
	private static int numPrivileges;
	// temporary settings
	private static String GuestPrivilege = "000000000";
	private static String UserPrivilege = "000000111";
	private static String DeveloperPrivilege = "000111111";
	private static String AdminPrivilege = "111111111";

	/**
	 * Constructor for the PrivilegeMap. Loads the map from the map handler
	 */
	public PrivilegeMap() {
		loadMap();
	}

	/**
	 * Creates a Privilege map from a String, which is a binary representation
	 * of which privileges are allowed and which are not.
	 * 
	 * @param privilege
	 *            The binary string of which privileges are on and which are off
	 * @return A map of privileges to boolean values
	 */
	public Map<String, Boolean> userPrivilegeMap(String privilege) {
		Map<String, Boolean> userPrivilegeMap = new HashMap<String, Boolean>();
		for (int i = 0; i < privilege.length(); i++) {
			String name = privilegeMap.get(i);
			boolean value = (privilege.charAt(i) == '1');
			userPrivilegeMap.put(name, value);
		}
		return userPrivilegeMap;
	}

	/**
	 * Returns the default Guest Privilege set
	 */
	public static String getGuestPrivilege() {
		return GuestPrivilege;
	}

	/**
	 * Returns the default User Privilege set
	 */
	public static String getUserPrivilege() {
		return UserPrivilege;
	}

	/**
	 * Returns the default Developer Privilege set
	 */
	public static String getDeveloperPrivilege() {
		return DeveloperPrivilege;
	}

	/**
	 * Returns the default Admin Privilege set
	 */
	public static String getAdminPrivilege() {
		return AdminPrivilege;
	}

	/**
	 * Loads the privilege map
	 */
	public static void loadMap() {
		privilegeMap = mapHandler.getPrivilegeMap();
		privilegeIndex = mapHandler.getPrivilegeIndex();
		numPrivileges = privilegeMap.size();
	}

	/**
	 * Returns the number of privileges in the map
	 * 
	 * @return the number of privileges
	 */
	public int getNumPrivileges() {
		return numPrivileges;
	}

	/**
	 * Adds a privilege to the map
	 * 
	 * @param privilegeName
	 *            the name of the privilege to be added
	 */
	public void addPrivilege(String privilegeName) {
		Map<String, String> row = new HashMap<String, String>();
		row.put("P_name", privilegeName);
		mapHandler.insert(row);
		// update();
		loadMap();
	}

	/**
	 * Returns the index of a specific privilege
	 * 
	 * @param pname
	 *            the privilege to look up
	 * @return the index of that privilege
	 */
	public int getPrivilegeIndex(String pname) {
		return privilegeIndex.get(pname);
	}

	/**
	 * Returns the name of a privilege, given its index
	 * 
	 * @param pid
	 *            the privilege index
	 * @return the name of the privilege
	 */
	public static String getPrivilegeString(int pid) {
		return privilegeMap.get(pid);
	}

}
