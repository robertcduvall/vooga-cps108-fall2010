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
	
	public void onActivate(){
		startDelay = 100;
	}
	
	public void nextLevel(){
		doodle.reset();
		myField.clearPlayField();
		myField = myLevelManager.loadNextLevel();
	}

	public void addBall(BallSprite ball) {
		myField.getGroup("ballGroup").add(ball);
	}

	private void initLevel() {
		doodle = (DoodleSprite) (myField.getGroup("doodleGroup").getSprites()[0]);
		doodle.setPlayState(this);
		initControls(doodle);
		initEvents();
	}

	@Override
	public void update(long elapsedTime) {
		if(startDelay != 0 && startDelay < 99)
			startDelay--;
		else{
			if(startDelay != 0){
				startDelay--;
			}
			for (SpriteGroup group : myField.getGroups()) {
				for (Sprite sprite : group.getSprites()) {
					if (doodle.getY() < doodle.getMaxHeight() && sprite != null) {
						scrollLevel(sprite);
						if (group.getName().equals("doodleGroup")) {
							((Stat<Integer>)doodle.getStat("score")).setStat(((Stat<Integer>)doodle.getStat("score")).getStat() + 5);
						}
					}
				}
			}
			super.update(elapsedTime);
			eventPool.checkEvents();
		}
	}

	private void scrollLevel(Sprite sprite) {
		sprite.moveY(doodle.getMaxHeight() - doodle.getY());
	}

	private void initControls(BetterSprite player) {
		Control playerControl = new KeyboardControl(player, game);
		playerControl.addInput(KeyEvent.VK_LEFT, "moveLeft",
				"vooga.games.doodlejump.DoodleSprite");
		playerControl.addInput(KeyEvent.VK_RIGHT, "moveRight",
				"vooga.games.doodlejump.DoodleSprite");
		playerControl.addInput(KeyEvent.VK_SPACE, "shoot",
				"vooga.games.doodlejump.DoodleSprite");
		myField.addControl("doodle", playerControl);
		Control gameControl = new KeyboardControl(game, game);
		gameControl.addInput(KeyEvent.VK_P, "pauseGame", "vooga.games.doodlejump.BlahThis");
		myField.addControl("game", gameControl);
	}
	
	private void initEvents(){
		eventPool = new EventPool();
		eventPool.addEvent(new GameWonEvent(doodle, game, gameWonState));
		eventPool.addEvent(new DoodleDiedEvent(doodle, game, gameOverState));
	}
}
