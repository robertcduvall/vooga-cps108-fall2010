package arcade.lobby.viewComponents;


import javax.swing.JTextField;

public class SampleText extends ValidatingComponent<JTextField> {

	public SampleText(JTextField component, String label) {
		super(component, label);
	}

	@Override
	public boolean validate() {
		return getComponent().getText().length() >5;
	}

}
