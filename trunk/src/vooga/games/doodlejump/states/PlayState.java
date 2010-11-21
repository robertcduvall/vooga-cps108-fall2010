package vooga.games.doodlejump.states;

import java.awt.event.KeyEvent;


import vooga.engine.control.Control;
import vooga.engine.control.KeyboardControl;
import vooga.engine.core.Game;
import vooga.engine.core.PlayField;
import vooga.engine.factory.LevelFactory;
import vooga.engine.factory.LevelParser;

import vooga.engine.core.BetterSprite;
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
		BetterSprite playerShip = (BetterSprite)(getGroup("doodleGroup").getSprites()[0]);
		initControls(playerShip);

	}
	
	private void initControls(BetterSprite player){
		Control playerControl = new KeyboardControl(player, game);
		playerControl.addInput(KeyEvent.VK_LEFT, "moveLeft", "vooga.games.doodlejump.DoodleSprite");
		playerControl.addInput(KeyEvent.VK_RIGHT, "moveRight", "vooga.games.doodlejump.DoodleSprite");
		playerControl.addInput(KeyEvent.VK_SPACE, "shoot", "vooga.games.doodlejump.DoodleSprite");
		myField.addControl("doodle", playerControl);
	}
}
