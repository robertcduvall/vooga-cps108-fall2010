package arcade.lobby.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;


public class MySqlAdapter implements DatabaseAdapter {
	
	private static Connection myDBConnection;

	public MySqlAdapter(String host, String dbName, String user, String pass) {
		String url = "jdbc:mysql://"+host+"/"+dbName;
		connect(url, user, pass);
	}

	private boolean connect(String host, String user, String pass){
		try {
			final String driver="com.mysql.jdbc.Driver"; 
			Class.forName(driver); //registration of the driver
			myDBConnection = DriverManager.getConnection(host, user, pass);
		} catch (Exception x) {
			System.out.println("Connection failed");
			System.out.println(x);
			return false;
		}
		return true;
	}


	@Override
	public String[] getColumn(String tableName, String columnName) {
		String sql = String.format("SELECT %s FROM %s", columnName, tableName);
		try {
			PreparedStatement ps = myDBConnection.prepareStatement(sql);
			ResultSet rs = ps.getResultSet();
			String[] result = new String[rs.getFetchSize()];
			while(rs.next()) {
				result[rs.getRow()] = rs.getString(0);
			}
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public String[] getColumn(String tableName, int columnIndex) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, String> getRow(String tableName, String pkName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean insert(String tableName, Map<String,String> row) {
		// TODO Auto-generated method stub
		String keys = "(";
		String values = "(";
		for(String s:row.keySet()){
			keys += s+",";
			values += "'"+row.get(s)+"',";
		}
		keys = keys.substring(0, keys.length()-1);
		values = values.substring(0, values.length()-1);
		keys += ")";
		values += ")";
	
		try{
			String sql = "INSERT INTO "+tableName+" "+ keys +" VALUES "+values;
			System.out.println(sql);
			PreparedStatement ps = myDBConnection.prepareStatement(sql);
			ps.executeUpdate();
			ps.close();
		}
		catch(Throwable e){
			System.out.println(e);
			return false;
		}
		return true;
	}

}
