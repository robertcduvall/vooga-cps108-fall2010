
package vooga.games.jumper;

import java.awt.Color;
import java.awt.event.KeyEvent;

import vooga.engine.control.Control;
import vooga.engine.control.KeyboardControl;
import vooga.engine.core.PlayField;
import vooga.engine.factory.LevelManager;
import vooga.engine.resource.Resources;
import vooga.engine.state.BasicTextGameState;
import vooga.engine.state.PauseGameState;
import vooga.games.jumper.states.PlayGameState;




/**
 * This is an example game called Jumper that we created to demonstrate the VOOGA Game Engine.
 * The purpose of the game is to move your "Doodle" around to avoid getting carried out of the top
 * of the screen for as long as possible by using the left and right arrow keys to avoid blocks.
 * CHEAT CODE: press "C" to move your "Doodle" to the bottom of the screen
 * @author BrianSimel & CodyKolodziejzyk & DevonTownsend
 */


public class Blah extends vooga.engine.core.Game {

	private static final String GAME_OVER_MESSSAGE = "YOU LOST";

	private static final String PAUSE_GAME_MESSAGE = "THE GAME IS PAUSED";

	private LevelManager levelManager;

	PlayGameState playState;
	PauseGameState pauseState;
	BasicTextGameState deathState;
	BasicTextGameState endState;
	
	
	
	private Control gameControl;

	/**
	 *  Initialize all of the game instance variables
	 */
	public void initResources() {

		this.hideCursor();
		super.initResources();

		initLevelManager();
		PlayField levelPlayField = levelManager.loadFirstLevel();
		initGameStates(levelPlayField);
		initControls();
//		loadMenuScreen();
		resumeGame();
		
	}
	
//	private void loadMenuScreen(){
//		stateManager.activateOnly(menuState);
//	}

	/**
	 * Initialize the game states.
	 */
	
	private void initGameStates(PlayField pf) {
		playState = new PlayGameState(this, pf, this);
		pauseState = new PauseGameState(playState, PAUSE_GAME_MESSAGE, Color.BLUE);
		deathState = new BasicTextGameState(GAME_OVER_MESSSAGE, Color.BLUE);
		stateManager.addGameState(playState, pauseState, deathState);
	}

	/**
	 * Create new game control and assign certain keys to activate gamestates
	 */
	
	private void initControls(){
		 gameControl = new KeyboardControl(this, this);
		 gameControl.addInput(KeyEvent.VK_P, "pauseGame", "vooga.games.jumper.Blah");
		 gameControl.addInput(KeyEvent.VK_R, "resumeGame", "vooga.games.jumper.Blah");
	}
	
	/**
	 * Initialize the level parser
	 */
	
	private void initLevelManager() {
		levelManager = new LevelManager(this);
		String levelFilesDirectory = Resources.getString("levelFilesDirectory");
		String levelNamesFile = Resources.getString("levelNamesFile");
		levelManager.makeLevels(levelFilesDirectory,levelNamesFile);		
	}	
	
	/**
	 * Active the playstate gamestate
	 */
	
	public void resumeGame() {		
		stateManager.activateOnly(playState);
	}
	
	/**
	 * Activate the pausestate gamestate
	 */
	
	public void pauseGame() {	
		stateManager.activateOnly(pauseState);
	}
	
	/**
	 * Activate the deathstate gamestate
	 */
	
	public void deathGame() {
		stateManager.activateOnly(deathState);
	}
	
	/**
	 * Main method which loads the game
	 * @param args String[] of arguments from the command line
	 */    
	public static void main(String[] args) {
		launch(new Blah(), "Guest");
	}
	
	 @Override
	 public void update(long elapsedTime) {
		 super.update(elapsedTime);
		 gameControl.update();
	 }
}

