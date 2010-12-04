package vooga.games.grandius.sprites.buttons;

import java.awt.image.BufferedImage;

import vooga.engine.core.Game;
import vooga.engine.resource.Resources;
import vooga.widget.Button;

@SuppressWarnings("serial")
public class BuyBlackHoleButton extends Button {

	private static final int START_X = 20;
	private static final int START_Y = 200;
	private static final BufferedImage myImage = Resources.getImage("buyBlackHoleButtonImage");
	
	public BuyBlackHoleButton() {
		super(myImage, START_X, START_Y);
	}

	@Override
	public void actionPerformed() {
		System.out.println("Black hole button activated!");

	}

}
