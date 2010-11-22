package vooga.games.grandius;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import vooga.engine.control.Control;
import vooga.engine.control.KeyboardControl;
import vooga.engine.core.Game;
import vooga.engine.factory.LevelManager;
import vooga.engine.resource.Resources;
import vooga.engine.state.GameState;
import vooga.engine.state.PauseGameState;
import vooga.games.grandius.states.GameCompleteState;
import vooga.games.grandius.states.GameOverState;
import vooga.games.grandius.states.GrandiusMenuState;
import vooga.games.grandius.states.LevelCompleteState;
import vooga.games.grandius.states.PlayState;
import vooga.games.grandius.states.ShoppingLevelState;

/**
 * Grandius is a side-scrolling space shooter. The object of each level is to
 * destroy all enemies. The player is the red ship on the left side of the
 * screen, and he or she can use various weapons to destroy enemies. The "boss"
 * of each level can only be destroyed once all 5 "mini-bosses" have been
 * destroyed.
 * 
 * @author Se-Gil Feldsott, John Kline, Bhawana Singh
 * @version 3.0
 */
public class DropThis extends Game {

	private PlayState myPlayState;
	private LevelManager levelManager;
	private Control gameControl;
	private PauseGameState myPauseGameState;

	/**
	 * Initializes Grandius Resources.
	 */
	@Override
	public void initResources() {
		super.initResources();
		initControls();
	}

	/**
	 * Initializes the controls relevant to the Game.
	 */
	 public void initControls() {
		 gameControl = new KeyboardControl(this, this);
		 gameControl.addInput(KeyEvent.VK_P, "pauseGame", "vooga.games.grandius.DropThis");
		 gameControl.addInput(KeyEvent.VK_U, "unpauseGame", "vooga.games.grandius.DropThis");
	 }

	 /**
	  * Pauses the game.
	  */
	 public void pauseGame() {
		 this.getGameStateManager().switchTo(myPauseGameState);
	 }

	 /**
	  * Unpauses the game.
	  */
	 public void unpauseGame() {
		 this.getGameStateManager().switchTo(myPlayState);
	 }

	 /**
	  * Initialize the different GameStates possible in Grandius.
	  */
	 @Override
	 public void initGameStates() {
		 super.initGameStates();
		 initLevelManager();
		 List<GameState> gameStates = new ArrayList<GameState>();
//		 gameStates.add(myMenuState = new GrandiusMenuState(this)); // Default state.
		 gameStates.add(new GrandiusMenuState(this)); // Default state.
		 gameStates.add(myPlayState = new PlayState(levelManager, this));
//		 gameStates.add(myLevelCompleteState = new LevelCompleteState(this));
		 gameStates.add(new LevelCompleteState(this));
//		 gameStates.add(myGameCompleteState = new GameCompleteState(this));
		 gameStates.add(new GameCompleteState(this));
//		 gameStates.add(myShoppingLevelState = new ShoppingLevelState(this));
		 gameStates.add(new ShoppingLevelState(this));
//		 gameStates.add(myGameOverState = new GameOverState(this));
		 gameStates.add(new GameOverState(this));
		 gameStates.add(myPauseGameState = new PauseGameState(myPlayState, "Paused"));
		 GameState[] gameStatesArray = new GameState[gameStates.size()];
		 for (int i = 0; i < gameStates.size(); i++) {
			 gameStatesArray[i] = gameStates.get(i);
		 }
		 stateManager.addGameState(gameStatesArray);
		 //stateManager.switchTo(myGameCompleteState);
	 }

	 /**
	  * Initialize the LevelManager for Grandius.
	  */
	 private void initLevelManager() {
		 levelManager = new LevelManager(this);
		 String levelFilesDirectory = Resources.getString("levelFilesDirectory");
		 String levelNamesFile = Resources.getString("levelNamesFile");
		 levelManager.makeLevels(levelFilesDirectory, levelNamesFile);
	 }

	 @Override
	 public void update(long elapsedTime) {
		 super.update(elapsedTime);
		 gameControl.update();
	 }

	 /**
	  * Returns the Grandius PlayState for use in collision management.
	  */
	 public PlayState getPlayState() {
		 return this.myPlayState;
	 }

	 /**
	  * Starts the PlayState.
	  */
	 public void startPlayState() {
		 stateManager.switchTo(myPlayState);
	 }

	 /**
	  * Main launching method for Grandius.
	  */
	 public static void main(String[] args) {
		 launch(new DropThis());
	 }
}
