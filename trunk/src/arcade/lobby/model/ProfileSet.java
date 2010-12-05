package arcade.lobby.model;

import java.util.Iterator;
import java.util.Map;

public class ProfileSet implements Iterable<Profile> {
	public DatabaseAdapter myDbAdapter;
	public String myTable;

	public ProfileSet(String host, String dbName, String tableName,
			String user, String pass) {
		myDbAdapter = new MySqlAdapter(host, dbName, user, pass);
	}

	public int size() {
		String[] col = myDbAdapter.getColumn(myTable, 0);
		return col.length;
	}

	public boolean addProfile(Profile profile) {
		// TODO add column names
		String[] columns = {};
		// TODO add values in same order as columns
		String[] values = new String[columns.length];
		return myDbAdapter.insert(columns, values);
	}

	public Profile getProfile(String userName) {
		Map<String, String> row = myDbAdapter.getRow(userName);
		Profile userProf = new Profile(userName);
		userProf.setName(row.get("First_Name"), row.get("Last_Name"));
		return null;
	}

	public Profile getProfile(int row) {
		return null;
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
				myLoc++;
				return getProfile(myLoc);
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
