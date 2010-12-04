package arcade.lobby.model;

import java.sql.Date;

public class Profile {
	private String myUserName;
	private String myFirstName;
	private String myLastName;
	private Date myBirthday;
	// TODO Add more fields (table columns)
	
	public Profile(String userName) {
		myUserName = userName;
	}
	
	public void setName(String first,String last) {
		myFirstName = first;
		myLastName = last;
	}
	

}
