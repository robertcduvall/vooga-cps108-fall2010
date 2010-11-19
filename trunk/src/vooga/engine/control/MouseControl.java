package vooga.engine.control;

import java.util.*;
import vooga.engine.core.Game;
import vooga.engine.core.BetterSprite;

/**
 * Built-in example of how to extend Control class properly. Also a usable mouse
 * Controller creator.
 * @author Choi, Cue, Hawthorne
 * @version 1.0
 */

public class MouseControl extends Control{

	/**
	 * Default MouseControl constructor. Initializes a Control object with no data.
	 */
	public MouseControl(){
		super();
	}

	/**
	 * Constructor creates a MouseControl object for a specific Game
	 * 
	 * @param game the Game to be controlled by this object
	 */
	public MouseControl(Game game){
		super(game);
	}

	/**
	 * Creates a MouseControl object with a Game and a single PlayerSprite which it controls.
	 * 
	 * @param initialEntity the PlayerSprite to be controlled
	 * @param game the Game to which the PlayerSprites belong
	 */
	public MouseControl(BetterSprite initialEntity, Game game){
		super(initialEntity, game);
	}

	/**
	 * Creates a MouseControl object with a number of players which it controls. The 
	 * Players are passed in as a List of PlayerSprites	
	 * 
	 * @param initialEntities the players to be controlled
	 * @param game the Game to which the PlayerSprites belong
	 */
	public MouseControl(List<BetterSprite> initialEntities, Game game){
		super(initialEntities, game);
	}

	@Override
	public void update(){
		for (int key : methodMap.keySet())
		{
			if (myGame.bsInput.isMouseDown(key))
			{
				super.update(key);
			}
		}
	}
}
