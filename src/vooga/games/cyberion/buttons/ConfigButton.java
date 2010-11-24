package vooga.games.cyberion.buttons;

import java.awt.image.BufferedImage;

import vooga.engine.core.Game;
import vooga.engine.resource.Resources;
import vooga.widget.Button;

/**
 * Config button. Move into Configuration menu.
 * 
 * @author Harris.He
 * 
 */

public class ConfigButton extends Button {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int START_X = 220;
	private static final int START_Y = 0;
	private static final BufferedImage myImage = Resources
			.getImage("playButtonImage");

	public ConfigButton() {
		super(myImage, START_X, START_Y);
	}

	public void actionPerformed() {
		System.out.println("Config button activated!");
		this.setActive(false);

	}
}
