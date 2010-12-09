package arcade.security.UserServiceUtil;

/** 
 * Extensibility and help get the UserService instance
 */
public final class UserServiceFactory {
	
	private static UserService instance = new UserService();
	
	private UserServiceFactory(){
		
	}
	
	public static UserService getUserService(){
		return instance;
	}
}
