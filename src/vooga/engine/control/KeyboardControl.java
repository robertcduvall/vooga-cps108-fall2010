package vooga.engine.control;

import java.util.*;
import com.golden.gamedev.Game;
import vooga.engine.player.GameEntitySprite;

/**
 * Built-in example of how to extend Control class properly. Also a usable keyboard
 * Controller creator.
 * 
 * @author Choi, Cue, Hawthorne
 * @version 1.0
 */

public class KeyboardControl extends Control implements Controller{
	
	public KeyboardControl(){
		super();
	}
	
	public KeyboardControl(Game game){
		super(game);
	}
	
	public KeyboardControl(GameEntitySprite entity, Game game){
		super(entity, game);
	}
	
	public KeyboardControl(ArrayList<GameEntitySprite> entities, Game game){
		super(entities, game);
	}

    
	@Override
	public void update(){
		key = myGame.bsInput.getKeyPressed();

	}
}
