package vooga.examples.state;

import vooga.engine.core.Sprite;
import vooga.engine.resource.Resources;
import vooga.engine.resource.clock.WorldClock;
import vooga.engine.state.GameState;

public class PlayState extends GameState {

	Sprite myDuvallCharacter;
	int frameCount = 0;
	
	
	@Override
	public void initialize() {

		myDuvallCharacter = new Sprite(Resources.getImage("duvall"), 150, 150);
		
	}
	
	@Override
	public void update(long elapsedTime){
		
		super.update(elapsedTime);
		
		myDuvallCharacter.setSpeed(Math.sin(frameCount), Math.cos(frameCount));
		
		frameCount++;
		
	}

}
