package vooga.examples.control;

import java.awt.event.KeyEvent;

import vooga.engine.control.Control;
import vooga.engine.control.KeyboardControl;
import vooga.engine.core.Game;
import vooga.engine.factory.LevelManager;
import vooga.engine.resource.Resources;
import vooga.games.doodlejump.states.PauseState;
import vooga.games.doodlejump.states.PlayState;
import vooga.games.doodlejump.states.StartMenuState;

/**
 * The DoodleGame class creates a game based on the popular iPhone app Doodle
 * Jump
 * 
 * @author Adam Cue, Marcus Molchany, Nick Straub
 * 
 */
public class ControlGameExample extends Game {

	private static final String LEVEL_FILES_DIRECTORY_STRING = "levelFilesDirectory";
	private static final String LEVEL_NAMES_FILE_STRING = "levelNamesFile";
	private PlayState playState;
	private PauseState pauseState;
	private StartMenuState startMenuState;
	private LevelManager levelManager;
	private Control control;
	private boolean showStart = true;

	public void initResources() {
		super.initResources();
		initLevelManager();
		initStates();
		initControl();
	}

	private void initControl(){
		control = new KeyboardControl(this, this);
		control.addInput(KeyEvent.VK_ENTER, "resumeGame", "vooga.examples.control.ControlGameExample");
	}

	private void initStates() {
		playState = new PlayState(this, levelManager);
		pauseState = new PauseState(this);
		startMenuState = new StartMenuState(this);
		stateManager.addGameState(playState, pauseState, startMenuState);
		if (showStart)
			stateManager.switchTo(startMenuState);
		else
			stateManager.switchTo(playState);
	}

	private void initLevelManager() {
		levelManager = new LevelManager(this);
		String levelFilesDirectory = Resources
				.getString(LEVEL_FILES_DIRECTORY_STRING);
		String levelNamesFile = Resources.getString(LEVEL_NAMES_FILE_STRING);
		levelManager.makeLevels(levelFilesDirectory, levelNamesFile);
	}
	public void pauseGame() {
		stateManager.switchTo(pauseState);
	}

	public void resumeGame() {
		playState.onActivate();
		stateManager.switchTo(playState);
	}

	public void restartGame() {
		showStart = false;
		initResources();
	}
	
	@Override
	public void update(long elapsedTime){
		super.update(elapsedTime);
		control.update();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		launch(new ControlGameExample(), "player");
	}

}
