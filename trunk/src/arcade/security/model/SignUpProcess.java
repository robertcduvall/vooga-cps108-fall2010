package arcade.security.model;


import java.util.Map;

import javax.swing.JTextField;

import arcade.lobby.viewComponents.ValidatorDock;
import arcade.security.util.SignUpHandler;

public class SignUpProcess implements IModel{

	public SignUpProcess(){
		
	}
	
	public boolean isSamePassword(char[] pwd_1,char[] pwd_2){
		return SignUpHandler.samePassword(pwd_1, pwd_2);
	}
	
	public boolean isValidUserName(String username){
		return SignUpHandler.isValidUserName(username);
	}
	
	public void createNewUser(String username,char[] password,int questionIndex,String questionAnwser,String firstName, String lastName,
			String email, String birthday, String avatar){
		SignUpHandler.createNewUser(username,password,questionIndex,questionAnwser,firstName,lastName,email,birthday,avatar);
	}

}
