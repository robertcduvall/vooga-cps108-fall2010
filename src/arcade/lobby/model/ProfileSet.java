package arcade.lobby.model;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ProfileSet implements Iterable<Profile> {
	public DatabaseAdapter myDbAdapter;
	public String myTable;

	public ProfileSet(String host, String dbName, String tableName,
			String user, String pass) {
		myDbAdapter = new MySqlAdapter(host, dbName, user, pass);
		myTable = tableName;
	}

	public int size() {
		String[] col = myDbAdapter.getColumn(myTable, "User_Name");
		return col.length;
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

	public Profile getProfile(String userName) {
		Map<String, String> row = myDbAdapter.getRow(myTable, userName);
		if(row.get("User_Name")==null) return null;
		Profile userProf = new Profile(userName);
		userProf.setName(row.get("First_Name"), row.get("Last_Name"));
		try {
			userProf.setBirthday(new Date(Long.parseLong(row.get("Birthday"))));
		} catch (NumberFormatException e) {
//			e.printStackTrace();
		}
		userProf.setEmail(row.get("Email"));
		userProf.setAvatar(row.get("Avatar_Url"));
		return userProf;
	}

	public Profile getProfile(int rowNo) {
		return getProfile(myDbAdapter.getColumn(myTable, "User_Name")[rowNo]);
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

}
