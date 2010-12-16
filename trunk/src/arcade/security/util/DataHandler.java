package arcade.security.util;

import java.util.Collections;
import java.util.Comparator;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import arcade.util.database.Constants;
import arcade.util.database.MySqlAdapter;

/**
 * This class handles all the direct interactions with the database
 * 
 * @author Jiaqi Yan, Meng Li, Nick Hawthorne
 * 
 */

public class DataHandler {

	private MySqlAdapter adapter;
	private String myTable;
	private final static Logger log = Logger.getLogger(DataHandler.class);
	private static DataHandler instance;

	/**
	 * Returns an instance of a DataHandler object with a specified table name.
	 * 
	 * @param tableName
	 *            The table to be handled
	 * @return A DataHandler object
	 */
	public static DataHandler getInstance(String tableName) {
		instance = new DataHandler(tableName);
		return instance;

	}

	/**
	 * Constructor for the DataHandler class.
	 * 
	 * @param tableName
	 *            The table to be handled
	 */
	private DataHandler(String tableName) {
		myTable = tableName;
		adapter = new MySqlAdapter(Constants.HOST, Constants.DBNAME,
				Constants.USER, Constants.PASSWORD);
	}

	/**
	 * Gets a map of the privileges and their corresponding indices.
	 * 
	 * @return The map of privileges
	 */
	public Map<String, Integer> getPrivilegeIndex() {
		List<String> id = adapter.getColumn(myTable, "P_Id");
		List<String> names = adapter.getColumn(myTable, "P_name");
		Map<String, Integer> privilegeMap = new HashMap<String, Integer>();
		Iterator<String> itr = names.iterator();
		for (String s : id) {
			privilegeMap.put(itr.next(), Integer.valueOf(s));
		}
		return privilegeMap;
	}

	/**
	 * Gets a map of indices and their corresponding privileges.
	 * 
	 * @return The map of indices
	 */
	public Map<Integer, String> getPrivilegeMap() {
		List<String> id = adapter.getColumn(myTable, "P_Id");
		List<String> names = adapter.getColumn(myTable, "P_name");
		Map<Integer, String> privilegeMap = new HashMap<Integer, String>();
		Iterator<String> itr = names.iterator();
		for (String s : id) {
			privilegeMap.put(Integer.valueOf(s), itr.next());
		}
		return privilegeMap;
	}

	/**
	 * Gets a map of usernames and their associated user types.
	 * 
	 * @return A map of usernames and user types
	 */
	public Map<String, String> getUserTypeMap() {
		List<String> usernames = adapter.getColumn(myTable, "UserName");
		List<String> userTypes = adapter.getColumn(myTable, "UserType");
		Map<String, String> userTypeMap = new HashMap<String, String>();
		Iterator<String> itr = userTypes.iterator();
		for (String name : usernames) {
			userTypeMap.put(name, itr.next());
		}
		return userTypeMap;
	}

	/**
	 * Given a map of usernames and indices of user types to which they should
	 * be reassigned, this method goes and makes the requisite changes to the
	 * table.
	 * 
	 * @param changeMap
	 *            A map of usernames and the integer indices of user types to
	 *            which they should be assigned
	 */
	public void updateUserTypes(Map<String, Integer> changeMap) {
		for (String name : changeMap.keySet()) {
			Map<String, String> row = new HashMap<String, String>();
			row.put("UserType", AdminHandler.getUserType(changeMap.get(name)));
			System.out.println("Will update");
			adapter.update(myTable, "UserName", name, row);
		}
	}

	/**
	 * Gets a List of currently logged in users.
	 * 
	 * @return The List of users logged in.
	 */
	public List<String> getLoggedInUsers() {
		List<Map<String, String>> list = adapter.getRows(myTable, "LoggedIn",
				"true");
		List<String> names = new ArrayList<String>();
		for (Map<String, String> m : list) {
			names.add(m.get("UserName"));
		}
		return names;
	}

	/**
	 * Logs in a user.
	 * 
	 * @param userId
	 *            The userID of the user to log in.
	 */
	public void setLoggedIn(int userId) {
		Map<String, String> row = new HashMap<String, String>();
		row.put("LoggedIn", "true");
		adapter.update(myTable, "UserName", Integer.toString(userId), row);
	}

