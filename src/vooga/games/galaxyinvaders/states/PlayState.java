package vooga.games.galaxyinvaders.states;

import java.awt.Color;
import java.awt.event.KeyEvent;

import com.golden.gamedev.object.SpriteGroup;
import com.golden.gamedev.object.background.ColorBackground;

import vooga.engine.control.Control;
import vooga.engine.control.KeyboardControl;
import vooga.engine.core.Game;
import vooga.engine.core.PlayField;
import vooga.engine.core.BetterSprite;
import vooga.engine.state.GameState;
import vooga.games.galaxyinvaders.DropThis;
import vooga.games.galaxyinvaders.GameLostEvent;
import vooga.games.galaxyinvaders.LevelSwitchEvent;


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
	//	initEvents();
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
		gameControl.addInput(KeyEvent.VK_P, "pauseGame", "vooga.games.galaxyinvaders.DropThis");
		gameControl.addInput(KeyEvent.VK_R, "startNewGame", "vooga.games.galaxyinvaders.DropThis");
		myField.addControl("game", gameControl);
		
	}
	
	private void initEvents() {
		LevelSwitchEvent levelSwitch = new LevelSwitchEvent(game, this.getGroup("enemies"));
		myField.addEvent(levelSwitch);
		GameLostEvent gameLost = new GameLostEvent(game, this.getGroup("player"));
		myField.addEvent(gameLost);
	}
	
	public void clearEnemies() {
		SpriteGroup enemies = this.getGroup("enemies");
		enemies.clear();
	}
}
