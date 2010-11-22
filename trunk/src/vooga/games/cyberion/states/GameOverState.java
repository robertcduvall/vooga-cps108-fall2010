package vooga.games.cyberion.states;

import vooga.engine.core.Game;
import vooga.engine.event.EventPool;
import vooga.engine.resource.Resources;
import vooga.engine.state.BasicTextGameState;
import vooga.engine.state.MenuGameState;
import vooga.games.cyberion.buttons.GameOverButton;

/**
 * Game over state for cyberion
 * 
 * @author Harris.He
 * 
 */

public class GameOverState extends MenuGameState {

	private GameOverButton myGameOverButton;
	private Game myGame;
	private EventPool eventPool;

	public GameOverState(Game game) {
		myGame = game;
	}

	@Override
	public void initialize() {
		this.myGameOverButton = new GameOverButton(myGame);
		addButton(myGameOverButton);
		eventPool = new EventPool();
		eventPool.addEvent(myGameOverButton);
	}

	@Override
	public void update(long elapsedTime) {
		super.update(elapsedTime);
		eventPool.checkEvents();
	}
}
