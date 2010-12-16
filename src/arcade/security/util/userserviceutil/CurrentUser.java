package arcade.security.util.userserviceutil;

import arcade.lobby.model.Profile;
import arcade.lobby.model.ProfileSet;
import arcade.security.util.userserviceutil.PrivilegeMap;
import arcade.security.util.DataHandler;
import java.util.HashMap;

public class CurrentUser implements IUser{
	
	private DataHandler userHandler = DataHandler.getInstance("User");
	private DataHandler privilegeHandler = DataHandler.getInstance("Privileges");
	private String currentUserName = "guest";  //default value
	private int currentUserID = 0;  //default value
	private boolean isLoggedIn;
	private String privilege;
	private HashMap<String,Boolean> currentMap = new HashMap<String,Boolean>();
	private static Profile profile = ProfileSet.getCurrentProfile();
	private String userType = "guest";
	//remove all the static 
	public void setAsLogined(int userId){
		profile = ProfileSet.getCurrentProfile();
		isLoggedIn = true;
		currentUserName = profile.getUserName();
		currentUserID = userId;//profile.getUserId();
		userType = "loginuser";////TODO:
		userHandler.setLoggedIn(currentUserID);
		initializePrivileges(currentUserID);
		
	}
	

	public void setLoggedOut(){
		isLoggedIn = false;
		userHandler.setLoggedOut(currentUserName);
		setToDefaultPrivileges();
	}
	
	private void setToDefaultPrivileges(){
		currentMap = new HashMap<String,Boolean>();
		privilege = PrivilegeMap.getGuestPrivilege();
	}
	
	private void initializePrivileges(int userId){
		privilege = privilegeHandler.getPrivileges(userId);
		for(int i=0;i<privilege.length();i++){
			boolean isAllowed = (privilege.charAt(i)=='0')? false:true;
			String name = PrivilegeMap.getPrivilegeString(i);
			currentMap.put(name, isAllowed);
		}
	}
	
	public boolean isLoggedIn(){
		return isLoggedIn;
		//return (userHandler.isLoggedIn(currentUserName).equals("true"));
	}
	
	public String getUserType(){
		return userType;
		//return userHandler.getUserType(userId);
	}
	
	public boolean isPrivilegeAllowed(String privilegeName){
		return (currentMap.get(privilegeName));
	}
	
	public int getUserID(){
		return currentUserID;
	}
	public String getUserName(){
		return currentUserName;
	}
	
	//**from Profile class
	
	public void setName(String first,String last) {
		profile.setName(first, last);

	}
	
	public void setBirthday(String date) {
		profile.setBirthday(date);
	}
	
	public void setEmail(String email) {
		profile.setEmail(email);
	}
	
	public void setAvatar(String avatarURL) {
		profile.setAvatar(avatarURL);
	}
	
	
	
	public void setUserName(String userName){
		profile.setUserName(userName);
	}
	
	public String getFullName() {
		return profile.getFullName();
	}
	
	public String getFirstName() {
		return profile.getFirstName();
	}
	
	public String getLastName() {
		return profile.getLastName();
	}
	
	public String getBirthday() {
		return profile.getBirthday();
	}
	
	public String getEmail() {
		return profile.getEmail();
	}
	
	public String getAvatar() {
		return profile.getAvatar();
	}

	public void setJoinDate(Long joinDate) {
		profile.setJoinDate(joinDate);
	}

	public Long getJoinDate() {
		return profile.getJoinDate();
	}
	
	
}
	
	
	
	
