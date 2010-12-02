package vooga.engine.networking.client;

import vooga.engine.core.Game;
import vooga.engine.factory.LevelManager;
import vooga.engine.resource.Resources;
import vooga.engine.networking.client.states.*;

public class TicTacToe extends Game {

	private static final String LEVEL_FILES_DIRECTORY_STRING = "levelFilesDirectory";
	private static final String LEVEL_NAMES_FILE_STRING = "levelNamesFile";
	private PlayState playState;
//	private PauseState pauseState;
//	private StartMenuState startMenuState;
	private LevelManager levelManager;
//	private boolean showStart = true;

	public void initResources() {
		super.initResources();
		initLevelManager();
		initStates();
	}

	private void initStates() {
		playState = new PlayState(this, levelManager);
//		pauseState = new PauseState(this);
//		startMenuState = new StartMenuState(this);
//		stateManager.addGameState(playState, pauseState, startMenuState);
//		if (showStart)
//			stateManager.switchTo(startMenuState);
//		else
//			stateManager.switchTo(playState);
		stateManager.addGameState(playState);
		stateManager.switchTo(playState);
	}

	private void initLevelManager() {
		levelManager = new LevelManager(this);
		String levelFilesDirectory = Resources
				.getString(LEVEL_FILES_DIRECTORY_STRING);
		String levelNamesFile = Resources.getString(LEVEL_NAMES_FILE_STRING);
		levelManager.makeLevels(levelFilesDirectory, levelNamesFile);
	}

//	public void pauseGame() {
//		stateManager.switchTo(pauseState);
//	}
//
//	public void resumeGame() {
//		playState.onActivate();
//		stateManager.switchTo(playState);
//	}

	public void restartGame() {
		//showStart = false;
		initResources();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		launch(new TicTacToe());
	}

}