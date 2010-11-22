
package vooga.games.jumper;

import java.awt.Color;
import java.awt.event.KeyEvent;

import vooga.engine.control.Control;
import vooga.engine.control.KeyboardControl;
import vooga.engine.core.PlayField;
import vooga.engine.factory.LevelManager;
import vooga.engine.resource.Resources;
import vooga.engine.state.PauseGameState;
import vooga.games.jumper.states.PlayGameState;
import vooga.games.jumper.states.StartingMenuGameState;




/**
 * This is an example game called Jumper that we created to demonstrate the VOOGA Game Engine.
 * The purpose of the game is to move your "Doodle" around to avoid getting carried out of the top
 * of the screen for as long as possible by using the left and right arrow keys to avoid blocks.
 * CHEAT CODE: press "C" to move your "Doodle" to the bottom of the screen
 * @author BrianSimel & CodyKolodziejzyk & DevonTownsend
 */

/**
 * it seems like the first time you play, only the first brown block will break.  After that they
 * behave as normal blocks and you can only stand on them.
 * 
 * @author Brian
 */


public class DropThis extends vooga.engine.core.Game {

	private LevelManager levelManager;

	PlayGameState playState;
	PauseGameState pauseState;
	StartingMenuGameState menuState;
	
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

	private void initGameStates(PlayField pf) {
		playState = new PlayGameState(this, pf);
		pauseState = new PauseGameState(playState, "THE GAME IS PAUSED", Color.BLUE);
		menuState = new StartingMenuGameState(this);
		stateManager.addGameState(playState, pauseState);
	}

	private void initControls(){
		 gameControl = new KeyboardControl(this, this);
		 gameControl.addInput(KeyEvent.VK_P, "pauseGame", "vooga.games.jumper.DropThis");
		 gameControl.addInput(KeyEvent.VK_R, "resumeGame", "vooga.games.jumper.DropThis");
	}
	
	private void initLevelManager() {
		levelManager = new LevelManager(this);
		String levelFilesDirectory = Resources.getString("levelFilesDirectory");
		String levelNamesFile = Resources.getString("levelNamesFile");
		levelManager.makeLevels(levelFilesDirectory,levelNamesFile);		
	}	
	
	public void resumeGame() {		
		stateManager.activateOnly(playState);
	}
	
	public void pauseGame() {	
		stateManager.activateOnly(pauseState);
	}

	/**
	 * Main method which loads the game
	 * @param args String[] of arguments from the command line
	 */    
	public static void main(String[] args) {
		launch(new DropThis());
	}
	
	 @Override
	 public void update(long elapsedTime) {
		 super.update(elapsedTime);
		 gameControl.update();
	 }
}

