package arcade.security.privileges;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.TreeMap;
/**
 * This should be put in database later. 
 * @author Meng Li
 *
 */
public class PrivilegeMap {
	
	public static Map<String , HashMap<String, Boolean>> roleMap = new TreeMap<String , HashMap<String,Boolean>>();
	
	
	private final static String DEVELOPER_DEFAULT="resources.developerdefaultprivileges"; 
	private final static String GUEST_DEFAULT="resources.guestdefaultprivileges"; 
	private final static String ADMINISTRATOR_DEFAULT="resources.guestdefaultprivileges";   //we can assume admin have all the privileges

	//preload all privileges
	static{
		loadDefaultPrivileges();
	}
	
	public static void loadDefaultPrivileges(){
		HashMap<String, Boolean> map = new HashMap<String, Boolean>();
		ResourceBundle privileges = ResourceBundle.getBundle(DEVELOPER_DEFAULT);
		for(String accessItem : privileges.keySet()){
			if(privileges.getString(accessItem).equals("false")){
				map.put(accessItem, false);
			}
			else{
				map.put(accessItem, true);
			}
			
		}
		roleMap.put("developer", map);
		
		map = new HashMap<String, Boolean>();
		privileges = ResourceBundle.getBundle(GUEST_DEFAULT);
		for(String accessItem : privileges.keySet()){
			if(privileges.getString(accessItem).equals("false")){
				map.put(accessItem, false);
			}
			else{
				map.put(accessItem, true);
			}
			
		}
		roleMap.put("guest", map);
		
		map = new HashMap<String, Boolean>();
		privileges = ResourceBundle.getBundle(ADMINISTRATOR_DEFAULT);
		for(String accessItem : privileges.keySet()){
			if(privileges.getString(accessItem).equals("false")){
				map.put(accessItem, false);
			}
			else{
				map.put(accessItem, true);
			}
			
		}
		roleMap.put("administrator", map);
		
		
	}
//this method should only be accessed by administrator
//	public static void setDeveloperPrivilege(String accessItem, boolean flag){
//		
//	}
	
//	public static void main(String args[]){
//		for(String key:roleMap.keySet()){
//		System.out.println(key);
//		
//		}
//	}
}
