package vooga.games.doodlejump;

import vooga.engine.core.Game;
import vooga.engine.factory.LevelManager;
import vooga.engine.resource.Resources;
import vooga.games.doodlejump.BlahThis;
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
public class BlahThis extends Game {

	private PlayState playState;
	private PauseState pauseState;
	private StartMenuState startMenuState;
	private LevelManager levelManager;
	private boolean showStart = true;

	public void initResources() {
		super.initResources();
		initLevelManager();
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
		String levelFilesDirectory = Resources.getString("levelFilesDirectory");
		String levelNamesFile = Resources.getString("levelNamesFile");
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

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		launch(new BlahThis());
	}

}
