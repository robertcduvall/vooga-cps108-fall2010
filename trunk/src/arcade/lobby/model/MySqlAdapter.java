package arcade.lobby.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MySqlAdapter implements DatabaseAdapter {

	private static Connection myDBConnection;

	public MySqlAdapter(String host, String dbName, String user, String pass) {
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

	@Override
	public List<String> getColumn(String tableName, String columnName) {
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
		return result;
	}

	@Override
	public List<Map<String, String>> getRows(String tableName, String field, String value) {
		// TODO Auto-generated method stub
		ResultSet rs;
		Map<String, String> map;
		List<Map<String,String>> maps = new ArrayList<Map<String,String>>();
		try {
			String sql = "SELECT * FROM " + tableName + " WHERE " + field + "='"
					+ value + "'";
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

	@Override
	public boolean insert(String tableName, Map<String, String> row) {
		// TODO Auto-generated method stub
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
		return true;
	}
	
	@Override
	public boolean update(String tableName, String pkName, Map<String,String> row){
		String newValues = "";
		for(String s:row.keySet()){
			newValues += s+"='"+row.get(s)+"', ";
		}
		newValues = newValues.substring(0, newValues.length() - 2);

		try {
			String sql = "UPDATE "+tableName+" SET "+newValues+" WHERE User_Name='"+pkName+"'";
			System.out.println(sql);
			PreparedStatement ps = myDBConnection.prepareStatement(sql);
			ps.executeUpdate();
			ps.close();
		} catch (Throwable e) {
			System.out.println(e);
			return false;
		}
		return true;
	}

}
