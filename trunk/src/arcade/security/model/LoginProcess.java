package arcade.security.model;


import arcade.security.util.LogInHandler;

public class LoginProcess implements IModel{
	
	
	public LoginProcess(){

	}
	
	public boolean isSuccessfulLogin(String username, char[] password){
		return LogInHandler.successfulLogin(username, password);
	}

	
	
}
