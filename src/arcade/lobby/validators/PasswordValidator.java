package arcade.lobby.validators;

import arcade.lobby.viewComponents.Validator;

public class PasswordValidator extends Validator<PasswordConfirmField> {

	@Override
	public boolean validate() {
		return new String(getComponent().getPassword1()).equals(new String(getComponent().getPassword2()));
	}

}