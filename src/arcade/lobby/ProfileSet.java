package arcade.lobby;

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

	@Override
	public Iterator<Profile> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

}
