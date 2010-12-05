package arcade.lobby.model;

import java.util.Map;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class MySqlAdapter implements DatabaseAdapter {
	
	private static Connection myDBConnection;

	public MySqlAdapter(String host, String dbName, String user, String pass) {
		connect(host, user, pass);
		selectDB(dbName);
	}

	private boolean connect(String host, String user, String pass){
		try {
			Class.forName("oracle.jdbc.OracleDriver"); // Or any other driver
			myDBConnection = DriverManager.getConnection(host, user, pass);
		} catch (Exception x) {
			System.out.println("Connection failed");
			return false;
		}
		return true;
	}

	private boolean selectDB(String dbName) {
		return false;

	}

	@Override
	public String[] getColumn(String tableName, String columnName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] getColumn(String tableName, int columnIndex) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, String> getRow(String pkName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean insert(String[] columns, String[] values) {
		// TODO Auto-generated method stub
		return false;
	}

}
