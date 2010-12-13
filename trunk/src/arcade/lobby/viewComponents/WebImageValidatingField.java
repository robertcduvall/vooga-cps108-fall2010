package arcade.lobby.viewComponents;

import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JTextField;

public class WebImageValidatingField extends ValidatingComponent<JTextField> {

	public WebImageValidatingField(JTextField component, String label) {
		super(component, label);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean validate() {
		if(getComponent().getText().isEmpty()) return true;
		try {
			new ImageIcon(ImageIO.read(new URL(getComponent().getText())));
		} catch (Exception e) {
			return false;
		}
		return true;
	}

}
