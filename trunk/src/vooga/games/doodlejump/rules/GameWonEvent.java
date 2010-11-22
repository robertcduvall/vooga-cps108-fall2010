package vooga.games.doodlejump.rules;

import vooga.engine.core.Game;
import vooga.engine.event.IEventHandler;
import vooga.engine.state.GameState;
import vooga.games.doodlejump.DoodleSprite;
import vooga.games.doodlejump.events.GameWonRule;

public class GameWonEvent implements IEventHandler{
	private DoodleSprite myDoodle;
	private Game myGame;
	private GameState myGameWonState;
	private GameWonRule myGameWonRule;
	
	public GameWonEvent(DoodleSprite doodle, Game game, GameState GameWonState) {
		myDoodle = doodle;
		myGame = game;
	}

	@Override
	public void actionPerformed() {
		myGame.getGameStateManager().activateOnly(myGameWonState);
	}

	@Override
	public boolean isTriggered() {
		return myGameWonRule.isSatisfied();
	}
}
