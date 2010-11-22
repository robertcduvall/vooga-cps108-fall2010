package vooga.games.grandius.states;

import vooga.engine.core.Game;
import vooga.engine.event.EventPool;
import vooga.engine.state.MenuGameState;
import vooga.games.grandius.sprites.buttons.NextLevelButton;

public class LevelCompleteState extends MenuGameState {

	private NextLevelButton myNextLevelButton;
	private Game myGame;
    private EventPool eventPool;
    
	public LevelCompleteState(Game game) {
		myGame = game;
	}
	
	@Override
	public void initialize() {
		this.myNextLevelButton = new NextLevelButton(myGame);
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
