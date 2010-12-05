package arcade.lobby.model;

import java.sql.Date;

public class Profile {
	private String myUserName;
	private String myFirstName;
	private String myLastName;
	private String myEmail;
	private Date myBirthday;
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
	
	public String getName() {
		return myFirstName + " "+myLastName;
	}
	
	public String getBirthday() {
		return myBirthday.toString();
	}
	
	public String getEmail() {
		return myEmail;
	}
	

}
