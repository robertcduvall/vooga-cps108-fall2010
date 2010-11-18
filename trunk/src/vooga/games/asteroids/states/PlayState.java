package vooga.games.asteroids.states;

import java.awt.event.KeyEvent;


import vooga.engine.control.Control;
import vooga.engine.control.KeyboardControl;
import vooga.engine.core.Game;
import vooga.engine.core.PlayField;
import vooga.engine.factory.LevelFactory;
import vooga.engine.factory.LevelParser;

import vooga.engine.core.Sprite;
import vooga.engine.resource.Resources;
import vooga.engine.state.GameState;

import vooga.games.asteroids.collisions.BulletToAsteroidCollision;
import vooga.games.asteroids.collisions.ShipToAsteroidCollision;

public class PlayState extends GameState{
	
	private Game game;
	PlayField myField;
	
	public PlayState(Game game, PlayField field){
		super(field);
		this.game = game;
		myField = field;
	}

	@Override
	public void initialize() {
		initLevel();
	}
	
	private void initLevel(){

//		PlayField playField = factory.getPlayfield("INSERTFILEPATHERE", game);
		
//		playField.addCollisionGroup(bulletGroup, asteroidGroup, new BulletToAsteroidCollision());
//		playField.addCollisionGroup(shipGroup, asteroidGroup, new ShipToAsteroidCollision());
//		
//		addPlayField(playField);
		Sprite playerShip = (Sprite)(getGroup("playerShip").getSprites()[0]);
		initControls(playerShip);

	}
	
	private void initControls(Sprite player){
		Control playerControl = new KeyboardControl(player, game);
		playerControl.addInput(KeyEvent.VK_LEFT, "rotateLeft", "vooga.games.asteroids.sprites.Ship");
		playerControl.addInput(KeyEvent.VK_RIGHT, "rotateRight", "vooga.games.asteroids.sprites.Ship");
		playerControl.addInput(KeyEvent.VK_UP, "thrust", "vooga.games.asteroids.sprites.Ship");
		playerControl.addInput(KeyEvent.VK_SPACE, "fire", "vooga.games.asteroids.sprites.Ship");
		myField.addControl(playerControl);
	}
}
