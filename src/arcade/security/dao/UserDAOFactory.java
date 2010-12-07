package arcade.security.dao;

public abstract class UserDAOFactory {
	
	public static UserDAO getUserDAO(String type) {
		 
		if (type.equalsIgnoreCase("jdbc")) {
			return new UserDAOImpl();
		} else {
			return null;
			//return new UserDAOImpl();
		}
	}
}
