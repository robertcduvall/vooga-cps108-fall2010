package arcade.lobby.validators;

import javax.swing.JTextField;

import arcade.lobby.viewComponents.Validator;

public class NameValidator extends Validator<JTextField> {

	@Override
	public boolean validate() {
		return getComponent().getText().matches("^[A-Za-z]*$");
	}

}
