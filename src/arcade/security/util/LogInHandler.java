package arcade.security.util;

import arcade.core.Arcade;
import arcade.lobby.model.ProfileSet;
import arcade.lobby.view.ProfilePanel;
import arcade.security.util.userserviceutil.PrivilegeMap;
import arcade.security.util.userserviceutil.CurrentUser;
import arcade.security.util.userserviceutil.UserServiceFactory;

public class LogInHandler {
	public static DataHandler dataHandler = DataHandler.getInstance("User");
	
	public static boolean successfulLogin(String username, String password){
		int userId = getUserId(username);
		if(userId < 1) return false;
		if(isPasswordValid(userId, password)){
			//Arcade.refreshRight(); //from arcade group
			ProfileSet.setUser(userId);
			ProfileSet.getCurrentProfile().setUserName(username);
			return true;
		}
		return false;
	}
	
	public static void setCurrentUser(int userId){
		PrivilegeMap.loadMap();
		CurrentUser user = UserServiceFactory.getCurrentUser();
		//user.setAsLogined(userId);
	}
	
	
	
	public static int getUserId(String username){
		return dataHandler.getUserId(username);
	}
	
	public static boolean isAdmin(String username){
		return dataHandler.isAdmin(username).equals("Admin");
	}
	
	public static boolean isPasswordValid(int userId, String password){
		String validPassword = dataHandler.getPassword(userId);
		PasswordHasher hasher = new PasswordHasher();
		String hashedPassword = hasher.encrypt(String.valueOf(password));
		return validPassword.compareTo(hashedPassword) == 0;
	}
}
