package arcade.lobby.model;

import java.util.Map;

public interface DatabaseAdapter {	
	
	public String[] getColumn(String tableName, String columnName);
	
	public String[] getColumn(String tableName, int columnIndex);
	
	Map<String, String> getRow(String tableName, String pkName);

	boolean insert(String tableName, Map<String, String> row);
	
}
