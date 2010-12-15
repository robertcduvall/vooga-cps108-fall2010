package arcade.lobby.viewComponents;

import java.awt.Color;

import javax.swing.JColorChooser;

import arcade.util.guiComponents.Validator;

public class ValidatorColor extends Validator<JColorChooser> {

	@Override
	public boolean validate() {
		return getComponent().getColor().equals(Color.BLACK);
	}

}
