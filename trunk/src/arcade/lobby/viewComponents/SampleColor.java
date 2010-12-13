package arcade.lobby.viewComponents;


import java.awt.Color;

import javax.swing.JColorChooser;
import javax.swing.JTextField;

public class SampleColor extends ValidatingComponent<JColorChooser> {

	public SampleColor(JColorChooser component, String label) {
		super(component, label);
	}

	@Override
	public boolean validate() {
		return getComponent().getColor().equals(Color.BLACK);
	}

}