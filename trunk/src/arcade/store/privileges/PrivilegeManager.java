package arcade.store.privileges;

import java.util.*;
import arcade.store.account.StoreUser;

public class PrivilegeManager {

	private static ResourceBundle userTypes = ResourceBundle.getBundle("arcade.store.privileges.usertypes");

	public boolean getPermission(StoreUser user, String permissionType) {
		String userType = user.getAccountType();
		ResourceBundle bundle = ResourceBundle.getBundle(userTypes.getString(userType));
		try {
			String booleanValue = bundle.getString(permissionType);
			return booleanValue.equals("true") ? true : false;
		}
		catch (Exception e) {
			return false;
		}
	}

}
