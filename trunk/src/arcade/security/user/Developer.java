package arcade.security.user;

import java.util.Map;

import arcade.security.privileges.PrivilegeMap;

public class Developer extends AdminClass {
	
	@Override
	public Map<String, Boolean> getPrivilegeMap(){
		return PrivilegeMap.roleMap.get("developer");
	}
	@Override
	public boolean getPrivilege(String accessItem){
		return PrivilegeMap.roleMap.get("developer").get(accessItem);
	}
}
