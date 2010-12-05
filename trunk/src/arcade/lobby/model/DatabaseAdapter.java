package arcade.lobby.model;

import java.sql.ResultSet;

public interface DatabaseAdapter {	
	
	public ResultSet getColumn(String tableName, String columnName);
	
	public ResultSet getColumn(String tableName, int columnIndex);
	
	public ResultSet getRow(String pkName);

	public boolean insert(String[] columns, String[] values);
	
}
