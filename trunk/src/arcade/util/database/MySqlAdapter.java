package arcade.util.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * An adapter to interface with a mySQL database
 * 
 * @author David Herzka
 * @author Andrew Brown
 * @author Justin Goldsmith
 */
public class MySqlAdapter implements DatabaseAdapter {

	private static Connection myDBConnection;
	private String myURL;
	private String myUser;
	private String myPassword;

	/**
	 * Creates a new MySqlAdapter that communicates with the specified database
	 * on the specied host
	 * 
	 * @param host
	 *            Host name
	 * @param dbName
	 *            Database name
	 * @param user
	 *            mySQL user name
	 * @param pass
	 *            Password for specified user
	 */
	public MySqlAdapter(String host, String dbName, String user, String pass) {
		String url = "jdbc:mysql://" + host + "/" + dbName;
		myURL = url;
		myUser = user;
		myPassword = pass;
		connect();
	}

	/**
	 * Connects to the specified host with the specified authentication
	 * information
	 * 
	 * @param host
	 *            Host address
	 * @param user
	 *            mySQL user name
	 * @param pass
	 *            Password for specified user
	 * @return True if connection successful, false if unsuccessful
	 */
	private boolean connect() {
		try {
			final String driver = "com.mysql.jdbc.Driver";
			Class.forName(driver); // registration of the driver
			myDBConnection = DriverManager.getConnection(myURL, myUser, myPassword);
		} catch (Exception x) {
			System.out.println("Connection failed");
			System.out.println(x);
			return false;
		}
		return true;
	}
	
	private void refreshConnection(){
		try{
			System.out.println("Checking connection");
			if(myDBConnection.isClosed()){
				connect();
				System.out.println("Connection re-established");
			}
			else{
				System.out.println("Connection was still fine");
			}
		}
		catch(SQLException x){
			System.out.println("Problem checking connection: "+x);
		}
	}
	
	private void closeConnection(){
	    if (myDBConnection != null) 
	    	try { 
	    		myDBConnection.close(); 
	    	} 
	    catch (SQLException x) {
	    	System.out.println("Problem closing connection: "+x);
	    }

	}

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
	@Override
	public List<String> getColumn(String tableName, String columnName) {
		refreshConnection();
		String sql = String.format("SELECT * FROM %s", tableName);
		PreparedStatement ps;
		List<String> result = new ArrayList<String>();
		try {
			ps = myDBConnection.prepareStatement(sql);
			ps.execute();
			ResultSet rs = ps.getResultSet();
			while (rs.next()) {
				result.add(rs.getString(columnName));
			}
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		closeConnection();
		return result;
	}

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
	@Override
	public List<Map<String, String>> getRows(String tableName, String field,
			String value) {
		Map<String, String> conditions = new HashMap<String, String>();
		conditions.put(field, value);
		return getRows(tableName, conditions);
	}

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
	@Override
	public List<Map<String, String>> getRows(String tableName,
			Map<String, String> conditions) {
		refreshConnection();
		ResultSet rs;
		Map<String, String> map;
		List<Map<String, String>> maps = new ArrayList<Map<String, String>>();
		try {
			String conditional = createConditional(conditions);
			String sql = "SELECT * FROM " + tableName + " WHERE " + conditional;
			PreparedStatement ps = myDBConnection.prepareStatement(sql);
			ps.executeQuery();
			rs = ps.getResultSet();
			while (rs.next()) {
				ResultSetMetaData rsmd = rs.getMetaData();
				int count = rsmd.getColumnCount();
				map = new HashMap<String, String>();
				for (int i = 1; i <= count; i++) {
					String curValue = rs.getString(i);
					String label = rsmd.getColumnLabel(i);
					map.put(label, curValue);
				}
				maps.add(map);
			}
			ps.close();
		} catch (Throwable e) {
			System.out.println(e);
			return null;
		}
		closeConnection();
		return maps;
	}

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
	@Override
	public boolean insert(String tableName, Map<String, String> row) {
		// TODO Auto-generated method stub
		refreshConnection();
		String keys = "(";
		String values = "(";
		for (String s : row.keySet()) {
			keys += s + ",";
			values += "'" + row.get(s) + "',";
		}
		keys = keys.substring(0, keys.length() - 1);
		values = values.substring(0, values.length() - 1);
		keys += ")";
		values += ")";

		try {
			String sql = "INSERT INTO " + tableName + " " + keys + " VALUES "
					+ values;
			PreparedStatement ps = myDBConnection.prepareStatement(sql);
			ps.executeUpdate();
			ps.close();
		} catch (Throwable e) {
			System.out.println(e);
			return false;
		}
		closeConnection();
		return true;
	}

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
	@Override
	public boolean update(String tableName, String field, String value,
			Map<String, String> row) {
		Map<String, String> conditions = new HashMap<String, String>();
		conditions.put(field, value);
		return update(tableName, conditions, row);
	}

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
	@Override
	public boolean update(String tableName, Map<String, String> conditions,
			Map<String, String> row) {
		refreshConnection();
		String newValues = "";
		for (String s : row.keySet()) {
			newValues += s + "='" + row.get(s) + "', ";
		}
		newValues = newValues.substring(0, newValues.length() - 2);

		try {
			String conditional = createConditional(conditions);
			String sql = "UPDATE " + tableName + " SET " + newValues
					+ " WHERE " + conditional;
			System.out.println(sql);
			PreparedStatement ps = myDBConnection.prepareStatement(sql);
			ps.executeUpdate();
			ps.close();
		} catch (Throwable e) {
			System.out.println(e);
			return false;
		}
		closeConnection();
		return true;
	}

	/**
	 * Creates a set of mySQL conditions from a mapping of field names to their
	 * desired values
	 */
	private String createConditional(Map<String, String> conditions) {
		String conditional = "";
		Iterator<String> it = conditions.keySet().iterator();
		while (it.hasNext()) {
			String field = it.next();
			String value = conditions.get(field);
			conditional += String.format("%s = '%s'", field, value);
			if (it.hasNext())
				conditional += " AND ";
		}
		return conditional;
	}

}
