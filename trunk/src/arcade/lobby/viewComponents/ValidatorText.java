package arcade.lobby.viewComponents;

import javax.swing.JTextField;

public class ValidatorText extends Validator<JTextField> {

	@Override
	public boolean validate() {
		return getComponent().getText().length() > 5;
	}

}
