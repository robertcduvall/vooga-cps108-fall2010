package arcade.security.util;

import arcade.security.util.DataHandler;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.HashMap;


public class SignUpHandler {
	//potentially add another datahandler that handles the privilege table
	private static DataHandler userHandler = DataHandler.getInstance("User");
	private static DataHandler privilegeHandler = DataHandler.getInstance("Privileges");
	
	public static boolean isValidUserName(String name){
		if(name.contains(" ")) return false;
		return (userHandler.getUserId(name) < 1 );
	}
	
	public static boolean samePassword(char[] pwd_1,char[] pwd_2){
		if(pwd_1.length!=pwd_2.length) return false;
		for(int i=0;i<pwd_1.length;i++){
			if(pwd_1[i]!=pwd_2[i]) return false;
		}
		return true;
	}
	
	public static void createNewUser(String username,char[] password,int questionIndex,String questionAnswer){
		//The next line is the potentially integration with the lobby group
		//int id = ProfileSet.currentProfile.getUserId();
		Map<String,String> userRow = new LinkedHashMap<String,String>();//insertion order,does this matter?
		userRow.put("UserName",username);
		userRow.put("Password",String.valueOf(password));
		userRow.put("QuestionIndex",String.valueOf(questionIndex)); 
		userRow.put("QuestionAnswer", questionAnswer);
		userHandler.insert(userRow);
		
		//potentially use userID to sync between different tables. But since the 
		//ID is autoincrement and there is no such method that 
		//int id = ProfileSet.currentProfile.getUserId();
		int id = userHandler.getUserId(username);

		Map<String,String> privilegeRow = new HashMap<String,String>();
		privilegeRow.put("User_Id", Integer.toString(id));
		privilegeRow.put("UserName", username);
		privilegeRow.put("Privileges", "00000");
		privilegeHandler.insert(privilegeRow);
	}
	
	
}
