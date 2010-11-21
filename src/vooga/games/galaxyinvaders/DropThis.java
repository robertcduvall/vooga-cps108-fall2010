package vooga.games.galaxyinvaders;

import java.awt.Color;
import java.awt.event.KeyEvent;

import vooga.engine.control.Control;
import vooga.engine.control.KeyboardControl;
import vooga.engine.core.Game;
import vooga.engine.core.PlayField;
import vooga.engine.factory.LevelManager;
import vooga.engine.resource.Resources;
import vooga.engine.state.BasicTextGameState;
import vooga.engine.state.GameState;
import vooga.engine.state.PauseGameState;
import vooga.games.galaxyinvaders.states.GalaxyGameState;
import vooga.games.galaxyinvaders.states.PauseState;
import vooga.games.galaxyinvaders.states.PlayState;

import com.golden.gamedev.object.SpriteGroup;


/**
 * The GalaxyInvaders class is the main Game class for Galaxy Invaders. It keeps track of the sprites, 
 * and has update called every turn by GoldenT, from which it calls update methods on all its sprite
 * groups. There are no command line arguments to run GalaxyInvaders.
 * 
 * @author Drew Sternesky, Kate Yang, Nick Hawthorne
 *
 */
public class DropThis extends Game {

	private static LevelManager levelManager;
	private PlayField playfield;
	
	private GameState play;
	private GameState pause;
	private GameState gameOver;

	/**
	 * Method inherited from Game. Initializes the game state and all the sprites in the game.
	 */
	public void initResources() {
		super.initResources();
		initLevelManager();
		playfield = levelManager.loadFirstLevel();
		play = new PlayState(this, playfield);
		pause = new PauseState(this, play, Resources.getString("pauseStateText"), Color.WHITE);
		gameOver = new BasicTextGameState(Resources.getString("gameOverText"), Color.WHITE);
		stateManager.addGameState(play, pause, gameOver);
		stateManager.switchTo(pause);
	}

	private void initLevelManager() {
		levelManager = new LevelManager(this);
		String levelFilesDirectory = Resources.getString("levelFilesDirectory");
		String levelNamesFile = Resources.getString("levelNamesFile");
		levelManager.makeLevels(levelFilesDirectory,levelNamesFile);		
	}


	public GameState getPlayGameState(){
		return play;
	}
	
	public void toggle(){
		stateManager.toggle(pause);
		stateManager.toggle(play);
	}
	
	public void gameOver(){
		stateManager.switchTo(gameOver);
	}
	
	public void startNewGame(){
		this.finish();
		DropThis.main(null);
	}

	public void switchLevel(){
		levelManager.loadNextLevel();
	}
	
	/**
	 * Java main method
	 * 
	 * @param args do nothing
	 */
	public static void main(String[] args) {
		launch(new DropThis());
	}

}