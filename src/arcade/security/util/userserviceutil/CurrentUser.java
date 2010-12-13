package arcade.security.util.userserviceutil;

import arcade.security.util.userserviceutil.PrivilegeMap;
import arcade.security.util.DataHandler;
import java.util.HashMap;

public class CurrentUser {
	private static DataHandler userHandler = DataHandler.getInstance("User");
	private static DataHandler privilegeHandler = DataHandler.getInstance("Privileges");
	private static String currentUserName;
	private static int currentUserID;
	private static boolean isLoggedIn;
	private static String privilege;
	private static HashMap<String,Boolean> currentMap = new HashMap<String,Boolean>();
	
	
	public static void LogIn(String username){
		isLoggedIn = true;
		currentUserName = username;
		userHandler.setLoggedIn(username);
		initializePrivileges(username);
	}
	
	public static void setLoggedOut(){
		isLoggedIn = false;
		userHandler.setLoggedOut(currentUserName);
		setToDefaultPrivileges();
	}
	
	private static void setToDefaultPrivileges(){
		currentMap = new HashMap<String,Boolean>();
		privilege = PrivilegeMap.getGuestPrivilege();
	}
	
	private static void initializePrivileges(String username){
		privilege = privilegeHandler.getPrivileges(username);
		for(int i=0;i<privilege.length();i++){
			boolean isAllowed = (privilege.charAt(i)=='0')? false:true;
			String name = PrivilegeMap.getPrivilegeString(i);
			currentMap.put(name, isAllowed);
		}
	}
	
	public static boolean isLoggedIn(){
		return isLoggedIn;
		//return (userHandler.isLoggedIn(currentUserName).equals("true"));
	}
	
	public static boolean isPrivilegeAllowed(String privilegeName){
		return (currentMap.get(privilegeName));
	}
	
	public static int getCurrentUserID(){
		return currentUserID;
	}
	public static String getCurrentUserName(){
		return currentUserName;
	}
}
	
	
	
	
