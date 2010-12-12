package arcade.security.model;

import arcade.security.util.DataHandler;

public class RetrievePasswordProcess implements IModel {
	
	private static DataHandler dataHandler = DataHandler.getInstance("User");
	
	public RetrievePasswordProcess(){
		
	}
	
	public boolean isAnswerMatched(String userNameInput, String userAnswerInput){
		return !(getPasswordQuestionAnswer(userNameInput).equals("false")||!getPasswordQuestionAnswer(userNameInput).equals(userAnswerInput));
	}
	
//	public int getPasswordQuestionIndex(String username){
//		return dataHandler.getPasswordQuestionIndex(username);
//	}
	
	public String getPassword(String username){
		return dataHandler.getPassword(dataHandler.getUserId(username));
	}
	
	public String getPasswordQuestionAnswer(String username){
		return dataHandler.getPasswordQuestionAnswer(username);
	}
	
}
