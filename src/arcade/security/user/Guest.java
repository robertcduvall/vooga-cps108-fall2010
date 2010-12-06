package arcade.security.user;

import java.util.Map;

import arcade.security.privileges.PrivilegeMap;

public class Guest extends UserClass {
	@Override
	public Map<String, Boolean> getPrivilegeMap(){
		return PrivilegeMap.roleMap.get("guest");
	}
	@Override
	public boolean getPrivilege(String accessItem){
		return PrivilegeMap.roleMap.get("guest").get(accessItem);
	}
}
