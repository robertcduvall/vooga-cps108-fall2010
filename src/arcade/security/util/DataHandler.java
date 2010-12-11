package arcade.security.util;

import arcade.util.database.*;
import java.util.List;
import java.util.Map;

public class DataHandler {
	private MySqlAdapter adapter;
	private String myTable;
	public DataHandler(String tableName){
		myTable = tableName;
		adapter = new MySqlAdapter(Constants.HOST,Constants.DBNAME,Constants.USER,Constants.PASSWORD);
	}
	public void update(){
		
	}
	public void addUser(){
		
	}
	public String getPassword(int userId){
		System.out.println("Getting password for: "+userId);
		Map<String,String> row = adapter.getRows(myTable, "Id", String.valueOf(userId)).get(0);
		return row.get("Password");
	}
	public void insert(Map<String,String> row){
		adapter.insert(myTable, row);
	}
	public int getUserId(String username){
		List<Map<String, String>> rows = adapter.getRows(myTable, "UserName", username);
		if(rows == null || rows.size() == 0) return 0;
		Map<String,String> row = rows.get(0);
		return Integer.valueOf(row.get("Id"));
	}
	
}
