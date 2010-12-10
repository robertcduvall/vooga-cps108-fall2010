package arcade.security.util.userserviceutil;


/**
 * Beta Version
 * 
 * @author Meng Li
 *
 */
public abstract interface UserService {
	
	
	
	public User getCurrentUser();
	
	public void setUserAs(String type);
	
	public void getUserId();

}
