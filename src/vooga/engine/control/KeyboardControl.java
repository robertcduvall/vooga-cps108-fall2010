package vooga.engine.control;

import java.util.*;
import vooga.engine.core.Game;
import vooga.engine.player.PlayerSprite;

/**
 * Built-in example of how to extend Control class properly. Also a usable keyboard
 * Controller creator.
 * 
 * @author Choi, Cue, Hawthorne
 * @version 1.0
 */

public class KeyboardControl extends Control{
	
	/**
	 * Default KeyboardControl constructor. Initializes a KeyboardControl object with no data.
	 */
	public KeyboardControl(){
		super();
	}
	
	/**
	 * Constructor creates a KeyboardControl object for a specific Game
	 * 
	 * @param game the Game to be controlled by this object
	 */
	public KeyboardControl(Game game){
		super(game);
	}
	
	/**
	 * Creates a KeyboardControl object with a Game and a single PlayerSprite which it controls.
	 * 
	 * @param initialEntity the PlayerSprite to be controlled
	 * @param game the Game to which the PlayerSprites belong
	 */
	public KeyboardControl(PlayerSprite entity, Game game){
		super(entity, game);
	}
	
	/**
	 * Creates a MouseControl object with a number of players which it controls. The 
	 * Players are passed in as a List of PlayerSprites	
	 * 
	 * @param initialEntities the players to be controlled
	 * @param game the Game to which the PlayerSprites belong
	 */
	public KeyboardControl(List<PlayerSprite> entities, Game game){
		super(entities, game);
	}

    
	@Override
	public void update(){
		while (!key.contains(myGame.bsInput.getKeyPressed()))
		{
			key.add(myGame.bsInput.getKeyPressed());
		}
		super.update();
	}
}
