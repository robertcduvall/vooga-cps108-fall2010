package arcade.security.util;

import java.util.Map;

public class AdminHandler {
	public static DataHandler userHandler = DataHandler.getInstance("User");
	public Map<String,String> getUserTypeMap(){
		return userHandler.getUserTypeMap();
	}

}
