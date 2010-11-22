package vooga.games.jumper.states;

import vooga.engine.control.Control;
import vooga.engine.control.KeyboardControl;
import vooga.engine.core.BetterSprite;
import vooga.engine.core.Game;
import vooga.engine.core.PlayField;
import vooga.engine.event.EventPool;
import vooga.engine.resource.Resources;
import vooga.engine.state.GameState;
import vooga.games.jumper.DropThis;
import vooga.games.jumper.events.BlockOffScreenEvent;
import vooga.games.jumper.events.DeathEvent;
import vooga.games.jumper.events.InfiniteBlocksEvent;

public class PlayGameState extends GameState{
	
	private Game myGame;
	private DropThis myDropThis;
	private PlayField myField;
	private EventPool eventPool;
	public Control playerControl;
	
	private String moveLeft = "moveLeft";
	private String moveRight = "moveRight";
	private String doodleKey = "doodle";

	private int keyLeft = Resources.getInt("left key");
	private int keyRight = Resources.getInt("right key");
	
	/**
	 * Create new PlayGameState
	 * @params game, playfield, dropthis
	 */
	
	public PlayGameState(Game game, PlayField field, DropThis dropThis){
		super(field);
		myDropThis = dropThis;
		myGame = game;
		myField = field;
	}
	
	/**
	 * Initialize the level
	 */
	
	@Override
	public void initialize() {
		initLevel();
	}
	
	/**
	 * Initialize the game's events
	 * Add the events to the eventpool
	 */
	
	private void initEvents() {
		eventPool = new EventPool();
		eventPool.addEvent(new InfiniteBlocksEvent(this));
		eventPool.addEvent(new BlockOffScreenEvent(this));
		eventPool.addEvent(new DeathEvent(myDropThis, this));
	}
	
	/**
	 * Create doodlesprite and the game's control scheme
	 * Initialize the events
	 */
	
	private void initLevel(){
		BetterSprite doodleSprite = (BetterSprite)(getGroup("doodleSprite").getSprites()[0]);
		initControls(doodleSprite);
		initEvents();
	}
	
	/**
	 * Initialize the game's controls
	 */
	
	private void initControls(BetterSprite player) {
		playerControl = new KeyboardControl(player, myGame);
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
