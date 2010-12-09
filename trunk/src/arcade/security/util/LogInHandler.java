package arcade.security.util;

import arcade.util.database.DatabaseAdapter;

public class LogInHandler {
	public static DataHandler dataHandler = new DataHandler("LoginInfo");
	public static boolean userNameExists(String username){
		if(dataHandler.getAllUsers().contains(username)) return true;
		//if(username.equals("me")) return true;
		return false;
	}
	public static boolean isPasswordValid(String username,char[] password){
		///char[] validPassword = {'1','2','3'};
		String validPassword = dataHandler.getPassword(username);
		for(int i=0;i<password.length;i++){
			if(password[i]!=validPassword.charAt(i)) return false;
		}
		//if(!username.equals("me")) return false;
		return true;
	}
	public boolean isValid(String userName,String password){
		return true;
	}
}
