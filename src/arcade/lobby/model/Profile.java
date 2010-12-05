package arcade.lobby.model;

import java.text.SimpleDateFormat;
import java.util.Date;


public class Profile {
	private String myUserName;
	private String myFirstName;
	private String myLastName;
	private String myEmail;
	private Date myBirthday;
	private String myAvatarURL;
	private SimpleDateFormat mySDF = new SimpleDateFormat("yyyy-mm-dd");
	// TODO Add more fields (table columns)
	
	public Profile(String userName) {
		myUserName = userName;
	}
	
	public void setName(String first,String last) {
		myFirstName = first;
		myLastName = last;
	}
	
	public void setBirthday(Date date) {
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
		try {
			return mySDF.format(myBirthday);
		} catch(Exception e) {
			return "";
		}
	}
	
	public String getEmail() {
		return myEmail;
	}
	
	public String getAvatar() {
		return myAvatarURL;
	}
	
}
