package arcade.security.user;

import java.util.Map;
/**
 * 
 * @author Meng Li
 *
 */
public abstract class AbstractUser {//implements Comparable<AbstractUser>{


	public abstract Map<String, Boolean> getPrivilegeMap();

	public abstract boolean getPrivilege(String accessItem);

}
