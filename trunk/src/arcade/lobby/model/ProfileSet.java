package arcade.lobby.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;

public class ProfileSet implements Iterable<Profile> {
	public DatabaseAdapter myDbAdapter;
	public String myTable;
	
	public ProfileSet(String host, String dbName, String tableName, String user, String pass) {
		myDbAdapter = new DatabaseAdapter(host,dbName,user,pass);
	}

	public int size() {
		ResultSet col = myDbAdapter.getColumn(myTable,0);
		try {
			return col.getFetchSize();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	public boolean addProfile(Profile profile) {
		//TODO add column names
		String[] columns = {};
		//TODO add values in same order as columns
		String[] values = new String[columns.length];
		return myDbAdapter.insert(columns,values);
	}
	
	public Profile getProfile(String userName) {
		ResultSet row = myDbAdapter.getRow(userName);
		Profile userProf = new Profile(userName);
		try {
			userProf.setName(row.getString("fName"),row.getString("lName"));
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public Profile getProfile(int row) {
		return null;
	}

	@Override
	public Iterator<Profile> iterator() {
		return new Iterator<Profile>(){

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
				throw(new UnsupportedOperationException());
			}
			
		};
	}

}
