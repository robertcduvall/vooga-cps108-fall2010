package arcade.security.UserServiceUtil;
/**
 * UserService will take care of creating user and userService. Beta Version
 * 
 * @author Meng Li
 *
 */
public class UserService {
	
	private static User user = new User();
	
	public User getCurrentUser(){
		return user;
	}
	
	public void setUserAs(String type){
		user.setUserAs(type);
	}
}
