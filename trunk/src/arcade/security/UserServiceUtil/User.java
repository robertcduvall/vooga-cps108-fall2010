package arcade.security.UserServiceUtil;

import java.util.Map;
import java.util.TreeMap;

public class User {
	
	private Map<String,Boolean> map = new TreeMap<String,Boolean>();
	
	public User(){
		map.put("admin", false);
		map.put("developer", false);
		map.put("login_user", false);
		
	}
//	private boolean admin = false;
//	
//	private boolean develoepr = false;
//	
//	private boolean login_user = false;
	
	public  void setUserAs(String type){//protected
		for(String role : map.keySet()){
			if(role.equals(type)){
				map.put(role, true);
			}
			else{
				map.put(role, false);
			}
		}
	}
	
	public void addNewUser(String type){
		
	}
	
	public String getRole(){
		for(String role : map.keySet()){
			if(map.get(role)==true){
				return role;
				
			}
			
		}
		return "default";
	}
}
