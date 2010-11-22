package vooga.games.doodlejump.events;

import vooga.engine.core.Game;
import vooga.engine.event.IEventHandler;
import vooga.engine.state.GameState;
import vooga.games.doodlejump.DoodleSprite;

/**
 * The DoodleDiedEvent implement IEventHandler and activates myGameOverState
 * when Doodle Dies
 * 
 * @author Adam Cue, Marcus Molchany, Nick Straub
 * 
 */
public class DoodleDiedEvent implements IEventHandler {
	private DoodleSprite myDoodle;
	private Game myGame;
	private GameState myGameOverState;

	public DoodleDiedEvent(DoodleSprite doodle, Game game,
			GameState gameOverState) {
		myDoodle = doodle;
		myGame = game;
		myGameOverState = gameOverState;
	}

	@Override
	public void actionPerformed() {
		myGame.getGameStateManager().activateOnly(myGameOverState);
	}

	@Override
	public boolean isTriggered() {
		return myDoodle.doodleFell();
	}
}
