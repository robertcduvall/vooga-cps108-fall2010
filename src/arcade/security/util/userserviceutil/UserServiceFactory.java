package arcade.security.util.userserviceutil;


/** 
 * Extensibility and help get the UserService instance
 */
public final class UserServiceFactory {
	
	private static UserService instance = new User();
	
	private UserServiceFactory(){
		
	}
	
	public static UserService getUserService(){
		return instance;
	}
}
