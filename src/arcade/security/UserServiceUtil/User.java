package arcade.security.UserServiceUtil;

import java.util.Map;
import java.util.TreeMap;
/**
 * Beta Version
 * @author Meng Li
 *
 */
public class User implements UserService{  //or get rid of this user class, just use userId
	
	private Map<String,Boolean> map = new TreeMap<String,Boolean>();
	
	protected User(){
		map.put("admin", false);
		map.put("developer", false);
		map.put("login_user", false);
		map.put("default", true);
		
	}
	
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
	
	public boolean isLoggedin(){
		return !map.get("default");
	}
	
	
	@Override
	public User getCurrentUser() {   //return a user or userId?? Discussion
		return this;
	}

	@Override
	public void getUserId() {
		// TODO Auto-generated method stub
		
	}

}
