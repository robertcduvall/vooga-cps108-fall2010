package arcade.security.user;

import java.util.Map;
/**
 * 
 * @author Meng Li
 *
 */
public abstract class UserClass extends AbstractUser{

	public abstract Map<String, Boolean> getPrivilegeMap();

	public abstract boolean getPrivilege(String accessItem);
	
}
