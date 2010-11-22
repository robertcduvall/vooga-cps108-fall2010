package vooga.games.mariogame.events;

import vooga.engine.event.IEventHandler;
import vooga.games.mariogame.rules.Gravity;
import vooga.games.mariogame.sprites.MarioSprite;
import vooga.games.mariogame.states.GamePlayState;

/**
 * Lose rule is enforced when Mario has 0 lives remaining.
 * 
 * @author Andrew Brown
 *
 */

public class NextLevelEvent implements IEventHandler{

	private MarioSprite myMario;
	private GamePlayState myGameState;
	
	/**
	 * Constructor. User can pass objects or variables through this constructor.
	 * @param human Human class object
	 * @param ghost Ghost class object
	 */
	public NextLevelEvent(MarioSprite mario, GamePlayState gameState){
		myMario=mario;
		myGameState=gameState;
	}
	/**
	 * User defines what to do after event has been triggered.
	 */
	@Override
	public void actionPerformed() {
		Gravity.reset();
		myGameState.nextLevel();
	}
	/**
	 * User defines the condition when the event will be triggered
	 */
	@Override
	public boolean isTriggered() {
		return  myMario.levelFinished();
	}


}
