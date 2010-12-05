package arcade.lobby.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Map;
import java.sql.PreparedStatement;


public class MySqlAdapter implements DatabaseAdapter {
	
	private static Connection myDBConnection;

	public MySqlAdapter(String host, String dbName, String user, String pass) {
		connect(host, dbName, user, pass);
		selectDB(dbName);
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
