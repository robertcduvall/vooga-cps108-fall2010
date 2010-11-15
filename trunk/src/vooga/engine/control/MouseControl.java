package vooga.engine.control;

import java.util.*;
import com.golden.gamedev.Game;
import vooga.engine.player.GameEntitySprite;

/**
 * Built-in example of how to extend Control class properly. Also a usable mouse
 * Controller creator.
 * @author Choi, Cue, Hawthorne
 * @version 1.0
 */

public class MouseControl extends Control{

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
		while (!key.contains(myGame.bsInput.getMousePressed()))
		{
			key.add(myGame.bsInput.getMousePressed());
		}
		super.update();
	}
}
