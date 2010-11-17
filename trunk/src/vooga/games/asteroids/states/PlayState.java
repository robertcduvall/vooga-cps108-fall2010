package vooga.games.asteroids.states;

import vooga.engine.control.*;
import vooga.engine.core.*;
import vooga.engine.state.*;

public class PlayState extends GameState{
	
	private Game game;
	
	public PlayState(Game game){
		this.game = game;
	}

	@Override
	public void initialize() {
		// TODO Auto-generated method stub
		
	}
	
	private void initControls(){
		Control playerControl = new KeyboardControl();
		
	}

}
