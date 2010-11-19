package vooga.games.grandius.buttons;

import vooga.engine.core.Game;
import vooga.engine.resource.Resources;
import vooga.widget.Button;

public class BuyMissileButton extends Button {

	public BuyMissileButton(Game game) {
		super(game);
		this.setImage(Resources.getImage("BuyMissileButton"));
	}

	@Override
	public void actionPerformed() {
		System.out.println("Missile button activated!");

	}

}
