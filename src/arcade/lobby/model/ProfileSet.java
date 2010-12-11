package arcade.lobby.model;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import arcade.util.database.DatabaseAdapter;
import arcade.util.database.MySqlAdapter;
import arcade.util.database.Constants;

public class ProfileSet implements Iterable<Profile> {
	
	public static Profile currentProfile = null;
	public static DatabaseAdapter myDbAdapter;
	public static String myTable = "Profile";

	public ProfileSet(String host, String dbName, String tableName,
			String user, String pass) {
		myDbAdapter = new MySqlAdapter(host, dbName, user, pass);
		myTable = tableName;
	}
	
	private static void checkAdapter(){
		if(myDbAdapter == null){
			myDbAdapter = new MySqlAdapter(Constants.HOST, Constants.DBNAME, Constants.USER, Constants.PASSWORD);
		}
	}

	public int size() {
		checkAdapter();
		return myDbAdapter.getColumn(myTable, "UserName").size();
	}

	public static boolean addProfile(Profile profile) {
		checkAdapter();
		Map<String, String> row = new HashMap<String, String>();
		row.put("User_Id", profile.getUserName());
		row.put("FirstName", profile.getFirstName());
		row.put("LastName", profile.getLastName());
		row.put("Birthday", profile.getBirthday());
		row.put("AvatarUrl", profile.getAvatar());
		row.put("Email", profile.getEmail());
		return myDbAdapter.insert(myTable, row);
	}
	
	public static boolean updateProfile(Profile profile) {
		checkAdapter();
		Map<String, String> row = new HashMap<String, String>();
		row.put("FirstName", profile.getFirstName());
		row.put("LastName", profile.getLastName());
		row.put("Birthday", profile.getBirthday());
		row.put("AvatarUrl", profile.getAvatar());
		row.put("Email", profile.getEmail());
		return myDbAdapter.update(myTable, "User_Id", Integer.toString(profile.getUserId()), row);
	}

	public static Profile getProfile(int userId) {
		checkAdapter();
		List<Map<String, String>> rows = myDbAdapter.getRows(myTable, "User_Id", Integer.toString(userId));
		System.out.println("profile data: "+rows);
		Profile userProf = new Profile(userId);
		if(rows == null || rows.size() == 0) return userProf;
		Map<String,String> row = rows.get(0);
		userProf.setName(row.get("FirstName"), row.get("LastName"));
		try {
			userProf.setBirthday(row.get("Birthday"));
		} catch (NumberFormatException e) {
//			e.printStackTrace();
		}
		userProf.setEmail(row.get("Email"));
		userProf.setAvatar(row.get("AvatarUrl"));
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
	
	public static void setUser(int userId){
		currentProfile = getProfile(userId);
	}

}
