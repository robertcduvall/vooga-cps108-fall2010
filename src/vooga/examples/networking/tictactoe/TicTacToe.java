package vooga.examples.networking.tictactoe;

import vooga.engine.core.Game;
import vooga.engine.core.PlayField;
import vooga.engine.factory.LevelManager;
import vooga.engine.factory.LevelParser;
import vooga.engine.resource.Resources;
import vooga.examples.networking.tictactoe.states.PlayState;
import vooga.examples.networking.tictactoe.states.WaitingState;

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
		TicTacToeConnection connection = null;
		try{
			connection = new TicTacToeConnection();
		}
		catch(Exception e){
			System.out.println("TicTacToe Error: "+ e.getMessage());
			System.exit(1);
		}
		playState = new PlayState(this, levelManager, connection);
		LevelParser levelParser = new LevelParser();
		PlayField waitField = levelParser.getPlayfield(Resources.getString("waitXml"), this);
		WaitingState waitState = new WaitingState(this, connection, waitField, playState);
		stateManager.addGameState(waitState);
//		pauseState = new PauseState(this);
//		startMenuState = new StartMenuState(this);
//		stateManager.addGameState(playState, pauseState, startMenuState);
//		if (showStart)
//			stateManager.switchTo(startMenuState);
//		else
//			stateManager.switchTo(playState);
		stateManager.addGameState(playState);
		stateManager.switchTo(waitState);
	}

	private void initLevelManager() {
		levelManager = new LevelManager(this);
		String levelFilesDirectory = Resources.getString(LEVEL_FILES_DIRECTORY_STRING);
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