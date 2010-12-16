package arcade.util.database;

import java.util.List;
import java.util.Map;

/**
 * An interface for interacting with a database that abstracts out the use of
 * database queries and ResultSets.
 * 
 * @author David Herzka
 * 
 */
public interface DatabaseAdapter {

	/**
	 * Gets a column from a database table as a list of strings.
	 * 
	 * @param tableName
	 *            Name of table
	 * @param columnName
	 *            Name of field
	 * @return List of strings containing value of specified field for all rows
	 *         in table or null if the query fails
	 */
	public List<String> getColumn(String tableName, String columnName);

	/**
	 * Gets a set of rows from the database that satisfy a specified
	 * "field = 'value'" conditional
	 * 
	 * @param tableName
	 *            Name of table
	 * @param field
	 *            The name of the field to match
	 * @param value
	 *            The value of the specified field to match
	 * @return A list of maps of field names to values for each row or null if
	 *         the query fails
	 */
	List<Map<String, String>> getRows(String tableName, String field,
			String value);

	/**
	 * Gets a set of rows from the database that satisfy a specified set of
	 * "field = 'value'" conditionals
	 * 
	 * @param tableName
	 *            Name of table
	 * @param conditions
	 *            A map containing the name of each field to match with the
	 *            value desired of that field
	 * @return A list of maps of field names to values for each row or null if
	 *         the query fails
	 */
	List<Map<String, String>> getRows(String tableName,
			Map<String, String> conditions);

	/**
	 * Adds the row given by the String=>String map to the specified database
	 * table
	 * 
	 * @param tableName
	 *            Name of table
	 * @param row
	 *            Map of field names to field values that are to be inserted
	 * @return True if the query was successful, false if it failed
	 */
	boolean insert(String tableName, Map<String, String> row);

	/**
	 * Updates the rows of a database that satisfy the given condition with the
	 * specified new values
	 * 
	 * @param tableName
	 *            Name of table
	 * @param field
	 *            Name of field to match
	 * @param value
	 *            Value of field to match
	 * @param row
	 *            A row containing a mapping of the fields that will change to
	 *            their new information
	 * @return True if the query was successful, false if it failed
	 */
	boolean update(String tableName, String field, String value,
			Map<String, String> row);

	/**
	 * Updates the rows of a database that satisfy the given conditions with the
	 * specified new values
	 * 
	 * @param tableName
	 *            Name of table
	 * @param conditions
	 *            Conditions rows must satisfy in order to be updated
	 * @param row
	 *            A row containing a mapping of the fields that will change to
	 *            their new information
	 * @return True if the query was successful, false if it failed
	 */
	boolean update(String tableName, Map<String, String> conditions,
			Map<String, String> row);
	
	/**
	 * Replaces the rows of a database that satisfy the given conditions with the
	 * specified new values if they exist, if they do not exist it inserts them
	 * 
	 * @param tableName
	 *            Name of table
	 * @param row
	 *            A row containing a mapping of the fields that will change to
	 *            their new information
	 * @return True if the query was successful, false if it failed
	 */
	public boolean replace(String tableName,
			Map<String, String> row);

}
