package vooga.games.asteroids.states;

import java.awt.event.KeyEvent;

import vooga.engine.control.*;
import vooga.engine.core.*;
import vooga.engine.factory.*;
import vooga.engine.state.*;

public class PlayState extends GameState{
	
	private Game game;
	
	public PlayState(Game game){
		this.game = game;
	}

	@Override
	public void initialize() {
		initLevel();
		initControls();
		
	}
	
	private void initLevel(){
		LevelFactory factory = new LevelParser();
		addPlayField(factory.getPlayfield("INSERTFILEPATHERE", game));
	}
	
	private void initControls(){
		Control playerControl = new KeyboardControl();
		playerControl.addInput(KeyEvent.VK_LEFT, "rotateLeft", "Ship", null);
		playerControl.addInput(KeyEvent.VK_RIGHT, "rotateRight", "Ship", null);
		playerControl.addInput(KeyEvent.VK_UP, "thrust", "Ship", null);
		playerControl.addInput(KeyEvent.VK_SPACE, "fire", "Ship", null);
		
	}

}
