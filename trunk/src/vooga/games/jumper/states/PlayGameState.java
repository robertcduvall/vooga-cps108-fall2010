package vooga.games.jumper.states;

import vooga.engine.control.Control;
import vooga.engine.control.KeyboardControl;
import vooga.engine.core.BetterSprite;
import vooga.engine.core.Game;
import vooga.engine.core.PlayField;
import vooga.engine.event.EventPool;
import vooga.engine.resource.Resources;
import vooga.engine.state.GameState;
import vooga.games.jumper.events.BlockOffScreenEvent;
import vooga.games.jumper.events.InfiniteBlocksEvent;

public class PlayGameState extends GameState{
	
	private Game myGame;
	private PlayField myField;
	private EventPool eventPool;
	
	private String moveLeft = "moveLeft";
	private String moveRight = "moveRight";
	private String doodleKey = "doodle";

	private int keyLeft = Resources.getInt("left key");
	private int keyRight = Resources.getInt("right key");
	
	public PlayGameState(Game game, PlayField field){
		super(field);
		myGame = game;
		myField = field;
	}
	
	
	@Override
	public void initialize() {
		initLevel();
	}
	
	private void initEvents() {
		eventPool = new EventPool();
		eventPool.addEvent(new InfiniteBlocksEvent(this));
		eventPool.addEvent(new BlockOffScreenEvent(this));
		System.out.println(this.getGroup("normalBlocks").getSize());
	}
	
	private void initLevel(){
		BetterSprite doodleSprite = (BetterSprite)(getGroup("doodleSprite").getSprites()[0]);
		initControls(doodleSprite);
		initEvents();
	}
	//Resources.getString("DoodleSprite")

	private void initControls(BetterSprite player) {
		Control playerControl = new KeyboardControl(player, myGame);
		playerControl.addInput(keyLeft, moveLeft, Resources.getString("DoodleSprite"));
		playerControl.addInput(keyRight, moveRight, Resources.getString("DoodleSprite"));
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
