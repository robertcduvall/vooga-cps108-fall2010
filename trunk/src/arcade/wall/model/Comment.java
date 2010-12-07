package arcade.wall.model;



public class Comment {
	private String myGameName;
	private String myUserName;
	private String myCommentString;
	// TODO Add more fields (table columns)
	
//	public Comment(String userName) {
//		this(userName, "");
//	}
	
	public Comment(String gameName, String userName, String commentString) {
		myGameName = gameName;
		myUserName = userName;
		myCommentString = commentString;
	}
	
//	public void setName(String first,String last) {
//		myFirstName = first;
//		myLastName = last;
//	}
//	
//	public void setBirthday(String date) {
//		myBirthday = date;
//	}
//	
//	public void setEmail(String email) {
//		myEmail = email;
//	}
//	
//	public void setAvatar(String avatarURL) {
//		myAvatarURL = avatarURL;
//	}
//	
	
	public String getGameName() {
		return myGameName;
	}
	
	public String getUserName() {
		return myUserName;
	}
	
	public String getCommentString() {
		return myCommentString;
	}
//	
//	public String getFullName() {
//		return myFirstName + " "+myLastName;
//	}
//	
//	public String getFirstName() {
//		return myFirstName;
//	}
//	
//	public String getLastName() {
//		return myLastName;
//	}
//	
//	public String getBirthday() {
//		return myBirthday;
//	}
//	
//	public String getEmail() {
//		return myEmail;
//	}
//	
//	public String getAvatar() {
//		return myAvatarURL;
//	}
//	

	public void setCommentString(String string) {
		this.myCommentString = string;
	}
}
