package vooga.games.tronupdate.state;


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
		Sprite player1 =(Sprite)(getGroup("playerSpriteGroup").getSprites()[0]);
		initPlayer1Controls(player1);
		Sprite player2 =(Sprite)(getGroup("playerSpriteGroup").getSprites()[1]);
		initPlayer2Controls(player2);

	}
	
	private void initPlayer1Controls(Sprite player){
		Control playerControl = new KeyboardControl(player, game);
		playerControl.addInput(KeyEvent.VK_LEFT, "playerXDirectionMove", "vooga.games.tronupdate.items.TronPlayer");
//		playerControl.addInput(KeyEvent.VK_RIGHT, "rotateRight", "vooga.games.asteroids.sprites.Ship");
//		playerControl.addInput(KeyEvent.VK_UP, "thrust", "vooga.games.asteroids.sprites.Ship");
//		playerControl.addInput(KeyEvent.VK_SPACE, "fire", "vooga.games.asteroids.sprites.Ship");
		myField.addControl(playerControl);
	}
	
	private void initPlayer2Controls(Sprite player){
		Control playerControl = new KeyboardControl(player, game);
		playerControl.addInput(KeyEvent.VK_LEFT, "playerXDirectionMove", "vooga.games.tronupdate.items.TronPlayer2");
//		playerControl.addInput(KeyEvent.VK_RIGHT, "rotateRight", "vooga.games.asteroids.sprites.Ship");
//		playerControl.addInput(KeyEvent.VK_UP, "thrust", "vooga.games.asteroids.sprites.Ship");
//		playerControl.addInput(KeyEvent.VK_SPACE, "fire", "vooga.games.asteroids.sprites.Ship");
		myField.addControl(playerControl);
	}
}
