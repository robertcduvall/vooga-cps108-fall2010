package vooga.games.jumper.states;

import java.awt.event.KeyEvent;
import java.util.ResourceBundle;

import vooga.engine.control.Control;
import vooga.engine.control.KeyboardControl;
import vooga.engine.core.BetterSprite;
import vooga.engine.core.Game;
import vooga.engine.core.PlayField;
import vooga.engine.event.EventPool;
import vooga.engine.resource.Resources;
import vooga.engine.state.GameState;
import vooga.games.jumper.events.InfiniteBlocksEvent;
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

	private String keyLeft = "left key";
	private String keyRight = "right key";
	private String keyUp = "up key";


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
		//doesn't see to do anything..
		eventPool.addEvent(new InfiniteBlocksEvent(this));
	}
	
	private void initLevel(){
		BetterSprite doodleSprite = (BetterSprite)(getGroup("doodleSprite").getSprites()[0]);
		initControls(doodleSprite);
		initEvents();
	}
	//Resources.getString("DoodleSprite")

	private void initControls(BetterSprite player) {
		Control playerControl = new KeyboardControl(player, myGame);
		playerControl.addInput(Resources.getInt(keyLeft), moveLeft, Resources.getString("DoodleSprite"));
		playerControl.addInput(Resources.getInt(keyRight), moveRight, Resources.getString("DoodleSprite"));
		playerControl.addInput(Resources.getInt(keyUp), moveUp, Resources.getString("DoodleSprite"));
		myField.addControl(doodleKey, playerControl);
		this.getUpdateField().add(myField);
		this.getRenderField().add(myField);
	}
	
	@Override
	public void update(long elapsedTime){
		super.update(elapsedTime);
		eventPool.checkEvents();
	}
}
