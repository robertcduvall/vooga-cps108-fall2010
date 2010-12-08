package arcade.lobby.model;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import arcade.util.database.DatabaseAdapter;
import arcade.util.database.MySqlAdapter;

public class ProfileSet implements Iterable<Profile> {
	
	public Profile currentProfile = null;
	public DatabaseAdapter myDbAdapter;
	public String myTable;

	public ProfileSet(String host, String dbName, String tableName,
			String user, String pass) {
		myDbAdapter = new MySqlAdapter(host, dbName, user, pass);
		myTable = tableName;
	}

	public int size() {
		return myDbAdapter.getColumn(myTable, "User_Name").size();
	}

	public boolean addProfile(Profile profile) {
		Map<String, String> row = new HashMap<String, String>();
		row.put("User_Name", profile.getUserName());
		row.put("First_Name", profile.getFirstName());
		row.put("Last_Name", profile.getLastName());
		row.put("Birthday", profile.getBirthday());
		row.put("Avatar_Url", profile.getAvatar());
		row.put("Email", profile.getEmail());
		return myDbAdapter.insert(myTable, row);
	}
	
	public boolean updateProfile(Profile profile) {
		Map<String, String> row = new HashMap<String, String>();
		row.put("First_Name", profile.getFirstName());
		row.put("Last_Name", profile.getLastName());
		row.put("Birthday", profile.getBirthday());
		row.put("Avatar_Url", profile.getAvatar());
		row.put("Email", profile.getEmail());
		return myDbAdapter.update(myTable, "User_Name", profile.getUserName(), row);
	}

	public Profile getProfile(String userName) {
		List<Map<String, String>> rows = myDbAdapter.getRows(myTable, "User_Name", userName);
		if(rows.size() == 0) return null;
		Map<String,String> row = rows.get(0);
		Profile userProf = new Profile(userName);
		userProf.setName(row.get("First_Name"), row.get("Last_Name"));
		try {
			userProf.setBirthday(row.get("Birthday"));
		} catch (NumberFormatException e) {
//			e.printStackTrace();
		}
		userProf.setEmail(row.get("Email"));
		userProf.setAvatar(row.get("Avatar_Url"));
		return userProf;
	}

	public Profile getProfile(int rowNo) {
		return getProfile(myDbAdapter.getColumn(myTable, "User_Name").get(rowNo));
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
	
	public void setUser(String userName){
		currentProfile = getProfile(userName);
	}

}
