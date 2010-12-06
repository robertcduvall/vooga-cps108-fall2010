package arcade.lobby.model;

import java.util.Map;

public interface DatabaseAdapter {	
	
	public String[] getColumn(String tableName, String columnName);
		
	Map<String, String> getRow(String tableName, String pkName);

	boolean insert(String tableName, Map<String, String> row);
	
	boolean update(String tableName, String pkName, Map<String,String> row);
	
}
