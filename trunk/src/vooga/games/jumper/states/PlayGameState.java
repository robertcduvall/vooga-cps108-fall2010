package vooga.games.jumper.states;

import java.awt.event.KeyEvent;

import vooga.engine.control.Control;
import vooga.engine.control.KeyboardControl;
import vooga.engine.core.BetterSprite;
import vooga.engine.core.Game;
import vooga.engine.core.PlayField;
import vooga.engine.event.EventPool;
import vooga.engine.resource.Resources;
import vooga.engine.state.GameState;
import vooga.games.jumper.events.InfiniteBlocks;
import vooga.games.jumper.sprites.BlockSprite;

import com.golden.gamedev.object.Sprite;

public class PlayGameState extends GameState{
	
	private Game myGame;
	private PlayField myField;
	private EventPool eventPool;
	
	private String moveLeft = "moveLeft";
	private String moveRight = "moveRight";
	private String moveUp = "moveUp";
	private String doodleKey = "doodle";
	
	public PlayGameState(Game game, PlayField field){
		super(field);
		myGame = game;
		myField = field;
	}
	
	
	@Override
	public void initialize() {
		initLevel();
	}
	
	public void addSprites(BlockSprite block) {
		myField.add(block);
	}
	
	
	private void initEvents() {
		eventPool = new EventPool();
		eventPool.addEvent(new InfiniteBlocks(this));
	}
	
	private void initLevel(){
		BetterSprite doodleSprite = (BetterSprite)(getGroup("doodleSprite").getSprites()[0]);
		initControls(doodleSprite);
		initEvents();
	}

	private void initControls(BetterSprite player) {
		Control playerControl = new KeyboardControl(player, myGame);
		playerControl.addInput(KeyEvent.VK_LEFT, moveLeft, Resources.getString("DoodleSprite"));
		playerControl.addInput(KeyEvent.VK_RIGHT, moveRight, Resources.getString("DoodleSprite"));
		playerControl.addInput(KeyEvent.VK_UP, moveUp, Resources.getString("DoodleSprite"));
		myField.addControl(doodleKey, playerControl);
		getUpdateField().add(myField);
		getRenderField().add(myField);
	}
	
	@Override
	public void update(long elapsedTime){
		super.update(elapsedTime);
		eventPool.checkEvents();
	}
}
