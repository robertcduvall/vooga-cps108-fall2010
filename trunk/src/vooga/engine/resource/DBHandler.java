package vooga.engine.resource;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;

import com.almworks.sqlite4java.SQLiteConnection;
import com.almworks.sqlite4java.SQLiteException;
import com.almworks.sqlite4java.SQLiteStatement;

/**
 * DBHandler is a class that allows static access to a database for reading and
 * writing data.
 * 
 * @author David Herzka
 * 
 */
public class DBHandler {
	private static SQLiteConnection dbConnection_;
	private static String table_;

	/**
	 * Opens a connection to the database file specified.
	 * @param dbFileName Path to database file.
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
	 * @param tableName Name of table
	 * @param columns The columns the table will have
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
	 * Creates a new table in the database with the specified name and columns of the BLOB data type
	 * @param tableName Name of table
	 * @param columnNames Names of columns
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
	 * @param tableName Name of table
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
	 * Fetches a column of strings from the database
	 * @param fieldName Name of column
	 * @return Array of column elements
	 */
	public static String[] getColumnStrings(String fieldName) {
		String query = "SELECT " + fieldName + " FROM " + table_;
		SQLiteStatement statement = null;
		Collection<String> column = new ArrayList<String>();
		try {
			statement = dbConnection_.prepare(query);
			statement.step();
			while (statement.hasRow()) {
				column.add(statement.columnString(0));
				statement.step();
			}
		} catch (SQLiteException e) {
			throw new RuntimeException(e);
		}
		return column.toArray(new String[0]);
	}

	/**
	 * Fetches a column of integers from the database
	 * @param fieldName Field name
	 * @return Array of column elements
	 */
	public static Integer[] getColumnInts(String fieldName) {
		String query = "SELECT " + fieldName + " FROM " + table_;
		SQLiteStatement statement = null;
		Collection<Integer> column = new ArrayList<Integer>();
		try {
			statement = dbConnection_.prepare(query);
			statement.step();
			while (statement.hasRow()) {
				column.add(statement.columnInt(0));
				statement.step();
			}
		} catch (SQLiteException e) {
			throw new RuntimeException(e);
		}
		return column.toArray(new Integer[0]);
	}

	/**
	 * Fetches a column of doubles from the database
	 * @param fieldName Field name
	 * @return Array of column elements
	 */
	public static Double[] getColumnDoubles(String fieldName) {
		String query = "SELECT " + fieldName + " FROM " + table_;
		SQLiteStatement statement = null;
		Collection<Double> column = new ArrayList<Double>();
		try {
			statement = dbConnection_.prepare(query);
			statement.step();
			while (statement.hasRow()) {
				column.add(statement.columnDouble(0));
				statement.step();
			}
		} catch (SQLiteException e) {
			throw new RuntimeException(e);
		}
		return column.toArray(new Double[0]);
	}

	/**
	 * Fetches a column of booleans from the database
	 * @param fieldName Field name
	 * @return Array of column elements
	 */
	public static Boolean[] getColumnBools(String fieldName) {
		String query = "SELECT " + fieldName + " FROM " + table_;
		SQLiteStatement statement = null;
		Collection<Boolean> column = new ArrayList<Boolean>();
		try {
			statement = dbConnection_.prepare(query);

			while (statement.hasRow()) {
				statement.step();
				column.add(statement.columnInt(0) != 0);
			}

		} catch (SQLiteException e) {
			throw new RuntimeException(e);
		}
		return column.toArray(new Boolean[0]);
	}

	/**
	 * Adds a row of data to the database
	 * @param entries A set of field,data pairs
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
	 * @param entries A set of field,data pairs a row must satisfy (via LIKE operator) to be deleted
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
