
package vooga.games.jumper;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.Random;
import java.util.ResourceBundle;

import vooga.engine.core.PlayField;
import vooga.engine.factory.LevelManager;
import vooga.engine.level.LevelField;
import vooga.engine.overlay.OverlayCreator;
import vooga.engine.overlay.OverlayTracker;
import vooga.engine.overlay.Stat;
import vooga.engine.resource.Resources;
import vooga.engine.resource.clock.GameClock;
import vooga.engine.resource.clock.GameClockException;
import vooga.engine.state.GameStateManager;
import vooga.engine.state.PauseGameState;
import vooga.engine.state.other.Pause;
import vooga.games.asteroids.states.PlayState;
import vooga.games.jumper.collisions.DoodleToBlockCollision;
import vooga.games.jumper.sprites.BlockSprite;
import vooga.games.jumper.sprites.BreakingBlock;
import vooga.games.jumper.sprites.DoodleSprite;
import vooga.games.jumper.sprites.JetpackBlock;
import vooga.games.jumper.sprites.NormalBlock;
import vooga.games.jumper.sprites.SpringBlock;
import vooga.games.jumper.states.PlayGameState;

import com.golden.gamedev.object.Background;
import com.golden.gamedev.object.GameFont;
import com.golden.gamedev.object.SpriteGroup;
import com.golden.gamedev.object.background.ImageBackground;




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

	private static final int WIDTH = 500;
	private static final int HEIGHT = 800;
	
	private LevelManager levelManager;

	PlayGameState playState;
	PauseGameState pauseState;

	

	private static final int UP_KEY = KeyEvent.VK_UP;
	private static final int CHEAT_KEY = KeyEvent.VK_C;
	private static final int LEFT_KEY = KeyEvent.VK_LEFT;
	private static final int RIGHT_KEY = KeyEvent.VK_RIGHT;
	private double BLOCK_FREQUENCY_INCREASE_RATE = 0.000001;
	private double BLOCK_XVELOCITY_INCREASE_RATE = 0;
	private double BLOCK_VELOCITY_INCREASE_RATE = 0.001;
	private double blockVelocity = -2.0;
	private double fastBlockSpeedMultiplier = 2.0;

	private Point DOODLE_START = new Point (WIDTH / 2, -500);

	private double myMaxBlockXVelocity = 0.4;    
	private double myBlockFrequency = 0.04;
	
	private static Pause myPause;

	private PlayField myPlayfield;

	private SpriteGroup myBlocks = new SpriteGroup("blocks");
	private SpriteGroup myPlayers = new SpriteGroup("players");

	private OverlayTracker myTrack;
	private String myFontString = " !            .,0123456789:   -? ABCDEFGHIJKLMNOPQRSTUVWXYZ ";
	
//	private GameState playGameState;
//	private GameState pauseGameState;
	private GameStateManager myGameStateManager;
	
	private static boolean jetpackOn = false;
	private static long jetpackStartTime = 0;
	private long totalJetpackTime = 3000;

	



	private DoodleToBlockCollision myNormalCollision;

	private Background myBackground;

	private GameFont myFont;

	private static GameClock myClock;

	private Stat<Integer> myScore;
	private Stat<Long> myFinalScore;

	private SpriteGroup myOverlay;

	private int myBlockCounter = 0;
	private int myBlockCounterIncrement = 4;

	/**
	 *  Initialize all of the game instance variables
	 */
	public void initResources() {

		this.hideCursor();
		super.initResources();

		Resources.loadInt("gameHeight", HEIGHT);
		Resources.loadInt("gameWidth", WIDTH);
		
		
		initLevelManager();
		PlayField levelPlayField = levelManager.loadFirstLevel();
		initGameStates(levelPlayField);
		resumeGame();
		
	}

	private void initGameStates(PlayField levelPlayField) {
		playState = new PlayGameState(this, levelPlayField);
		pauseState = new PauseGameState(playState);
		stateManager.addGameState(playState, pauseState);
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
	
	public static void setJetpackOn(boolean jetpackOn) {
		DropThis.jetpackOn = jetpackOn;
		if(jetpackOn == true){
			DropThis.jetpackStartTime = DropThis.myClock.getTime();
		}
	}


	/**
	 * Main method which loads the game
	 * @param args String[] of arguments from the command line
	 */    
	public static void main(String[] args) {
		launch(new DropThis());
	}
}

