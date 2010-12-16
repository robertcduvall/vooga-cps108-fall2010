package arcade.lobby.validators;

import javax.swing.JTextField;

import arcade.security.util.SignUpHandler;
import arcade.util.guiComponents.Validator;

/**
 * Validation class to make sure the username
 * field contains a unique username.
 * @author the lobbyists
 */
public class UsernameValidator extends Validator<JTextField> {

	@Override
	public boolean validate() {
		return SignUpHandler.isValidUserName(getComponent().getText());
	}

}
