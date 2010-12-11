package arcade.security.util;

import arcade.lobby.model.ProfileSet;
import arcade.lobby.view.ProfilePanel;

public class LogInHandler {
	public static DataHandler dataHandler = new DataHandler("User");
	
	public static boolean successfulLogin(String username, char[] password){
		int userId = getUserId(username);
		if(userId < 1) return false;
		if(isPasswordValid(userId, password)){
			ProfileSet.setUser(userId);
			ProfileSet.currentProfile.setUserName(username);
			ProfilePanel.setProfile(ProfileSet.currentProfile);
			return true;
		}
		return false;
	}
	public static int getUserId(String username){
		return dataHandler.getUserId(username);
	}
	public static boolean isPasswordValid(int userId, char[] password){
		String validPassword = dataHandler.getPassword(userId);
		for(int i=0;i<password.length;i++){
			if(password[i]!=validPassword.charAt(i)) return false;
		}
		return true;
	}
}
