package arcade.security.model;


import java.util.Map;

import javax.swing.JTextField;

import arcade.security.util.SignUpHandler;
import arcade.util.guiComponents.ValidatorDock;

/**
 * Model object for the signup panel
 * 
 * @author Meng Li, Jiaqi Yan, Nick Hawthorne
 *
 */
public class SignUpProcess implements IModel{


	/**
	 * Constructor for the signup model object
	 */
	public SignUpProcess(){
		
	}
	
	/**
	 * Checks two passwords to make sure they match
	 * 
	 * @param pwd_1 the first password
	 * @param pwd_2 the second password
	 * @return true if the passwords match
	 */
	public boolean isSamePassword(char[] pwd_1,char[] pwd_2){
		return SignUpHandler.samePassword(pwd_1, pwd_2);
	}
	
	/**
	 * Checks that a username is valid
	 * 
	 * @param username the username to check
	 * @return true if the username is valid
	 */
	public boolean isValidUserName(String username){
		return SignUpHandler.isValidUserName(username);
	}
	
	/**
	 * Creates a new user with all of their information.
	 * 
	 * @param username
	 * @param password
	 * @param questionIndex
	 * @param questionAnwser
	 * @param firstName
	 * @param lastName
	 * @param email
	 * @param birthday
	 * @param avatar
	 */
	public void createNewUser(String username,char[] password,int questionIndex,String questionAnwser,String firstName, String lastName,
			String email, String birthday, String avatar){
		SignUpHandler.createNewUser(username,password,questionIndex,questionAnwser,firstName,lastName,email,birthday,avatar);
	}

}
