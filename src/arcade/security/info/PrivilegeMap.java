package arcade.security.info;

import java.util.*;
import arcade.security.util.DataHandler;


public class PrivilegeMap {
	
	private static int currentUserID;
	private static String currentUserName;
	
	private static DataHandler handler = DataHandler.getInstance("Privileges");
	private static HashMap<String,Boolean> currentMap = new HashMap<String,Boolean>();
	private static String privilege;
	
	public static void setCurrentUser(String username){
		//currentUserID = userID;
		currentUserName = username;
		loadMap();
	}
	
	
	//public static void initialize(String username){
	//	loadMap();
	//}
	
	public static String getPrivilegeString(){
		return privilege;
	}	
	
	private static void loadMap(){
		privilege =  handler.getPrivileges(currentUserName);
		//System.out.println(s);
	}
	
	
	
	
}
