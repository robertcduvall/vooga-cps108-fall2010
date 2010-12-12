package arcade.store;

import arcade.lobby.model.*;
import arcade.util.database.MySqlAdapter;

import java.sql.*;
import java.util.*;


public class StoreSqlAdapter extends MySqlAdapter {
	

	private static final String host = "voogaarcade.db.7093929.hostedresource.com";
	private static final String dbName = "voogaarcade";
	private static final String user = dbName;
	private static final String pass = "Vooga108";
	private static final String credditField = "creddits";
	private static final String cartField = "cart";
	private static final String usernameField = "username";
	private static final String ownedGamesField = "owned_games";
	private static final String userTableName = "StoreUsers";

	private static Connection myDBConnection;
	
	public StoreSqlAdapter(String host, String dbName, String user, String pass) {
		super(host, dbName, user, pass);
		String url = "jdbc:mysql://" + host + "/" + dbName;
		connect(url, user, pass);
	}
	
	public StoreSqlAdapter() {
		this(host, dbName, user, pass);
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

	
	public boolean updateCreddits(double newCreddits, String user) {
		Map<String, String> row = new HashMap<String, String>();
		row.put(credditField, Double.toString(newCreddits));
		return super.update(userTableName, usernameField, user, row);
	}
	
	public boolean updateList(List<String> newCart, String user) {
		Map<String, String> row = new HashMap<String, String>();
		StringBuilder builder = new StringBuilder();
		for(String s : newCart) {
			builder.append(s);
			builder.append(",");
		}
		String newCartRow = builder.toString().substring(0, builder.length()-1);
		row.put(cartField, newCartRow);
		return super.update(userTableName, usernameField, user, row);
	}
	
}
