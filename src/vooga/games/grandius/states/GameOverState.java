package vooga.games.grandius.states;

import vooga.engine.core.Game;
import vooga.engine.event.EventPool;
import vooga.engine.state.MenuGameState;
import vooga.games.grandius.sprites.buttons.GameOverButton;

public class GameOverState extends MenuGameState {

	private GameOverButton myGameOverButton;
	private Game myGame;
    private EventPool eventPool;
    
	public GameOverState(Game game) {
		myGame = game;
	}
	
	@Override
	public void initialize() {
		this.myGameOverButton = new GameOverButton();
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