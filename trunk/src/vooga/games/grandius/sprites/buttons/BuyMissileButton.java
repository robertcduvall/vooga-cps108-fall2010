package vooga.games.grandius.sprites.buttons;

import java.awt.image.BufferedImage;

import vooga.engine.core.Game;
import vooga.engine.resource.Resources;
import vooga.games.grandius.DropThis;
import vooga.games.grandius.events.FireMissileEvent;
import vooga.widget.Button;

@SuppressWarnings("serial")
public class BuyMissileButton extends Button {

	private static final int START_X = 20;
	private static final int START_Y = 0;
	private static final BufferedImage myImage = Resources.getImage("buyMissileButtonImage");
	
	public BuyMissileButton() {
		super(myImage, START_X, START_Y);
	}

	@Override
	public void actionPerformed() {
		DropThis game = (DropThis)myGame;
		FireMissileEvent missileEvent = new FireMissileEvent(game, game.getPlayState().getPlayer(),
				game.getPlayState());
		missileEvent.setMissileActive(true);
		game.getPlayState().getEventPool().addEvent(missileEvent);
		new NextLevelButton(myGame).actionPerformed();

	}

}
