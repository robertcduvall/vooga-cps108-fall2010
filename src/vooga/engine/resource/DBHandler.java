package vooga.engine.resource;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.almworks.sqlite4java.SQLiteConnection;
import com.almworks.sqlite4java.SQLiteException;
import com.almworks.sqlite4java.SQLiteStatement;

/** 
 * DBHandler is a class that allows static access to a database for reading and
 * writing data.
 * 
 * The DBHandler class allows for basic interactions with an SQLite database.
 * Currently, its functionality includes creating tables, fetching columns, and
 * adding and deleting table rows. Different types of data can be stored in the
 * database columns as dictated by the built-in data type constants in the
 * Column class. As SQLite is loosely typed, it is usually fine to use the
 * "NONE" data type for all columns.
 * 
 * @author David G. Herzka
 * 
 */
public class DBHandler {
	// TODO make non-static
	
	
	private static SQLiteConnection dbConnection_;
	private static String table_;

	/**
	 * Opens a connection to the database file specified.
	 * 
	 * @param dbFileName
	 *            Path to database file.
	 * @return true if operation successful
	 */
	public static boolean open(String dbFileName) {
		dbConnection_ = new SQLiteConnection(new File(dbFileName));
		try {
			dbConnection_.open(true);
		} catch (SQLiteException e) {
			throw new RuntimeException(e);
		}
		return true;
	}

	/**
	 * Creates a new table in the database with the specified name and columns
	 * 
	 * @param tableName
	 *            Name of table
	 * @param columns
	 *            The columns the table will have
	 * @return true if operation successful
	 */
	public static boolean createTable(String tableName, Column... columns) {
		StringBuilder query = new StringBuilder("CREATE TABLE " + tableName
				+ " (");
		for (Column column : columns) {
			query.append(column.getDeclaration() + ",");
		}
		query.deleteCharAt(query.length() - 1);
		query.append(")");
		try {
			SQLiteStatement statement = dbConnection_.prepare(query.toString());
			statement.step();
		} catch (SQLiteException e) {
			return false;
		}
		return true;
	}

	/**
	 * Creates a new table in the database with the specified name and columns
	 * of the BLOB data type
	 * 
	 * @param tableName
	 *            Name of table
	 * @param columnNames
	 *            Names of columns
	 * @return true if operation successful
	 */
	public static boolean createTable(String tableName, String... columnNames) {
		Column[] columns = new Column[columnNames.length];
		for (int i = 0; i < columnNames.length; i++) {
			columns[i] = new Column(columnNames[i], Column.NONE);
		}
		return createTable(tableName, columns);
	}

	/**
	 * Sets the active table on which other operations are performed.
	 * 
	 * @param tableName
	 *            Name of table
	 * @return true if operation successful
	 */
	public static boolean setTable(String tableName) {
		String query = "SELECT name FROM sqlite_master WHERE name = '"
				+ tableName + "'";
		SQLiteStatement statement = null;
		try {
			statement = dbConnection_.prepare(query);
			statement.step();
		} catch (SQLiteException e) {
			throw new RuntimeException(e);
		}
		if (!statement.hasRow())
			return false;
		table_ = tableName;
		return true;
	}

	/**
	 * Retrieves a column of information from the database as strings
	 * 
	 * @param fieldName
	 *            Name of field to fetch
	 * @return A list containing the contents of the column
	 */
	public static List<String> getColumn(String fieldName) {
		return getColumn(fieldName, new String());
	}

	/**
	 * Retrieves a column of information from the database in the specified
	 * format
	 * 
	 * @param <T>
	 *            A class which extends Integer, String, Double, Boolean
	 * @param fieldName
	 *            Name of field to fetch
	 * @param t
	 *            An object of the class of the desired output
	 * @return A list containing the contents of the column
	 */
	public static <T> List<T> getColumn(String fieldName, T t) {
		String query = "SELECT " + fieldName + " FROM " + table_;
		SQLiteStatement statement = null;
		List<T> column = new ArrayList<T>();
		try {
			statement = dbConnection_.prepare(query);
			statement.step();
			while (statement.hasRow()) {
				T columnElement = getColumnElement(statement, t);
				column.add(columnElement);
				statement.step();
			}
		} catch (SQLiteException e) {
			throw new RuntimeException(e);
		}
		return column;
	}

	@SuppressWarnings("unchecked")
	private static <T> T getColumnElement(SQLiteStatement statement, T t)
			throws SQLiteException {
		if (t instanceof Double)
			return (T) (Double) Double.parseDouble(statement.columnString(0));
		if (t instanceof Integer)
			return (T) (Integer) Integer.parseInt(statement.columnString(0));
		if (t instanceof String)
			return (T) statement.columnString(0);
		if (t instanceof Boolean)
			return (T) (Boolean) new Boolean(Integer.parseInt(statement
					.columnString(0)) != 0);
		throw new RuntimeException(t.getClass().getName()
				+ "is not a valid type");
	}

	/**
	 * Adds a row of data to the database
	 * 
	 * @param entries
	 *            A set of field,data pairs
	 * @return true if operation was successful
	 */
	public static boolean addRow(Entry<?>... entries) {
		StringBuilder columnNames = new StringBuilder("");
		StringBuilder columnValues = new StringBuilder("");
		for (Entry<?> e : entries) {
			columnNames.append(e.getFieldName() + ",");
			columnValues.append("'" + e.getData().toString() + "',");
		}
		columnNames.deleteCharAt(columnNames.length() - 1);
		columnValues.deleteCharAt(columnValues.length() - 1);

		String query = "INSERT INTO " + table_ + "(" + columnNames.toString()
				+ ") values(" + columnValues + ")";
		try {
			dbConnection_.prepare(query).step();
		} catch (SQLiteException e) {
			throw new RuntimeException(e);
		}
		return true;
	}

	/**
	 * Deletes a row of data from the database
	 * 
	 * @param entries
	 *            A set of field,data pairs a row must satisfy (via LIKE
	 *            operator) to be deleted
	 * @return true if operation was successful
	 */
	public static boolean deleteRow(Entry<?>... entries) {
		StringBuilder conditions = new StringBuilder("");
		for (Entry<?> e : entries) {
			conditions.append(e.getFieldName() + " LIKE '"
					+ e.getData().toString() + "' AND");
		}
		conditions.delete(conditions.length() - 4, conditions.length());
		String query = "DELETE FROM " + table_ + " WHERE "
				+ conditions.toString();
		try {
			dbConnection_.prepare(query).step();
		} catch (SQLiteException e) {
			throw new RuntimeException(e);
		}
		return true;
	}

}
