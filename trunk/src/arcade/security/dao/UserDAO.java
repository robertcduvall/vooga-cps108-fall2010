package arcade.security.dao;

import arcade.security.user.AbstractUser;

public interface UserDAO {
	
	  public boolean insertUser(AbstractUser user);
	  
	  public boolean deleteUser(String userName);//delete by primary key

	  public AbstractUser getUser(String userName);  //get by primary key


}
