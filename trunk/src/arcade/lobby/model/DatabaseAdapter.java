package arcade.lobby.model;

public class DatabaseAdapter {	
	
	public DatabaseAdapter(String host, String dbName, String user, String pass)  {
		connect(host,user,pass);
		selectDB(dbName);
	}
	
	private void connect(String host, String user, String pass) {
		
	}
	
	private void selectDB(String dbName) {
		
	}
	
	public String[] getColumn(String tableName, String columnName) {
		return null;
		
	}
	
	public String[] getColumn(String tableName, int columnIndex) {
		return null;
		
	}
	
}
