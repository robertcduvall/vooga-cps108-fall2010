package vooga.games.doodlejump.events;

import vooga.engine.core.Game;
import vooga.engine.event.IEventHandler;
import vooga.engine.state.GameState;
import vooga.games.doodlejump.DoodleSprite;

public class GameWonEvent implements IEventHandler{
	private DoodleSprite myDoodle;
	private Game myGame;
	private GameState myGameWonState;
	
	public GameWonEvent(DoodleSprite doodle, Game game, GameState gameWonState) {
		myDoodle = doodle;
		myGame = game;
		myGameWonState = gameWonState;
	}

	@Override
	public void actionPerformed() {
		myGame.getGameStateManager().activateOnly(myGameWonState);
	}

	@Override
	public boolean isTriggered() {
		return myDoodle.isLevelComplete();
	}
}
