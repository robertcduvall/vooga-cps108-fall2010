package vooga.games.asteroids.states;

import java.awt.event.KeyEvent;

import vooga.engine.control.Control;
import vooga.engine.control.KeyboardControl;
import vooga.engine.core.Game;
import vooga.engine.core.PlayField;
import vooga.engine.core.Sprite;
import vooga.engine.factory.LevelFactory;
import vooga.engine.factory.LevelParser;
import vooga.engine.resource.Resources;
import vooga.engine.state.GameState;
import vooga.games.asteroids.collisions.BulletToAsteroidCollision;
import vooga.games.asteroids.collisions.ShipToAsteroidCollision;

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

//		PlayField playField = factory.getPlayfield("INSERTFILEPATHERE", game);
		
//		playField.addCollisionGroup(bulletGroup, asteroidGroup, new BulletToAsteroidCollision());
//		playField.addCollisionGroup(shipGroup, asteroidGroup, new ShipToAsteroidCollision());
//		
//		addPlayField(playField);
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
