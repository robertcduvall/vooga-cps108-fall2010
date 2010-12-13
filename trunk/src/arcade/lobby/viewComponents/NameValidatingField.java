package arcade.lobby.viewComponents;

import javax.swing.JTextField;

public class NameValidatingField extends ValidatingComponent<JTextField> {

	public NameValidatingField(JTextField component, String label) {
		super(component, label);
	}

	@Override
	public boolean validate() {
		return getComponent().getText().matches("^[A-Za-z]*$");
	}

}
