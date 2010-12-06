package arcade.security.user;

import java.util.Map;

import arcade.security.privileges.PrivilegeMap;

public class Guest extends UserClass {
	
	private String myUserName;//I recommend a name mechanism to separate different users
	private String myFirstName;
	private String myLastName;
	private String myEmail;
	private String myBirthday;
	private String myAvatarURL;
	private boolean isLoggedin;
	
	
	public void setLoginState(boolean state){
		isLoggedin=state;
	}
	
	public boolean isLoggedin(){
		return isLoggedin;
	}
	
	
	public void setUserName(String userName){
		myUserName = userName;
	}
	
	public String getUserName(){
		return myUserName;
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
	
	public String getAvatarURL() {
		return myAvatarURL;
	}
	
	@Override
	public Map<String, Boolean> getPrivilegeMap(){
		return PrivilegeMap.roleMap.get("guest");
	}
	@Override
	public boolean getPrivilege(String accessItem){
		return PrivilegeMap.roleMap.get("guest").get(accessItem);
	}
}
