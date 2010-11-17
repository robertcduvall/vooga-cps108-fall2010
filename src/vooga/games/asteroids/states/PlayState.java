package vooga.games.asteroids.states;

import java.awt.event.KeyEvent;

import vooga.engine.control.*;
import vooga.engine.core.*;
import vooga.engine.factory.*;
import vooga.engine.resource.Resources;
import vooga.engine.state.*;

public class PlayState extends GameState{
	
	private Game game;
	
	public PlayState(Game game){
		this.game = game;
	}

	@Override
	public void initialize() {
		initLevel();
		
		
	}
	
	private void initLevel(){
		LevelFactory factory = new LevelParser();
PlayField playField = factory.getPlayfield("INSERTFILEPATHERE", game);
		
		playField.addCollisionGroup(bulletGroup, asteroidGroup, new BulletToAsteroidCollision());
		playField.addCollisionGroup(shipGroup, asteroidGroup, new ShipToAsteroidCollision());
		
		addPlayField(playField);
		PlayField levelPlayfield = factory.getPlayfield(Resources.getString("levelFilesDirectory")+"asteroids.xml", game);
		addPlayField(levelPlayfield);
		
		// TODO how do you get the player sprite out of the playfield
		//Sprite[] playerShip = levelPlayfield.getGroup("playerShip").getSprites();
		
		// initControls(playerShip);

	}
	
	private void initControls(Sprite player){
		Control playerControl = new KeyboardControl(player, game);
		playerControl.addInput(KeyEvent.VK_LEFT, "rotateLeft", "Ship", null);
		playerControl.addInput(KeyEvent.VK_RIGHT, "rotateRight", "Ship", null);
		playerControl.addInput(KeyEvent.VK_UP, "thrust", "Ship", null);
		playerControl.addInput(KeyEvent.VK_SPACE, "fire", "Ship", null);
		
	}

}
