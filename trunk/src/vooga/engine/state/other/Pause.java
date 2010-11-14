/**
 * @author: Yijia Mu
 * @date: 10-17-10
 * @description: The pause class is a specific subclass of the Menu class. Unlike the menu class,
 * it must specify a user input (keyboard or mouse) for the pause() method inside the constructor.
 * The actOnKeyPressed(int key) is overwritten to act when the pauseKey is pressed or when the 
 * resume key is pressed
 */

package vooga.engine.state.other;

import com.golden.gamedev.Game;
import com.golden.gamedev.object.SpriteGroup;

public class Pause extends Menu{

	private int pauseKey;
	private int resumeKey;
	
	public Pause(Game game, int pauseKeyEvent, int resumeKeyEvent) 
	{
		super(game);
		tagKeyToMenu(pauseKeyEvent, this);
		pauseKey = pauseKeyEvent;
		resumeKey = resumeKeyEvent;
	}
	
	public Pause(Game game, SpriteGroup displayedgroup, int pauseKeyEvent, int resumeKeyEvent)
	{
		super(game, displayedgroup);
		tagKeyToMenu(pauseKeyEvent, this);
		pauseKey = pauseKeyEvent;
		resumeKey = resumeKeyEvent;
	}
	
	public void actOnKeyPressed(int key)
	{
		if(key == pauseKey)
		{
			setActive(true);
			getCurrentGame().stop();
		}
		
		if(key == resumeKey)
		{
			setActive(false);
			getCurrentGame().start();
		}		
	}
		
}
