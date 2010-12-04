package vooga.games.grandius.states;

import vooga.engine.core.Game;
import vooga.engine.event.EventPool;
import vooga.engine.state.MenuGameState;
import vooga.games.grandius.sprites.buttons.QuitButton;

public class GameCompleteState extends MenuGameState {

	private QuitButton myQuitButton;
	private Game myGame;
    private EventPool eventPool;
    
	public GameCompleteState(Game game) {
		myGame = game;
	}
	
	@Override
	public void initialize() {
		this.myQuitButton = new QuitButton();
        addButton(myQuitButton);
        eventPool = new EventPool();
        eventPool.addEvent(myQuitButton);
	}
	
	@Override
    public void update(long elapsedTime) {
            super.update(elapsedTime);
            eventPool.checkEvents();
    }
}