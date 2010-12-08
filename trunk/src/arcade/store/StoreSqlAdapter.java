package arcade.store;

import arcade.lobby.model.*;
import arcade.util.database.MySqlAdapter;

import java.sql.*;
import java.util.*;


public class StoreSqlAdapter extends MySqlAdapter {

	private static Connection myDBConnection;
	
	public StoreSqlAdapter(String host, String dbName, String user, String pass) {
		super(host, dbName, user, pass);
		String url = "jdbc:mysql://" + host + "/" + dbName;
		connect(url, user, pass);
	}
	
	private boolean connect(String host, String user, String pass) {
		try {
			final String driver = "com.mysql.jdbc.Driver";
			Class.forName(driver); // registration of the driver
			myDBConnection = DriverManager.getConnection(host, user, pass);
		} catch (Exception x) {
			System.out.println("Connection failed");
			System.out.println(x);
			return false;
		}
		return true;
	}
	
	public List<Map<String, String>> getAllRows(String tableName) {
		ResultSet rs;
		Map<String, String> map;
		List<Map<String,String>> maps = new ArrayList<Map<String,String>>();
		try {
			String sql = "SELECT * FROM " + tableName;
			PreparedStatement ps = myDBConnection.prepareStatement(sql);
			ps.executeQuery();
			rs = ps.getResultSet();
			while(rs.next()) {
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
		return maps;
	}

}
