package arcade.security.util;

import arcade.security.util.DataHandler;
import java.util.List;
import java.util.Map;
import java.util.HashMap;


public class SignUpHandler {
	//potentially add another datahandler that handles the privilege table
	private static DataHandler dataHandler = new DataHandler("LoginInfo");
	public static boolean isValidUserName(String name){
		if(name.contains(" ")) return false;
		List<String> currentUsers = dataHandler.getAllUsers();
		return (!currentUsers.contains(name));
	}
	public static boolean samePassword(char[] pwd_1,char[] pwd_2){
		if(pwd_1.length!=pwd_2.length) return false;
		for(int i=0;i<pwd_1.length;i++){
			if(pwd_1[i]!=pwd_2[i]) return false;
		}
		return true;
	}
	public static void createNewUser(String username,char[] password,int questionIndex,String questionAnwser){
		Map<String,String> row = new HashMap<String,String>();
		row.put("UserName",username);
		row.put("Password",String.valueOf(password));
		dataHandler.insert(row);
	}
	
	
}
