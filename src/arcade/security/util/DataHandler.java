package arcade.security.util;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import arcade.util.database.Constants;
import arcade.util.database.MySqlAdapter;

/**
 * This class handles all the direct interactions with the database
 * @author Jiaqi Yan, Meng Li
 *
 */
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
	
	public String getPrivileges(int userId){
		return adapter.getRows(myTable,"User_Id", String.valueOf(userId)).get(0).get("Privileges");
	}
	
	public String getPrivileges(String username){
		return adapter.getRows(myTable,"UserName", username).get(0).get("Privileges");
	}
	
	public String getPassword(int userId){
		log.info("Getting password for: "+userId);
		Map<String,String> row = adapter.getRows(myTable, "Id", String.valueOf(userId)).get(0);
		return row.get("Password");
	}
	
	public void insert(Map<String,String> row){
		adapter.insert(myTable, row);
//		List<String> ids = adapter.getColumn(myTable, "Id");
//		Collections.sort(ids,new Comparator<String>() {
//
//			@Override
//			public int compare(String o1, String o2) {
//				if(Integer.parseInt(o1)==Integer.parseInt(o2))
//					return 0;
//				if(Integer.parseInt(o1)<Integer.parseInt(o2))
//					return 1;
//				return -1;
//			}
//			
//		});
//		return Integer.parseInt(ids.get(0));
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
		Map<String,String> row = rows.get(0);
		return Integer.valueOf(row.get("QuestionIndex"));
	}
	
	public String getPasswordQuestionAnswer(String username){
		List<Map<String, String>> rows = adapter.getRows(myTable, "UserName", username);
		if(rows == null || rows.size() == 0) return "false";	
		Map<String,String> row = rows.get(0);
		return row.get("QuestionAnswer");
	}
	
}
