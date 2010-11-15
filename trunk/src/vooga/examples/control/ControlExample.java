package vooga.examples.control;

import java.util.ArrayList;

import com.golden.gamedev.Game;

import vooga.engine.control.Control;
import vooga.engine.player.GameEntitySprite;

public class ControlExample extends Control{
	public ControlExample(){
		super();
	}

	public ControlExample(Game game){
		super(game);
	}

	public ControlExample(GameEntitySprite entity, Game game){
		super(entity, game);
	}

	public ControlExample(ArrayList<GameEntitySprite> entities, Game game){
		super(entities, game);
	}
	
	@Override
	public void update(){
		int input = 0;
		//input = Joystick.getCompassDirection() OBTAIN DATA FROM PERIPHERAL
		while (!key.contains(input))
		{
			key.add(input);
		}
		super.update();
	}
}
