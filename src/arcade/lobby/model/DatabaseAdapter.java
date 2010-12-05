package arcade.lobby.model;

import java.util.Map;

public interface DatabaseAdapter {	
	
	public String[] getColumn(String tableName, String columnName);
	
	public String[] getColumn(String tableName, int columnIndex);
	
	public Map<String,String> getRow(String pkName);

	public boolean insert(String[] columns, String[] values);
	
}
