package vooga.games.cyberion;

import java.awt.event.KeyEvent;

import com.golden.gamedev.object.GameFont;

import vooga.engine.control.Control;
import vooga.engine.control.KeyboardControl;
import vooga.engine.core.Game;
import vooga.engine.event.EventPool;
import vooga.engine.factory.LevelManager;
import vooga.engine.factory.LevelParser;
import vooga.engine.resource.Resources;
import vooga.engine.state.PauseGameState;
import vooga.games.cyberion.states.GameCompleteState;
import vooga.games.cyberion.states.GameOverState;
import vooga.games.cyberion.states.LevelCompleteState;
import vooga.games.cyberion.states.MenuState;
import vooga.games.cyberion.states.PlayState;

/**
 * Cyberion shooting game
 * 
 * @author Hao He, Scott Winkleman, Vitor Olivier
 * @version 1.0
 * @see http://www.istisoft.net/online/cyberion/cyberion.html
 */

public class DropThis extends Game {
	
	private MenuState myMenuState;
	private PlayState myPlayState;
	private PauseGameState myPauseState;
	private LevelCompleteState myLevelCompleteState;
	private GameCompleteState myGameCompleteState;
	private GameOverState myGameOverState;
	private EventPool eventPool;

	private LevelParser levelParser;
	private LevelManager levelManager;
	private Control gameControl;

	private GameFont font;

	private static final int WIDTH = 1024;
	private static final int HEIGHT = 768;

	public void initResources() {

		super.initResources();

		Resources.loadInt("Height", HEIGHT);
		Resources.loadInt("Width", WIDTH);

		initControls();
	}

	public void initControls() {
		gameControl = new KeyboardControl(this, this);
		gameControl.addInput(KeyEvent.VK_P, "pauseGame",
				"vooga.games.cyberion.DropThis");
		gameControl.addInput(KeyEvent.VK_R, "resumeGame",
				"vooga.games.cyberion.DropThis");
	}

	public void pauseGame() {
		this.getGameStateManager().switchTo(myPauseState);
	}

	public void resumeGame() {
		this.getGameStateManager().switchTo(myPlayState);
	}

	public void initGameStates() {
		super.initGameStates();

		initLevelManager();
		stateManager.addGameState(myMenuState = new MenuState(), 0);
		//myPlayState = new PlayState(levelManager, this);
		stateManager.addGameState(myPlayState);
		stateManager
				.addGameState(myLevelCompleteState = new LevelCompleteState());
		stateManager
				.addGameState(myGameCompleteState = new GameCompleteState());
		stateManager.addGameState(myGameOverState = new GameOverState());
		stateManager.addGameState(myPauseState = new PauseGameState(
				myPlayState, "Paused"));
		stateManager.activateOnly(myMenuState);
	}

	public void initLevelManager() {
		levelManager = new LevelManager(this);
		String levelFilesDirectory = Resources.getString("levelFilesDirectory");
		String levelNamesFile = Resources.getString("levelNamesFile");
		levelManager.makeLevels(levelFilesDirectory, levelNamesFile);
	}

	public void update(long elapsedTime) {
		super.update(elapsedTime);
		gameControl.update();
	}

	public void setPlayState() {
		stateManager.switchTo(myPlayState);
	}

	public static void main(String[] args) {
		launch(new DropThis(), "player");
	}

}
