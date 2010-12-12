package arcade.security.model;


import arcade.security.util.LogInHandler;
import arcade.security.util.SignUpHandler;

public class LoginProcess implements IModel{
	
	//private Control controller;
	
//	public LoginProcess(Control controller){
//		this.controller = controller;
//	}
	
	public LoginProcess(){

	}
	
	public boolean isSuccessfulLogin(String username, char[] password){
		return LogInHandler.successfulLogin(username, password);
	}

	
	
}
