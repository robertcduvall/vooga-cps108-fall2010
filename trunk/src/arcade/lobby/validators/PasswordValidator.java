package arcade.lobby.validators;

import javax.swing.JPasswordField;

import arcade.util.guiComponents.Validator;

/**
 * Validation class to make sure passwords are strong,
 * and it checks if the 1st and 2nd password fields match.
 * @author the lobbyists
 */
public class PasswordValidator extends Validator<JPasswordField> {

	private JPasswordField[] myPasswordFields = new JPasswordField[2];
	private int mySpot = 0;
	
	
	@Override
	protected void setComponent(JPasswordField component){
			myPasswordFields[mySpot] = component;
			changeSpot();
	};
	
	
	@Override
	public boolean validate() {
		boolean toReturn = false;
		if(mySpot == 0){
			 toReturn = validateFirst();
			 changeSpot();
		}else{
			toReturn = validateSecond();
			changeSpot();
		}
		return toReturn;
	}
	
	private boolean validateSecond() {
		return String.valueOf(myPasswordFields[0].getPassword()).equals(String.valueOf(myPasswordFields[1].getPassword()));
	}


	private boolean validateFirst() {
		boolean digit = false;
		boolean letter = false;
		
		for(char c : myPasswordFields[0].getPassword()){
			if(Character.isDigit(c)){
				digit = true;
			}
			if(Character.isLetter(c)){
				letter = true;
			}
		}
		
		return digit && letter;
	}


	private void changeSpot(){
		if(mySpot == 0){
			mySpot = 1;
		}else{
			mySpot = 0;
		}
	}

}
