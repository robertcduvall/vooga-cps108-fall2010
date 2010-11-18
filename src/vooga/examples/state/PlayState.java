package vooga.examples.state;

import vooga.engine.core.BetterSprite;
import vooga.engine.resource.Resources;
import vooga.engine.resource.clock.WorldClock;
import vooga.engine.state.GameState;

public class PlayState extends GameState {

	BetterSprite myDuvallCharacter;
	int frameCount = 0;
	
	
	@Override
	public void initialize() {

		myDuvallCharacter = new BetterSprite(Resources.getImage("duvall"), 150, 150);
		
	}
	
	@Override
	public void update(long elapsedTime){
		
		super.update(elapsedTime);
		
		myDuvallCharacter.setSpeed(Math.sin(frameCount), Math.cos(frameCount));
		
		frameCount++;
		
	}

}
