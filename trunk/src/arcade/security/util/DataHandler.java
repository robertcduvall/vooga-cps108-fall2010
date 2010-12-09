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
	public String getPassword(String username){
		Map<String,String> row = adapter.getRows(myTable, "UserName", username).get(0);
		return row.get("Password");
	}
	public void insert(Map<String,String> row){
		adapter.insert(myTable, row);
	}
	public List<String> getAllUsers(){
		return adapter.getColumn(myTable, "UserName");
	}
	
}
