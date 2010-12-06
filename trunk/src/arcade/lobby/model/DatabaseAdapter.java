package arcade.lobby.model;

import java.util.List;
import java.util.Map;

public interface DatabaseAdapter {

	/**
	 * Gets a column from a database table as a list of strings.
	 * 
	 * @param tableName
	 *            Name of table
	 * @param columnName
	 *            Name of field
	 * @return List of strings containing value of specified field for all rows in
	 *         table
	 */
	public List<String> getColumn(String tableName, String columnName);

//	Map<String, String> getRow(String tableName, String field, String value);
	
	List<Map<String, String>> getRows(String tableName, String field, String value);

	boolean insert(String tableName, Map<String, String> row);

	boolean update(String tableName, String pkName, Map<String, String> row);

}
