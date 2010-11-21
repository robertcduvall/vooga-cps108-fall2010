package vooga.games.cyberion.states;

import vooga.engine.core.Game;
import vooga.engine.state.MenuGameState;
import vooga.games.cyberion.buttons.PlayButton;
import vooga.games.cyberion.buttons.QuitButton;

/**
 * Cyberion specific menu game state
 * 
 * @author Harris.He
 * 
 */

public class MenuState extends MenuGameState {

	private PlayButton myPlayButton;
	private QuitButton myQuitButton;
	private Game myGame;

	public MenuState(Game game) {
		myGame = game;
	}

	public void initialize() {
		this.myPlayButton = new PlayButton(myGame);
		this.myQuitButton = new QuitButton(myGame);
		addButton(myPlayButton);
		addButton(myQuitButton);
	}
}
