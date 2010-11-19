package vooga.games.grandius.buttons;

import vooga.engine.core.Game;
import vooga.engine.resource.Resources;
import vooga.widget.Button;

public class BuyBlackHoleButton extends Button {

	public BuyBlackHoleButton(Game game) {
		super(game);
		this.setImage(Resources.getImage("BuyBlackHoleButton"));
	}

	@Override
	public void actionPerformed() {
		System.out.println("Black hole button activated!");

	}

}
