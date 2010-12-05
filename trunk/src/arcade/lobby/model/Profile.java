package arcade.lobby.model;

import java.text.DateFormat;
import java.util.Date;

import arcade.lobby.controller.Validator;


public class Profile {
	private String myUserName;
	private String myFirstName;
	private String myLastName;
	private String myEmail;
	private String myBirthday;
	private String myAvatarURL;
	private DateFormat mySDF = Validator.getDateFormat();
	// TODO Add more fields (table columns)
	
	public Profile(String userName) {
		myUserName = userName;
	}
	
	public void setName(String first,String last) {
		myFirstName = first;
		myLastName = last;
	}
	
	public void setBirthday(String date) {
		myBirthday = date;
	}
	
	public void setEmail(String email) {
		myEmail = email;
	}
	
	public void setAvatar(String avatarURL) {
		myAvatarURL = avatarURL;
	}
	
	public String getUserName() {
		return myUserName;
	}
	
	public String getFullName() {
		return myFirstName + " "+myLastName;
	}
	
	public String getFirstName() {
		return myFirstName;
	}
	
	public String getLastName() {
		return myLastName;
	}
	
	public String getBirthday() {
		return myBirthday;
	}
	
	public String getEmail() {
		return myEmail;
	}
	
	public String getAvatar() {
		return myAvatarURL;
	}
	
}
