package vooga.games.mariogame.events;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;

import vooga.engine.event.IEventHandler;
import vooga.engine.resource.Resources;
import vooga.engine.util.SoundPlayer;
import vooga.engine.core.Game;
import vooga.games.mariogame.sprites.MarioSprite;
import vooga.games.mariogame.DropThis;

/**
 * Lose rule is enforced when Mario has 0 lives remaining.
 * 
 * @author Andrew Brown
 *
 */

public class LoseEvent implements IEventHandler{

	private MarioSprite myMario;
	private Game myGame;
	private String myLoseNoise;
	
	/**
	 * Constructor. User can pass objects or variables through this constructor.
	 * @param human Human class object
	 * @param ghost Ghost class object
	 */
	public LoseEvent(MarioSprite mario, Game game){
		myMario=mario;
		myGame=game;
		myLoseNoise = Resources.getSound("Game Over 1");
	}
	/**
	 * User defines what to do after event has been triggered.
	 */
	@Override
	public void actionPerformed() {
		SoundPlayer.playMusic(myLoseNoise);
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