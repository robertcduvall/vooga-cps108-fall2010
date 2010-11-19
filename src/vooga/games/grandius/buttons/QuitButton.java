package vooga.games.grandius.buttons;

import vooga.engine.core.Game;
import vooga.widget.Button;

public class QuitButton extends Button {

	public QuitButton(Game game) {
		super(game);
	}

	@Override
	public void actionPerformed() {
		System.out.println("Quit button activated!");
	}

}
