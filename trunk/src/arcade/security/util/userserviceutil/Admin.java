package arcade.security.util.userserviceutil;

import java.util.*;

import arcade.security.util.userserviceutil.PrivilegeMap;
import arcade.security.util.DataHandler;

public class Admin {
	private static DataHandler userHandler = DataHandler.getInstance("User");
	private static DataHandler privilegeHandler = DataHandler.getInstance("Privileges");
	
	private static List<String> loggedInUsers = new ArrayList<String>();
	private static char[] currentPrivileges;
	//private static Map<String,String> currentUserPrivileges = new HashMap<String,String>();
	
	public static void refreshInfo(){
		loggedInUsers = userHandler.getLoggedInUsers();
	}
	
	public static void allowAccess(String username,String privilegeName){
		int index = PrivilegeMap.getPrivilegeIndex(privilegeName);
		currentPrivileges[index] = '1';
		privilegeHandler.setUserPrivilege(username,privilegeName,currentPrivileges.toString());
	}
	
	public static void denyAccess(String username,String privilegeName){
		privilegeHandler.setUserPrivilege(username,privilegeName,"false");
	}
	
	public static boolean hasAccess(String username,String privilegeName){
		loadUserPrivileges(username);
		int index = PrivilegeMap.getPrivilegeIndex(privilegeName);
		return (currentPrivileges[index]=='1');
	}
	
	private static void loadUserPrivileges(String username){
		currentPrivileges = privilegeHandler.getPrivileges(username).toCharArray();
	}
	
	
	public static void kickOutUser(String username){
		userHandler.setLoggedOut(username);
	}
	
	public static List<String> getAllLoggedInUsrs(){
		return loggedInUsers;
	}
	
	
	
	
}
