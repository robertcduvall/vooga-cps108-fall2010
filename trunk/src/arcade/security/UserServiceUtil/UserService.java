package arcade.security.UserServiceUtil;

public class UserService {
	
	private static User user = new User();
	
	public User getCurrentUser(){
		return user;
	}
	
	public void setUserAs(String type){
		user.setUserAs(type);
	}
}
