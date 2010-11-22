package vooga.examples.control;

import java.awt.event.KeyEvent;

import vooga.engine.control.Control;
import vooga.engine.control.KeyboardControl;
import vooga.engine.core.BetterSprite;
import vooga.engine.core.Game;
import vooga.engine.resource.Resources;
import vooga.engine.state.GameState;

public class PlayStateControlExample extends GameState {

	BetterSprite myDuvallCharacter;
	int frameCount = 0;
	private Control control;
	Game game;
	
	
	@Override
	public void initialize() {

		myDuvallCharacter = new BetterSprite(Resources.getImage("duvall"), 150, 150);
		control = new KeyboardControl(this, game);
		control.addInput(KeyEvent.VK_LEFT, "setHorizontalSpeed", "vooga.engine.core.BetterSprite", int.class);
		control.setParams(KeyEvent.VK_LEFT, .5);
	}
	
	@Override
	public void update(long elapsedTime){
		
		super.update(elapsedTime);
		control.update();
	}

}
