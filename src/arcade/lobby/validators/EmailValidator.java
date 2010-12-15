package arcade.lobby.validators;

import javax.swing.JTextField;

import arcade.util.guiComponents.Validator;

public class EmailValidator extends Validator<JTextField> {

	@Override
	public boolean validate() {
		if (getComponent().getText().isEmpty())
			return true;
		return getComponent().getText().matches(
				"^[A-Za-z0-9\\.]+[^\\.]@([A-Za-z0-9]+\\.)+[A-Za-z]{2,6}$");
	}

}
