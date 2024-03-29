package arcade.lobby.validators;

import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JTextField;

import arcade.util.guiComponents.Validator;

/**
 * Validates the Avatar URL.  Does nothing if the
 * URL is not a valid image.
 * @author the lobbyists
 */
public class WebImageValidator extends Validator<JTextField> {

	@Override
	public boolean validate() {
		if (getComponent().getText().isEmpty())
			return true;
		try {
			new ImageIcon(ImageIO.read(new URL(getComponent().getText())));
		} catch (Exception e) {
			return false;
		}
		return true;
	}

}
