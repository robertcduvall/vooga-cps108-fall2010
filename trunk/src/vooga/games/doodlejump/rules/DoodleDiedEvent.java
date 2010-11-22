package vooga.games.doodlejump.rules;

import vooga.engine.core.Game;
import vooga.engine.event.IEventHandler;
import vooga.engine.state.GameState;
import vooga.games.doodlejump.DoodleSprite;

public class DoodleDiedEvent implements IEventHandler{
	private DoodleSprite myDoodle;
	private Game myGame;
	private GameState myGameOverState;
	
	public DoodleDiedEvent(DoodleSprite doodle, Game game, GameState gameOverState) {
		myDoodle = doodle;
		myGame = game;
	}

	@Override
	public void actionPerformed() {
		myGame.getGameStateManager().activateOnly(myGameOverState);
	}

	@Override
	public boolean isTriggered() {
		return myDoodle.getDied();
	}
}
