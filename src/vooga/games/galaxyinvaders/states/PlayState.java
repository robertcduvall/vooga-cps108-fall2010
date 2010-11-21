package vooga.games.galaxyinvaders.states;

import java.awt.event.KeyEvent;

import com.golden.gamedev.object.SpriteGroup;

import vooga.engine.control.Control;
import vooga.engine.control.KeyboardControl;
import vooga.engine.core.Game;
import vooga.engine.core.PlayField;
import vooga.engine.core.BetterSprite;
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
		BetterSprite ship = (BetterSprite)(getGroup("player").getSprites()[0]);
		initControls(ship);
	}
	
	private void initControls(BetterSprite player){
		Control playerControl = new KeyboardControl(player, game);
		playerControl.addInput(KeyEvent.VK_LEFT, "moveLeft", "vooga.games.galaxyinvaders.sprites.Ship");
		playerControl.addInput(KeyEvent.VK_RIGHT, "moveRight", "vooga.games.galaxyinvaders.sprites.Ship");
		playerControl.addInput(KeyEvent.VK_SPACE, "fire", "vooga.games.galaxyinvaders.sprites.Ship");
		myField.addControl("ship", playerControl);
		
		Control gameControl = new KeyboardControl(game, game);
		// this is a cheat code. it kills all the enemies on the screen and advances you to the next level
		gameControl.addInput(KeyEvent.VK_T, "clearEnemies", "vooga.games.galaxyinvaders.states.PlayState");
		gameControl.addInput(KeyEvent.VK_P, "toggle", "vooga.games.galaxyinvaders.DropThis");
		gameControl.addInput(KeyEvent.VK_R, "startNewGame", "vooga.games.galaxyinvaders.DropThis");
		myField.addControl("game", gameControl);
		
		
	}
	
	public void clearEnemies()
	{
		SpriteGroup enemies = this.getGroup("enemies");
		enemies.clear();
	}
}
