package vooga.games.doodlejump.states;

import java.awt.event.KeyEvent;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;

import vooga.engine.control.Control;
import vooga.engine.control.KeyboardControl;
import vooga.engine.core.Game;
import vooga.engine.core.PlayField;
import vooga.engine.event.EventPool;
import vooga.engine.factory.LevelFactory;
import vooga.engine.factory.LevelManager;
import vooga.engine.factory.LevelParser;

import vooga.engine.core.BetterSprite;
import vooga.engine.overlay.OverlayString;
import vooga.engine.overlay.Stat;
import vooga.engine.resource.Resources;
import vooga.engine.state.GameState;
import vooga.games.doodlejump.BallSprite;
import vooga.games.doodlejump.Blah;
import vooga.games.doodlejump.DoodleSprite;
import vooga.games.doodlejump.events.DoodleDiedEvent;
import vooga.games.doodlejump.events.GameWonEvent;
import vooga.games.doodlejump.events.LevelWonEvent;
import vooga.games.grandius.events.FireBlackHoleEvent;
import vooga.games.grandius.events.FireHorizontalEvent;
import vooga.games.grandius.events.FireMissileEvent;
import vooga.games.grandius.events.FireVerticalEvent;
import vooga.games.grandius.events.LevelCompleteEvent;
import vooga.games.grandius.events.ZipsterFireEvent;

/**
 * The PlayState class extends GameState and allows for the actual game to be
 * played.
 * 
 * @author Adam Cue, Marcus Molchany, Nick Straub
 * 
 */
public class PlayState extends GameState {

	private static final String GAME_DELAY_STRING = "gameDelay";
	private static final String DOODLE_GROUP_STRING = "doodleGroup";
	private static final String DOODLE_VOOGA_PATH_STRING = "vooga.games.doodlejump.DoodleSprite";
	private static final String GAME_VOOGA_PATH_STRING = "vooga.games.doodlejump.BlahThis";
	private static final String MOVE_LEFT_STRING = "moveLeft";
	private static final String MOVE_RIGHT_STRING = "moveRight";
	private static final String SHOOT_STRING = "shoot";
	private static final String PAUSE_STRING = "pauseGame";
	private static final String GAME_STRING = "game";
	private static final String SCORE_STRING = "score";
	private static final String SCORE_INCREMENT_STRING = "scoreIncrement";
	private static final String BALL_GROUP_STRING = "ballGroup";

	private static final int FIRST_SPRITE = 0;
	private static final int ZERO_START_DELAY = 0;
	private static final int SUBTRACT_FROM_START_DELAY = 1;

	private Game game;
	PlayField myField;
	private DoodleSprite doodle;
	private LevelManager myLevelManager;
	private GameOverMenuState gameOverState;
	private GameWonState gameWonState;
	private EventPool eventPool;
	private int startDelay;

	public PlayState(Game game, LevelManager levelManager) {
		myLevelManager = levelManager;
		this.game = game;
	}

	@Override
	public void initialize() {
		gameOverState = new GameOverMenuState(game);
		gameWonState = new GameWonState(game);
		game.getGameStateManager().addGameState(gameOverState);
		game.getGameStateManager().addGameState(gameWonState);
		myField = myLevelManager.loadFirstLevel();
		initLevel();
		this.addPlayField(myField);
	}

	public void onActivate() {
		startDelay = Resources.getInt(GAME_DELAY_STRING);
	}

	private void initLevel() {
		doodle = (DoodleSprite) (myField.getGroup(DOODLE_GROUP_STRING)
				.getSprites()[FIRST_SPRITE]);
		doodle.setPlayState(this);
		initControls();
		initEvents();
	}

	public void initControls() {
		Control playerControl = new KeyboardControl(doodle, game);
		playerControl.addInput(KeyEvent.VK_LEFT, MOVE_LEFT_STRING,
				DOODLE_VOOGA_PATH_STRING);
		playerControl.addInput(KeyEvent.VK_RIGHT, MOVE_RIGHT_STRING,
				DOODLE_VOOGA_PATH_STRING);
		playerControl.addInput(KeyEvent.VK_SPACE, SHOOT_STRING,
				DOODLE_VOOGA_PATH_STRING);
		myField.addControl("doodle", playerControl);
		Control gameControl = new KeyboardControl(game, game);
		gameControl.addInput(KeyEvent.VK_P, PAUSE_STRING,
				GAME_VOOGA_PATH_STRING);
		myField.addControl(GAME_STRING, gameControl);
	}

	public void initEvents() {
		eventPool = new EventPool();
		eventPool.addEvent(new GameWonEvent(doodle, game, gameWonState));
		eventPool.addEvent(new DoodleDiedEvent(doodle, game, gameOverState));
		// eventPool.addEvent(new LevelWonEvent(doodle, this, (BlahThis) game));
	}

	@Override
	public void update(long elapsedTime) {
		if (startDelay != ZERO_START_DELAY
				&& startDelay <= Resources.getInt(GAME_DELAY_STRING)) {
			startDelay--;
			if (startDelay != Resources.getInt(GAME_DELAY_STRING)
					- SUBTRACT_FROM_START_DELAY)
				return;
		}
		for (SpriteGroup group : myField.getGroups()) {
			for (Sprite sprite : group.getSprites()) {
				if (doodle.getY() < doodle.getMaxHeight() && sprite != null) {
					scrollLevel(sprite);
					if (group.getName().equals(DOODLE_GROUP_STRING)) {
						incrementScore();
					}
				}
			}
		}
		super.update(elapsedTime);
		eventPool.checkEvents();
	}

	private void scrollLevel(Sprite sprite) {
		sprite.moveY(doodle.getMaxHeight() - doodle.getY());
	}

	private void incrementScore() {
		Stat<Integer> stat = (Stat<Integer>) (doodle.getStat(SCORE_STRING));
		stat.setStat(stat.getStat() + Resources.getInt(SCORE_INCREMENT_STRING));
	}

	public void setField(PlayField newField) {
		myField = newField;
	}

	public void setDoodle(DoodleSprite doodleSprite) {
		doodle = doodleSprite;
	}

	public LevelManager getLevelManager() {
		return myLevelManager;
	}

	public void addBall(BallSprite ball) {
		myField.getGroup(BALL_GROUP_STRING).add(ball);
	}
}
