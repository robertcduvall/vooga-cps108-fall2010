package vooga.games.cyberion.states;

import vooga.engine.core.Game;
import vooga.engine.event.EventPool;
import vooga.engine.state.MenuGameState;
import vooga.games.cyberion.buttons.NextLevelButton;

/**
 * Level complete state for cyberion
 * 
 * @author Harris.He
 * 
 */

public class LevelCompleteState extends MenuGameState {
	private NextLevelButton myNextLevelButton;
	private EventPool eventPool;

	public LevelCompleteState() {
	}

	@Override
	public void initialize() {
		this.myNextLevelButton = new NextLevelButton();
		addButton(myNextLevelButton);
		eventPool = new EventPool();
		eventPool.addEvent(myNextLevelButton);
	}

	@Override
	public void update(long elapsedTime) {
		super.update(elapsedTime);
		eventPool.checkEvents();
	}
}
