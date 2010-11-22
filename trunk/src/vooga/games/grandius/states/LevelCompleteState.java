package vooga.games.grandius.states;

import vooga.engine.core.Game;
import vooga.engine.event.EventPool;
import vooga.engine.state.MenuGameState;
import vooga.games.grandius.sprites.buttons.NextLevelButton;
import vooga.games.grandius.sprites.buttons.ShoppingLevelButton;


public class LevelCompleteState extends MenuGameState {

	private NextLevelButton myNextLevelButton;
	private ShoppingLevelButton myShoppingLevelButton;
	private Game myGame;
    private EventPool eventPool;
    
	public LevelCompleteState(Game game) {
		myGame = game;
	}
	
	@Override
	public void initialize() {
		this.myNextLevelButton = new NextLevelButton(myGame);
		this.myShoppingLevelButton = new ShoppingLevelButton(myGame);
        addButton(myNextLevelButton);
        addButton(myShoppingLevelButton);
        eventPool = new EventPool();
        eventPool.addEvent(myNextLevelButton);
        eventPool.addEvent(myShoppingLevelButton);
	}
	
	@Override
    public void update(long elapsedTime) {
            super.update(elapsedTime);
            eventPool.checkEvents();
    }
	
}
