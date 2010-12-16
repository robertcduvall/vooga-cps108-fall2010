package arcade.security.util;

import arcade.core.ArcadeTab;
import arcade.lobby.model.ProfileSet;
import arcade.security.util.userserviceutil.CurrentUser;
import arcade.security.util.userserviceutil.PrivilegeMap;

public class LogInHandler {
	public static DataHandler dataHandler = DataHandler.getInstance("User");
	
	public static boolean successfulLogin(String username, String password){
		int userId = getUserId(username);
		if(userId < 1) return false;
		if(isPasswordValid(userId, password)){
			ProfileSet.setUser(userId);
			ArcadeTab.refreshRight(); //from arcade group
			return true;
		}
		return false;
	}
	
	public static void setCurrentUser(String username){
		PrivilegeMap.loadMap();
		CurrentUser.LogIn(username);
	}
	
	
	
	public static int getUserId(String username){
		return dataHandler.getUserId(username);
	}
	
	public static boolean isAdmin(String username){
		return dataHandler.isAdmin(username).equals("Admin");
	}
	
	public static boolean isPasswordValid(int userId, String enteredPassword){
		String realPassword = dataHandler.getPassword(userId);
		PasswordHasher hasher = new PasswordHasher();
		enteredPassword = hasher.encrypt(enteredPassword);
		return enteredPassword.compareTo(realPassword) == 0;
	}
}
