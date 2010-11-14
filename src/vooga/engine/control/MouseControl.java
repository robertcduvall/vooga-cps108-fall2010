package vooga.engine.player.control;

import java.util.*;
import com.golden.gamedev.Game;

/**
 * Built-in example of how to extend Control class properly. Also a usable mouse
 * Controller creator.
 * @author Choi, Cue, Hawthorne
 * @version 1.0
 */

public class MouseControl extends Control implements Controller{
	
	public MouseControl(){
		super();
	}
	
	public MouseControl(Game game){
		super(game);
	}
	
	public MouseControl(GameEntitySprite initialEntity, Game game){
		super(initialEntity, game);
	}
	
	public MouseControl(ArrayList<GameEntitySprite> initialEntities, Game game){
		super(initialEntities, game);
	}

	@Override
	public void update(){
		key = myGame.bsInput.getMousePressed();
        
	}
}
