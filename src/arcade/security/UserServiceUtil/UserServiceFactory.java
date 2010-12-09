package arcade.security.UserServiceUtil;

public final class UserServiceFactory {
	
	private static UserService instance = new UserService();
	
	private UserServiceFactory(){
		
	}
	
	public static UserService getUserService(){
		return instance;
	}
}
