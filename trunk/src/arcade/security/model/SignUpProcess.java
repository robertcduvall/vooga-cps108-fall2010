package arcade.security.model;


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
	
	public int createNewUser(String username,char[] password,int questionIndex,String questionAnwser){
		return SignUpHandler.createNewUser(username,password,questionIndex,questionAnwser);
	}
}
