package arcade.security.util.userserviceutil;


/** 
 * Extensibility and help get the UserService instance
 */
public final class UserServiceFactory {
	
	private static CurrentUser instance = new CurrentUser();
	private static PrivilegeMap map = new PrivilegeMap();
	
	private UserServiceFactory(){
		
	}
	
	public static CurrentUser getCurrentUser(){
		return instance;
	}
	
	public static PrivilegeMap getPrivilegeMap(){
		return map;
	}
}
