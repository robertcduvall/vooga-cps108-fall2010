package arcade.lobby.model;



/**
 * Information about the current user's profile
 * is stored in this class.  All fields are
 * accessible and settable except for UserID.
 * @author david, segil
 */
public class Profile implements IModel {
	private int myUserId;
	private String myUserName="";	
	private String myFirstName="";
	private String myLastName="";
	private String myEmail="";
	private String myBirthday="";
	private String myAvatarURL="";
	private long joinDate;
	
	
	
	public Profile(int userId) {
		myUserId = userId;
	}
	
	/**
	 * @return user ID of user
	 */
	public int getUserId(){
		return myUserId;
	}
	
	/**
	 * Sets the first and last names of the user.
	 * @param first
	 * @param last
	 */
	public void setName(String first,String last) {
		myFirstName = first;
		myLastName = last;
	}
	
	/**
	 * Sets the birthday of the user.
	 * @param date
	 */
	public void setBirthday(String date) {
		myBirthday = date;
	}
	
	/**
	 * Sets the user's email.
	 * @param email
	 */
	public void setEmail(String email) {
		myEmail = email;
	}
	
	/**
	 * Sets the avatar / image representing
	 * the user from a URL.
	 * @param avatarURL
	 */
	public void setAvatar(String avatarURL) {
		myAvatarURL = avatarURL;
	}
	
	/**
	 * @return username
	 */
	public String getUserName() {
		return myUserName;
	}
	
	/**
	 * Sets the username / nickname.
	 * @param userName
	 */
	public void setUserName(String userName){
		myUserName = userName;
	}
	
	/**
	 * Gets the first and last names of the user.
	 * @return first + last names
	 */
	public String getFullName() {
		return myFirstName + " "+myLastName;
	}
	
	/**
	 * @return first name
	 */
	public String getFirstName() {
		return myFirstName;
	}
	
	/**
	 * @return last name
	 */
	public String getLastName() {
		return myLastName;
	}
	
	/**
	 * @return user's birthday
	 */
	public String getBirthday() {
		return myBirthday;
	}
	
	/**
	 * @return user's email
	 */
	public String getEmail() {
		return myEmail;
	}
	
	/**
	 * @return avatar URL
	 */
	public String getAvatar() {
		return myAvatarURL;
	}

	/**
	 * Sets the date when the user joined.  Should
	 * only be called when user is created.
	 * @param joinDate
	 */
	public void setJoinDate(Long joinDate) {
		this.joinDate = joinDate;
	}

	/**
	 * @return when user joined / was created.
	 */
	public Long getJoinDate() {
		return joinDate;
	}
	
}
