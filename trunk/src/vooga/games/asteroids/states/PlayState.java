package vooga.games.asteroids.states;

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
import vooga.games.asteroids.sprites.Ship;

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
		BetterSprite playerShip = (BetterSprite)(getGroup("playerShip").getSprites()[0]);
		initControls(playerShip);
	}
	
	private void initControls(BetterSprite player){
		Control playerControl = new KeyboardControl(player, game);
		playerControl.addInput(KeyEvent.VK_LEFT, "rotateLeft", "vooga.games.asteroids.sprites.Ship", null);
		playerControl.addInput(KeyEvent.VK_RIGHT, "rotateRight", "vooga.games.asteroids.sprites.Ship", null);
		playerControl.addInput(KeyEvent.VK_UP, "thrust", "vooga.games.asteroids.sprites.Ship", null);
		playerControl.addInput(KeyEvent.VK_SPACE, "fire", "vooga.games.asteroids.sprites.Ship", null);
		myField.addControl("ship", playerControl);
		this.getUpdateField().add(myField);
		this.getRenderField().add(myField);
	}
}
