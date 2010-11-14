package vooga.engine.resource;

import java.io.File;

import com.almworks.sqlite4java.SQLiteConnection;
import com.almworks.sqlite4java.SQLiteException;
import com.almworks.sqlite4java.SQLiteStatement;

/**
 * DBHandler is a class that allows static access to a database for reading and writing data.
 * 
 * @author David Herzka
 *
 */
public class DBHandler {
	private static SQLiteConnection dbConnection_;
	private static String table_;
	
	public static boolean open(String dbFileName) {
		dbConnection_=new SQLiteConnection(new File(dbFileName));
		try {
			dbConnection_.open(true);
		} catch (SQLiteException e) {
			return false;
		}
		return true;
	}
	
	public static boolean selectTable(String tableName,Column... columns) {
		table_=tableName;
		StringBuilder query = new StringBuilder("CREATE TABLE " + tableName + " (");
		for(Column column : columns) {
			query.append(column.getDeclaration()+",");
		}
		query.deleteCharAt(query.length()-1);
		query.append(")");
		try {
			SQLiteStatement statement = null;
			statement = dbConnection_.prepare(query.toString());
			statement.step();
		} catch (SQLiteException e) {
			return false;
		}
		return true;
	}
	
}
