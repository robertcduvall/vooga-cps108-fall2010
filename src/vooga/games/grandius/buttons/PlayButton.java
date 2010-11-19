package vooga.games.grandius.buttons;

import vooga.engine.core.Game;
import vooga.engine.resource.Resources;
import vooga.widget.Button;

public class PlayButton extends Button {

	public PlayButton(Game game) {
		super(game);
		this.setImage(Resources.getImage("PlayButton"));
	}

	@Override
	public void actionPerformed() {
		System.out.println("Play button activated!");

	}

}
