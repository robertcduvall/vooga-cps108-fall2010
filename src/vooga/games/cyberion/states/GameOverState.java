package vooga.games.cyberion.states;

import com.golden.gamedev.object.PlayField;
import com.golden.gamedev.object.background.ImageBackground;

import vooga.engine.core.Game;
import vooga.engine.event.EventPool;
import vooga.engine.resource.Resources;
import vooga.engine.state.MenuGameState;
import vooga.games.cyberion.buttons.PlayButton;
import vooga.games.cyberion.buttons.QuitButton;

/**
 * Cyberion specific menu game state
 * 
 * @author Harris.He
 * 
 */

public class GameOverState extends MenuGameState {

	private PlayButton myPlayButton;
	private QuitButton myQuitButton;
	private Game myGame;
	private EventPool eventPool;

	public GameOverState(Game game) {
		myGame = game;
	}

	@Override
	public void initialize() {
		eventPool = new EventPool();
		this.myPlayButton = new PlayButton(myGame);
		this.myQuitButton = new QuitButton();
		addButton(myPlayButton);
		addButton(myQuitButton);
		eventPool.addEvent(myPlayButton);
		eventPool.addEvent(myQuitButton);
	}

	@Override
	public void update(long elapsedTime) {
		super.update(elapsedTime);
		eventPool.checkEvents();
	}
}
