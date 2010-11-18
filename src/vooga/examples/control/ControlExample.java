package vooga.examples.control;

import java.util.ArrayList;

import vooga.engine.core.Game;
import vooga.engine.core.BetterSprite;

import vooga.engine.control.Control;

public class ControlExample extends Control{
	public ControlExample(){
		super();
	}

	public ControlExample(Game game){
		super(game);
	}

	public ControlExample(BetterSprite entity, Game game){
		super(entity, game);
	}

	public ControlExample(ArrayList<BetterSprite> entities, Game game){
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
