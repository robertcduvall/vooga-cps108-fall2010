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
		connect(host, dbName, user, pass);
	}

	private boolean connect(String host, String dbName, String user, String pass){
		try {
			final String driver="com.mysql.jdbc.Driver"; 
			Class.forName(driver); //registration of the driver
			String url = "jdbc:mysql://"+host+"/"+dbName;
			myDBConnection = DriverManager.getConnection(host, user, pass);
		} catch (Exception x) {
			System.out.println("Connection failed");
			System.out.println(x);
			return false;
		}
		testInsert();
		return true;
	}

	private void testInsert(){
		try{
			String sql = "INSERT INTO Users (User_Name,First_Name,Last_Name,Email) VALUES ('test1','test2','test3','test4')";
			PreparedStatement ps = myDBConnection.prepareStatement(sql);
			ps.executeUpdate();
			ps.close();
		}
		catch(Throwable e){
			System.out.println(e);
		}
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
		return false;
	}

}
