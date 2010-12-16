package arcade.lobby.validators;

import javax.swing.JTextField;

import arcade.util.guiComponents.Validator;

/**
 * Validation class for names (first and last)
 * @author the lobbyists
 */
public class NameValidator extends Validator<JTextField> {

	@Override
	public boolean validate() {
		return getComponent().getText().matches("^[A-Za-z]*$");
	}

}
