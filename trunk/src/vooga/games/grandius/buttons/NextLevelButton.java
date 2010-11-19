package vooga.games.grandius.buttons;

import vooga.engine.core.Game;
import vooga.engine.resource.Resources;
import vooga.widget.Button;

public class NextLevelButton extends Button {

	public NextLevelButton(Game game) {
		super(game);
		this.setImage(Resources.getImage("NextLevelButton"));
	}

	@Override
	public void actionPerformed() {
		System.out.println("Next level button activated!");
	}

}
