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
import vooga.games.grandius.states.StartNewLevelState;

import com.golden.gamedev.object.GameFont;

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

	private GrandiusMenuState myMenuState;
	private PlayState myPlayState;
	private LevelCompleteState myLevelCompleteState;
	private ShoppingLevelState myShoppingLevelState;
	private StartNewLevelState myStartNewLevelState;
	private GameCompleteState myGameCompleteState;
	private GameOverState myGameOverState;
	private LevelManager levelManager;
	private Control gameControl;
	private PauseGameState myPauseGameState;

	@Override
	public void initResources() {
		super.initResources();
		initControls();
	}

	public void initControls() {
		gameControl = new KeyboardControl(this, this);
		gameControl.addInput(KeyEvent.VK_P, "pauseGame", "vooga.games.grandius.DropThis");
		gameControl.addInput(KeyEvent.VK_U, "unpauseGame", "vooga.games.grandius.DropThis");
	}
	
	public void pauseGame() {
		this.getGameStateManager().switchTo(myPauseGameState);
	}
	
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
		gameStates.add(myMenuState = new GrandiusMenuState(this)); // Default state.
		gameStates.add(myPlayState = new PlayState(levelManager, this));
		gameStates.add(myLevelCompleteState = new LevelCompleteState());
		gameStates.add(myGameCompleteState = new GameCompleteState());
		gameStates.add(myShoppingLevelState = new ShoppingLevelState(this));
		gameStates.add(myStartNewLevelState = new StartNewLevelState());
		gameStates.add(myGameOverState = new GameOverState());
		gameStates.add(myPauseGameState = new PauseGameState(myPlayState, "Paused"));
		GameState[] gameStatesArray = new GameState[gameStates.size()];
		for (int i = 0; i < gameStates.size(); i++) {
			gameStatesArray[i] = gameStates.get(i);
		}
		stateManager.addGameState(gameStatesArray);
		stateManager.switchTo(myPlayState);
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

	//Moving to PlayState
//	private void checkCheats() {
//		if (keyPressed(KeyEvent.VK_ENTER)) {
//			JFrame frame = new JFrame();
//			String userInput = (String) JOptionPane.showInputDialog(frame,
//					"Enter a cheat code:", "Cheats", JOptionPane.PLAIN_MESSAGE);
//			if (userInput.equals(Resources.getString("invincibility")))
//				player.setInvincible();
//			else if (userInput.equals(Resources.getString("skipLevel")))
//				player.skipLevel();
//			else if (userInput.equals(Resources.getString("extraPoints")))
//				player.updateScore(1000000);
//			else if (userInput.equals(Resources.getString("extraCash")))
//				player.updateCash(5000);
//			else if (userInput.equals(Resources.getString("activateMissile")))
//				player.setMissileActive();
//		}
//	}

	//TODO could these two methods be removed somehow? they're used
	//to deal with collisions...
//	public Player getPlayer() {
//		return this.myPlayState.getPlayer();
//	}
	
	public PlayState getPlayState() {
		return this.myPlayState;
	}
	
	public static void main(String[] args) {
		launch(new DropThis());
	}
}
