package arcade.security.util.userserviceutil;

import java.util.*;
import arcade.security.util.DataHandler;


public class PrivilegeMap {
	
	private static DataHandler mapHandler = DataHandler.getInstance("PrivilegeMap");
	private static Map<Integer,String> privilegeMap = new HashMap<Integer,String>();
	private static Map<String,Integer> privilegeIndex = new HashMap<String,Integer>();
	private static int numPrivileges;
	//temporary settings
	private static String GuestPrivilege = "000000000";
	private static String UserPrivilege = "000000111";
	private static String DeveloperPrivilege = "000111111";
	private static String AdminPrivilege = "111111111";
	
	/*private static int currentUserID;
	private static String currentUserName;
	
	private static DataHandler handler = DataHandler.getInstance("Privileges");
	private static HashMap<String,Boolean> currentMap = new HashMap<String,Boolean>();
	private static String privilege;*/
	
	//public static void update(){
	//}
	
	public static String getGuestPrivilege(){
		return GuestPrivilege;
	}
	
	public static String getUserPrivilege(){
		return UserPrivilege;
	}
	public static String getDeveloperPrivilege(){
		return DeveloperPrivilege;
	}
	public static String getAdminPrivilege(){
		return AdminPrivilege;
	}
	
	
	public static void loadMap(){
		privilegeMap = mapHandler.getPrivilegeMap();
		privilegeIndex = mapHandler.getPrivilegeIndex();
		numPrivileges = privilegeMap.size();
	}
	
	public int getNumPrivileges(){
		return numPrivileges;
	}
	
	public static void addPrivilege(String privilegeName){
		Map<String,String> row = new HashMap<String,String>();
		row.put("P_name",privilegeName);
		mapHandler.insert(row);
		//update();
		loadMap();
	}
	
	public static int getPrivilegeIndex(String pname){
		return privilegeIndex.get(pname);
	}
	
	public static String getPrivilegeString(int pid){
		return privilegeMap.get(pid);
	}
	
	
	
	
	
	
}
