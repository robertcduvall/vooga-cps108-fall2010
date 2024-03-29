package arcade.lobby.model;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import arcade.util.database.DatabaseAdapter;
import arcade.util.database.MySqlAdapter;
import arcade.util.database.Constants;

/**
 * Communicates with the database, receiving and updating
 * current profile information.  
 * @author andrew, david, justin
 */
public class ProfileSet implements Iterable<Profile> {

	private static Profile currentProfile = null;
	private static final Profile GUEST_PROFILE = createGuest();
	/**
	 * Adapter used to connect to the MySQL database.
	 */
	public static DatabaseAdapter myDbAdapter = new MySqlAdapter(Constants.HOST, Constants.DBNAME,
			Constants.USER, Constants.PASSWORD);
	private static ResourceBundle resourceBundle = ResourceBundle
			.getBundle("arcade.lobby.resources.resources");
	/**
	 * The Profile table in the database.
	 */
	public static String myTable = resourceBundle.getString("dbProfile");



	private static Profile createGuest() {
		Profile guest = new Profile(0);
		guest.setName("Guest", "");
		guest.setUserName("guest");
		guest.setAvatar("http://www.cs.duke.edu/~rcd/images/rcd.jpg");
		return guest;
	}

	/**
	 * @return number of users in Profile table.
	 */
	public int size() {
		return myDbAdapter.getColumn(myTable,
				resourceBundle.getString("dbUserName")).size();
	}

	/**
	 * Adds a new profile to the database.
	 * @param profile
	 * @return if profile successfully added.
	 */
	public static boolean addProfile(Profile profile) {
		if (profile.getUserId() == 0)
			return false;
		Map<String, String> row = addToRow(profile);
		row.put(resourceBundle.getString("dbJoinDate"),
				String.valueOf(System.currentTimeMillis()));
		return myDbAdapter.insert(myTable, row);
	}

	/**
	 * Update an existing profile in the database.
	 * @param profile
	 * @return if profile was successfully updated.
	 */
	public static boolean updateProfile(Profile profile) {
		Map<String, String> row = addToRow(profile);
		return myDbAdapter.update(myTable,
				resourceBundle.getString("dbUserID"),
				Integer.toString(profile.getUserId()), row);
	}

	private static Map<String, String> addToRow(Profile profile) {
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

	/**
	 * @param userId
	 * @return profile with the matching userID in the database.
	 */
	public static Profile getProfile(int userId) {
		List<Map<String, String>> rows = myDbAdapter.getRows(myTable,
				resourceBundle.getString("dbUserID"), Integer.toString(userId));
		Profile userProf = new Profile(userId);
		if (rows == null || rows.size() == 0)
			return userProf;
		Map<String, String> row = rows.get(0);
		userProf.setName(row.get(resourceBundle.getString("dbFirstName")),
				row.get(resourceBundle.getString("dbLastName")));
		userProf.setBirthday(row.get(resourceBundle.getString("birthday")));
		userProf.setEmail(row.get(resourceBundle.getString("email")));
		userProf.setAvatar(row.get(resourceBundle.getString("dbAvatarURL")));
		try {
			userProf.setJoinDate(Long.parseLong(row.get(resourceBundle
					.getString("dbJoinDate"))));
		} catch (NumberFormatException e) {
		}
		
		System.out.println("my query start");
		List<Map<String, String>> userInfo = myDbAdapter.getRows(resourceBundle.getString("dbUserTable"),
				resourceBundle.getString("dbUserTableID"), Integer.toString(userId));
		System.out.println("stop");
		Map<String, String> userName = userInfo.get(0);
		userProf.setUserName(userName.get(resourceBundle.getString("username")));
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

	/**
	 * Sets the current user to the profile matching
	 * the given user ID.
	 * @param userId
	 */
	public static void setUser(int userId) {
		currentProfile = getProfile(userId);
	}
	
	/**
	 * @return list of available usernames in the database.
	 */
	public static Map<String,Integer> getUserNames() {
		List<String> names = myDbAdapter.getColumn("User", "UserName");
		List<String> ids = myDbAdapter.getColumn("User", "Id");
		Map<String,Integer> map = new HashMap<String,Integer>();
		for(int i = 0; i < names.size(); i++) {
			map.put(names.get(i),Integer.parseInt(ids.get(i)));
		}
		return map;
	}

	/**
	 * @return current profile accessing the Arcade.
	 */
	public static Profile getCurrentProfile() {
		return currentProfile == null ? GUEST_PROFILE : currentProfile;
	}

	/**
	 * Sets the current profile to the given profile.
	 * @param profile
	 */
	public static void setCurrentProfile(Profile profile) {
		currentProfile = profile;
	}

}
