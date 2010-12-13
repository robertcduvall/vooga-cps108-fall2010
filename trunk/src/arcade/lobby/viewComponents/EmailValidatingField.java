package arcade.lobby.viewComponents;

import java.util.regex.Pattern;

import javax.swing.JTextField;

public class EmailValidatingField extends ValidatingComponent<JTextField> {

	public EmailValidatingField(JTextField component, String label) {
		super(component, label);
	}

	@Override
	public boolean validate() {
		return Pattern.matches(
				"^[A-Za-z0-9\\.]+[^\\.]@([A-Za-z0-9]+\\.)+[A-Za-z]{2,6}$",
				getComponent().getText());
	}

}
