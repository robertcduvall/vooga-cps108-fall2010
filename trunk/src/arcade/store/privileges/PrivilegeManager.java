package arcade.store.privileges;

import java.util.*;
import arcade.store.account.StoreUser;

public class PrivilegeManager {
	
	private static ResourceBundle userTypes = ResourceBundle.getBundle("arcade.store.privileges.UserTypes");
	
	public boolean getPermission(StoreUser user, String permissionType) {
		String userType = user.getAccountType();
		ResourceBundle bundle = ResourceBundle.getBundle(userTypes.getString(userType));
		return bundle.getString(permissionType).equals("true") ? true : false;
	}
	
}
