package arcade.security.user;

import java.util.Map;

import arcade.security.exceptions.PrivilegeNotFoundException;
import arcade.security.privileges.PrivilegeMap;

/**
 * 
 * @author Meng Li
 *
 */
public class Administrator extends AdminClass {
	
	public Administrator(){
		
	//	loadCommonPrivileges();
	//	loadSpecialPrivileges();
	}
	@Override
	public Map<String, Boolean> getPrivilegeMap(){
		return PrivilegeMap.roleMap.get("administrator");
	}	
	@Override
	public boolean getPrivilege(String accessItem){
		return PrivilegeMap.roleMap.get("administrator").get(accessItem);
	}
	
	//Admin's prime privilege
	public void setDeveloperPrivilege(String accessItem,boolean flag) throws PrivilegeNotFoundException{
		Map<String, Boolean> tempMap = PrivilegeMap.roleMap.get("developer");
		if(!tempMap.containsKey(accessItem)){
			throw new PrivilegeNotFoundException(accessItem+" is not a privilege");
		}
		else{
			tempMap.put(accessItem, flag);
		}
	}

	
	public void setGuestPrivilege(String accessItem,boolean flag) throws PrivilegeNotFoundException{
		Map<String, Boolean> tempMap = PrivilegeMap.roleMap.get("guest");
		if(!tempMap.containsKey(accessItem)){
			throw new PrivilegeNotFoundException(accessItem+" is not a privilege");
		}
		else{
			tempMap.put(accessItem, flag);
		}
	}

//	@Override
//	public abstract int compareTo(AbstractUser user);

	
}
