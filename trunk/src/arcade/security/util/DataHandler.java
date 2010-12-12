package arcade.security.util;

import arcade.security.view.RetrievePasswordPanel;
import arcade.util.database.*;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

public class DataHandler {
	
	private MySqlAdapter adapter;
	private String myTable;
	private final static Logger log=Logger.getLogger(DataHandler.class);
	private static DataHandler instance;
	
	public static DataHandler getInstance(String tableName){
		instance = new DataHandler(tableName);
		return instance;
	}
	
	private DataHandler(String tableName){
		myTable = tableName;
		adapter = new MySqlAdapter(Constants.HOST,Constants.DBNAME,Constants.USER,Constants.PASSWORD);
	}
	
	public void update(){
		
	}
	
	public void addUser(){
		
	}
	
	public String getPassword(int userId){
		log.info("Getting password for: "+userId);
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
	
	public int getPasswordQuestionIndex(String username){
		List<Map<String, String>> rows = adapter.getRows(myTable, "UserName", username);
		if(rows == null || rows.size() == 0) return 0;
		Map<String,String> row = rows.get(3);
		return Integer.valueOf(row.get("Question"));
	}
	
	public String getPasswordQuestionAnswer(String username){
		List<Map<String, String>> rows = adapter.getRows(myTable, "UserName", username);
		for(int i = 0; i < rows.size();i++){
			for(String a:rows.get(i).keySet()){
				System.out.println(a+"*");
			}
		}
		if(rows == null || rows.size() == 0) return "false";
		for(int i = 0; i < rows.size();i++){
			for(String a:rows.get(i).keySet()){
				System.out.println(a);
			}
		}
		
		Map<String,String> row = rows.get(4);
		return row.get("QuestionAnswer");
	}
	
}
