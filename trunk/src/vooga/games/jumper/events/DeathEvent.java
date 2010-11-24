package vooga.games.jumper.events;

import com.golden.gamedev.object.Sprite;


import vooga.engine.event.IEventHandler;
import vooga.games.jumper.DropThis;
import vooga.games.jumper.states.PlayGameState;

/**
 * Event handler for the death of the main Doodle character.
 * @author Devon, Brian, Cody
 */
public class DeathEvent implements IEventHandler {
	
	private PlayGameState playState;
	private DropThis myGame;
	
	/**
	 * Constructor for this death event.  Sets the game and the
	 * gamestate that this event applies to.
	 * @param dropThis
	 * @param gamestate
	 */
	public DeathEvent(DropThis dropThis, PlayGameState gamestate) {
		playState = gamestate;
		myGame = dropThis;
	}

	
	/**
	 * Returns true if the Doodle reaches the top of the screen.
	 */
	@Override
	public boolean isTriggered() {
		
		for (Sprite s : playState.getGroup("doodleSprite").getSprites()) {
			if (s!=null && s.getY() < 0) {
				return true;
			}
		}
		return false;
	}
	
	

	/**
	 * When this event returns true, the action performed is to end
	 * the game.
	 */
	@Override
	public void actionPerformed() {
		myGame.deathGame();
	}

}
