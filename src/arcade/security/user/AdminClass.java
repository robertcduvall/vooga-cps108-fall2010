package arcade.security.user;

import java.util.Map;

public abstract class AdminClass extends AbstractUser{


	public abstract Map<String, Boolean> getPrivilegeMap();

	public abstract boolean getPrivilege(String accessItem);


}
