package arcade.lobby.model;



public class Profile implements IModel {
	private int myUserId;
	private String myUserName="";	
	private String myFirstName="";
	private String myLastName="";
	private String myEmail="";
	private String myBirthday="";
	private String myAvatarURL="";
	
	public Profile(int userId) {
		myUserId = userId;
	}
	
	public int getUserId(){
		return myUserId;
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
	
	public void setUserName(String userName){
		myUserName = userName;
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