	/**
	 * Sets a specific privilege for a specific user.
	 * 
	 * @param username
	 *            The user to which it should be assigned
	 * @param value
	 *            The privilege to be assigned
	 */
	public void setUserPrivilege(String username, String value) {
		Map<String, String> row = new HashMap<String, String>();
		row.put("Privileges", value);
		adapter.update(myTable, "UserName", username, row);
	}

	/**
	 * Logs a user out.
	 * 
	 * @param username
	 *            The username of the user to be logged out
	 */
	public void setLoggedOut(String username) {
		Map<String, String> row = new HashMap<String, String>();
		row.put("LoggedIn", "false");
		adapter.update(myTable, "UserName", username, row);
	}

	/**
	 * Checks if the user is logged in.
	 * 
	 * @param username
	 *            The user to check
	 * @return A String, either 0 or 1 (0 false, 1 true)
	 */
	public String isLoggedIn(String username) {
		return adapter.getRows(myTable, "UserName", username).get(0).get(
				"LoggedIn");
	}

	/**
	 * Checks if the user is an administrator.
	 * 
	 * @param username
	 *            The user to check
	 * @return A String, either 0 or 1 (0 false, 1 true)
	 */
	public String isAdmin(String username) {
		return adapter.getRows(myTable, "UserName", username).get(0).get(
				"UserType");
	}

	/**
	 * Gets a privilege name from its privilege id.
	 * 
	 * @param pid
	 *            The privilege ID number
	 * @return The privilege name
	 */
	public String getPrivilegeName(int pid) {
		return adapter.getRows(myTable, "P_Id", Integer.toString(pid)).get(0)
				.get("P_name");
	}

	/**
	 * Returns a list of privileges for a user from their userID.
	 * 
	 * @param userId
	 *            The user's userID
	 * @return The list of privileges for that user.
	 */
	public String getPrivileges(int userId) {
		return adapter.getRows(myTable, "User_Id", Integer.toString(userId))
				.get(0).get("Privileges");
	}

	/**
	 * Returns a list of privileges for a user from their username.
	 * 
	 * @param username
	 *            The user
	 * @return The list of privileges for that user.
	 */
	public String getPrivileges(String username) {
		return adapter.getRows(myTable, "UserName", username).get(0).get(
				"Privileges");
	}

	/**
	 * Gets a user password with their userID.
	 * 
	 * @param userId
	 *            The user's userID
	 * @return The user's password
	 */
	public String getPassword(int userId) {
		log.info("Getting password for: " + userId);
		Map<String, String> row = adapter.getRows(myTable, "Id",
				String.valueOf(userId)).get(0);
		return row.get("Password");
	}

	/**
	 * Adds a row to the table.
	 * 
	 * @param row
	 *            The row to add
	 * @return true if the row adds successfully
	 */
	public boolean insert(Map<String, String> row) {
		return adapter.insert(myTable, row);

	}

	/**
	 * Gets a user's userID from their username.
	 * 
	 * @param username
	 *            The user's username
	 * @return The user's userID
	 */
	public int getUserId(String username) {
		List<Map<String, String>> rows = adapter.getRows(myTable, "UserName",
				username);
		if (rows == null || rows.size() == 0)
			return 0;
		Map<String, String> row = rows.get(0);
		return Integer.valueOf(row.get("Id"));
	}

	/**
	 * Gets the index of a user's forgotten password question.
	 * 
	 * @param username
	 *            The user's username
	 * @return The user's password question's index
	 */
	public int getPasswordQuestionIndex(String username) {
		List<Map<String, String>> rows = adapter.getRows(myTable, "UserName",
				username);
		if (rows == null || rows.size() == 0)
			return 0;
		Map<String, String> row = rows.get(0);
		return Integer.valueOf(row.get("QuestionIndex"));
	}

	/**
	 * Gets the answer to a user's forgotten password question.
	 * 
	 * @param username
	 *            The user's username
	 * @return The answer to the user's forgotten password question
	 */
	public String getPasswordQuestionAnswer(String username) {
		List<Map<String, String>> rows = adapter.getRows(myTable, "UserName",
				username);
		if (rows == null || rows.size() == 0)
			return "false";
		Map<String, String> row = rows.get(0);
		return row.get("QuestionAnswer");
	}
}
