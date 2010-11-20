package vooga.games.jumper;

import java.awt.event.KeyEvent;

import vooga.engine.control.Control;
import vooga.engine.control.KeyboardControl;
import vooga.engine.core.BetterSprite;
import vooga.engine.core.Game;
import vooga.engine.core.PlayField;
import vooga.engine.state.GameState;

import com.golden.gamedev.object.Sprite;

public class PlayGameState extends GameState{
	
	private Game myGame;
	private PlayField myField;
	
	public PlayGameState(Game game, PlayField field){
		super(field);
		myGame = game;
		myField = field;
	}
	
	@Override
	public void initialize() {
		initLevel();
	}
	
	private void initLevel(){
		BetterSprite doodleSprite = (BetterSprite)(getGroup("doodleSprite").getSprites()[0]);
		initControls(doodleSprite);
	}

	private void initControls(BetterSprite player) {
		Control playerControl = new KeyboardControl(player, myGame);
		playerControl.addInput(KeyEvent.VK_LEFT, "moveLeft", "vooga.games.jumper.sprites.DoodleSprite");
		playerControl.addInput(KeyEvent.VK_RIGHT, "moveRight", "vooga.games.jumper.sprites.DoodleSprite");
		playerControl.addInput(KeyEvent.VK_UP, "moveUp", "vooga.games.jumper.sprites.DoodleSprite");
		myField.addControl(playerControl);
		this.getUpdateField().add(myField);
		this.getRenderField().add(myField);
	}
}
