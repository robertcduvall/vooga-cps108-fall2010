package vooga.examples.core;

import com.golden.gamedev.object.SpriteGroup;

import vooga.engine.core.Sprite;
import vooga.engine.resource.Resources;
import vooga.engine.state.GameState;

public class ExampleState1 extends GameState {
	@Override
	public void initialize() {
		SpriteGroup bg=new SpriteGroup("background");
		bg.add(new Sprite(Resources.getImage("BG")));
		addGroup(bg);
	}

}
