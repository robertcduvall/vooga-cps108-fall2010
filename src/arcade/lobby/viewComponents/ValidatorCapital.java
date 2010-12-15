package arcade.lobby.viewComponents;

import javax.swing.JTextField;

import arcade.util.guiComponents.Validator;

public class ValidatorCapital extends Validator<JTextField> {

	@Override
	public boolean validate() {
		return Character.isUpperCase(getComponent().getText().charAt(0));
	}

}
