package arcade.lobby.model;

import java.sql.ResultSet;

public class DatabaseAdapter {	
	
	public DatabaseAdapter(String host, String dbName, String user, String pass)  {
		connect(host,user,pass);
		selectDB(dbName);
	}
	
	private void connect(String host, String user, String pass) {
		
	}
	
	private void selectDB(String dbName) {
		
	}
	
	public ResultSet getColumn(String tableName, String columnName) {
		return null;
		
	}
	
	public ResultSet getColumn(String tableName, int columnIndex) {
		return null;
		
	}
	
	public ResultSet getRow(String pkName) {
		return null;
	}
	
}
