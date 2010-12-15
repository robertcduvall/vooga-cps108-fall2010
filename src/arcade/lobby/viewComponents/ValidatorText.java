package arcade.lobby.viewComponents;

import javax.swing.JTextField;

import arcade.util.guiComponents.Validator;

public class ValidatorText extends Validator<JTextField> {

	@Override
	public boolean validate() {
		return getComponent().getText().length() > 5;
	}

}
