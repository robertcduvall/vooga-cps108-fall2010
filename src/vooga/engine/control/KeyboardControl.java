package vooga.engine.control;

import java.util.*;
import vooga.engine.core.Game;

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
	 * Creates a KeyboardControl object with a Game and a single Object which it controls.
	 * 
	 * @param initialEntity the Object to be controlled
	 * @param game the Game to which the Object belongs
	 */
	public KeyboardControl(Object entity, Game game){
		super(entity, game);
	}
	
	/**
	 * Creates a KeyboardControl object with a number of objects which it controls.
	 * 
	 * @param initialEntities the Objects to be controlled
	 * @param game the Game to which the Objects belong
	 */
	public KeyboardControl(List<Object> entities, Game game){
		super(entities, game);
	}

    
	@Override
	public void update(){

		for (int key : methodMap.keySet())
		{
			if (myGame.bsInput.isKeyDown(key))
			{
				super.update(key);
			}
		}
	}
}
