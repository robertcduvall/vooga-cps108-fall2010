package arcade.lobby.model;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import arcade.util.database.DatabaseAdapter;
import arcade.util.database.MySqlAdapter;
import arcade.util.database.Constants;

public class ProfileSet implements Iterable<Profile> {

	public static Profile currentProfile = null;
	public static DatabaseAdapter myDbAdapter;
	private static ResourceBundle resourceBundle = ResourceBundle
			.getBundle("arcade.lobby.resources.resources");
	public static String myTable = resourceBundle.getString("dbProfile");

	public ProfileSet(String host, String dbName, String tableName,
			String user, String pass) {
		myDbAdapter = new MySqlAdapter(host, dbName, user, pass);
		myTable = tableName;
		ResourceBundle.getBundle("resources.resources");
	}

	private static void checkAdapter() {
		if (myDbAdapter == null) {
			myDbAdapter = new MySqlAdapter(Constants.HOST, Constants.DBNAME,
					Constants.USER, Constants.PASSWORD);
		}
	}

	public int size() {
		checkAdapter();
		return myDbAdapter.getColumn(myTable,
				resourceBundle.getString("dbUserName")).size();
	}

	public static boolean addProfile(Profile profile) {
		Map<String, String> row = addToRow(profile);
		return myDbAdapter.insert(myTable, row);
	}

	public static boolean updateProfile(Profile profile) {
		Map<String, String> row = addToRow(profile);
		return myDbAdapter.update(myTable,
				resourceBundle.getString("dbUserID"),
				Integer.toString(profile.getUserId()), row);
	}

	private static Map<String, String> addToRow(Profile profile) {
		checkAdapter();
		Map<String, String> row = new HashMap<String, String>();
		row.put(resourceBundle.getString("dbFirstName"),
				nullToEmpty(profile.getFirstName()));
		row.put(resourceBundle.getString("dbLastName"),
				nullToEmpty(profile.getLastName()));
		row.put(resourceBundle.getString("birthday"),
				nullToEmpty(profile.getBirthday()));
		row.put(resourceBundle.getString("dbAvatarURL"),
				nullToEmpty(profile.getAvatar()));
		row.put(resourceBundle.getString("email"),
				nullToEmpty(profile.getEmail()));
		row.put(resourceBundle.getString("dbUserID"),
				nullToEmpty(String.valueOf(profile.getUserId())));
		return row;
	}

	private static String nullToEmpty(String s) {
		if (s == null)
			return "";
		return s;
	}

	public static Profile getProfile(int userId) {
		checkAdapter();
		List<Map<String, String>> rows = myDbAdapter.getRows(myTable,
				"User_Id", Integer.toString(userId));
		System.out.println("profile data: " + rows);
		Profile userProf = new Profile(userId);
		if (rows == null || rows.size() == 0)
			return userProf;
		Map<String, String> row = rows.get(0);
		userProf.setName(row.get("FirstName"), row.get("LastName"));
		try {
			userProf.setBirthday(row.get(resourceBundle.getString("birthday")));
		} catch (NumberFormatException e) {
			// e.printStackTrace();
		}
		userProf.setEmail(row.get(resourceBundle.getString("email")));
		userProf.setAvatar(row.get(resourceBundle.getString("dbAvatarURL")));
		return userProf;
	}

	@Override
	public Iterator<Profile> iterator() {
		return new Iterator<Profile>() {

			int mySize = size();
			int myLoc = 0;

			@Override
			public boolean hasNext() {
				return myLoc < mySize;
			}

			@Override
			public Profile next() {
				return getProfile(myLoc++);
			}

			@Override
			/**
			 * This method will not be implemented
			 */
			public void remove() {
				throw (new UnsupportedOperationException());
			}

		};
	}

	public static void setUser(int userId) {
		currentProfile = getProfile(userId);
	}

}
