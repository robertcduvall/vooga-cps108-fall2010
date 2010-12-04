package arcade.lobby.model;

import java.util.Iterator;

public class ProfileSet implements Iterable<Profile> {
	public DatabaseAdapter myDbAdapter;
	public String myTable;
	
	public ProfileSet(String host, String dbName, String tableName, String user, String pass) {
		myDbAdapter = new DatabaseAdapter(host,dbName,user,pass);
	}

	public int size() {
		String[] col = myDbAdapter.getColumn(myTable,0);
		return col.length;
	}
	
	public Profile getProfile(String userName) {
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
