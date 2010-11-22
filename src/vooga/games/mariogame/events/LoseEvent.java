package vooga.games.mariogame.events;

import vooga.engine.core.Game;
import vooga.engine.event.IEventHandler;
import vooga.games.mariogame.DropThis;
import vooga.games.mariogame.sprites.MarioSprite;

/**
 * Lose rule is enforced when Mario has 0 lives remaining.
 * 
 * @author Andrew Brown
 *
 */

public class LoseEvent implements IEventHandler{

	private MarioSprite myMario;
	private Game myGame;
	
	/**
	 * Constructor. User can pass objects or variables through this constructor.
	 * @param human Human class object
	 * @param ghost Ghost class object
	 */
	public LoseEvent(MarioSprite mario, Game game){
		myMario=mario;
		myGame=game;
	}
	/**
	 * User defines what to do after event has been triggered.
	 */
	@Override
	public void actionPerformed() {
		((DropThis)myGame).loseGame();
	}
	/**
	 * User defines the condition when the event will be triggered
	 */
	@Override
	public boolean isTriggered() {
		return  myMario.getHealth()<=0;
	}


}