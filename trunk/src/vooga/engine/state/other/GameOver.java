/**
 * @author: Yijia Mu
 * @date: 10-17-10
 * @description: The GameOver state is displayed when the game comes to an end and
 * what GUI's are to displayed when that happens. 
 */

package vooga.engine.state.other;


import com.golden.gamedev.Game;
import com.golden.gamedev.object.SpriteGroup;

public class GameOver extends Menu{

	private int restartKey;
	
	/**
	 * The constructor must take in the restartKeyEvent, which designates which
	 * key keeps track of restart input
	 * @param game
	 * @param restartKeyEvent
	 */
	public GameOver(Game game, int restartKeyEvent) 
	{
		super(game);
		restartKey = restartKeyEvent;
	}
	
	public GameOver(Game game, SpriteGroup displayedgroup, int restartKeyEvent)
	{
		super(game, displayedgroup);
		tagKeyToMenu(restartKeyEvent, this);
		restartKey = restartKeyEvent;
	}
	
	/**
	 * If the restartKey is pressed, then the start starts and initializes.
	 */
	public void actOnKeyPressed(int key)
	{
		if(restartKey == key)
		{
			setActive(false);
			getCurrentGame().start();
			getCurrentGame().initResources();
		}		
	}
	
	/**
	 * If gameOver is set to active, then the game is stopped and the gameOver
	 * state is set to active. 
	 */
	public void setActive(boolean state)
	{	
		super.setActive(state);
		if(state == true)
		{
			getCurrentGame().stop();
		}
	}
}
