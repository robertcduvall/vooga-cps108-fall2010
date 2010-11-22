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
import vooga.games.doodlejump.BlahThis;
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
		startDelay = Resources.getInt("gameDelay");
	}

	private void initLevel() {
		doodle = (DoodleSprite) (myField.getGroup("doodleGroup").getSprites()[0]);
		doodle.setPlayState(this);
		initControls();
		initEvents();
	}

	public void initControls() {
		Control playerControl = new KeyboardControl(doodle, game);
		playerControl.addInput(KeyEvent.VK_LEFT, "moveLeft",
		"vooga.games.doodlejump.DoodleSprite");
		playerControl.addInput(KeyEvent.VK_RIGHT, "moveRight",
		"vooga.games.doodlejump.DoodleSprite");
		playerControl.addInput(KeyEvent.VK_SPACE, "shoot",
		"vooga.games.doodlejump.DoodleSprite");
		myField.addControl("doodle", playerControl);
		Control gameControl = new KeyboardControl(game, game);
		gameControl.addInput(KeyEvent.VK_P, "pauseGame",
		"vooga.games.doodlejump.BlahThis");
		myField.addControl("game", gameControl);
	}

	public void initEvents() {
		eventPool = new EventPool();
		eventPool.addEvent(new GameWonEvent(doodle, game, gameWonState));
		eventPool.addEvent(new DoodleDiedEvent(doodle, game, gameOverState));
		//eventPool.addEvent(new LevelWonEvent(doodle, this, (BlahThis) game));
	}

	@Override
	public void update(long elapsedTime) {
		if (startDelay != 0 && startDelay <= Resources.getInt("gameDelay")) {
			startDelay--;
			if(startDelay != Resources.getInt("gameDelay") - 1)
				return;
		}
		for (SpriteGroup group : myField.getGroups()) {
			for (Sprite sprite : group.getSprites()) {
				if (doodle.getY() < doodle.getMaxHeight() && sprite != null) {
					scrollLevel(sprite);
					if (group.getName().equals("doodleGroup")) {
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

	private void incrementScore(){
		Stat<Integer> stat = (Stat<Integer>) (doodle.getStat("score"));
		stat.setStat(stat.getStat() + Resources.getInt("scoreIncrement"));
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
		myField.getGroup("ballGroup").add(ball);
	}
}
