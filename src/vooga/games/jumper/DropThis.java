
package vooga.games.jumper;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.Random;

import vooga.engine.core.PlayField;
import vooga.engine.overlay.OverlayCreator;
import vooga.engine.overlay.OverlayTracker;
import vooga.engine.overlay.Stat;
import vooga.engine.resource.Resources;
import vooga.engine.resource.clock.GameClock;
import vooga.engine.resource.clock.GameClockException;
import vooga.engine.state.GameStateManager;
import vooga.engine.state.other.Pause;

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
 */


//TODO: try making it so that the blocks come in waves.  Add side jetpack thrusters.  Add jump capability.

public class DropThis extends vooga.engine.core.Game {

	private static final int UP_KEY = KeyEvent.VK_UP;
	private static final int CHEAT_KEY = KeyEvent.VK_C;
	private static final int LEFT_KEY = KeyEvent.VK_LEFT;
	private static final int RIGHT_KEY = KeyEvent.VK_RIGHT;
	private final static int GAME_WIDTH = 500;
	private final static int GAME_HEIGHT = 800;
	private double BLOCK_FREQUENCY_INCREASE_RATE = 0.000001;
	private double BLOCK_XVELOCITY_INCREASE_RATE = 0;
	private double BLOCK_VELOCITY_INCREASE_RATE = 0.001;
	private double myBlockVelocity = -2.0;
	private double fastBlockSpeedMultiplier = 2.0;

	private Point DOODLE_START = new Point (GAME_WIDTH / 2, -500);

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
		
		BufferedImage leftDoodle = Resources.getImage("leftDoodle");
		BufferedImage rightDoodle = Resources.getImage("rightDoodle");
		DoodleSprite player1 = new DoodleSprite(leftDoodle, DOODLE_START, leftDoodle, rightDoodle);
		myPlayers.add(player1);

		myPlayfield = new PlayField();
		myBackground = new ImageBackground(Resources.getImage("backgroundImage"), GAME_WIDTH, GAME_HEIGHT);
		myPlayfield.setBackground(myBackground);

		myPlayfield.addGroup(myBlocks);
		myPlayfield.addGroup(myPlayers);
		
		myNormalCollision = new DoodleToBlockCollision();
		myPlayfield.addCollisionGroup(myPlayers, myBlocks, myNormalCollision);

		
		
		OverlayCreator.setGame(this);
		myTrack = OverlayCreator.createOverlays("src/vooga/games/jumper/resources/overlays.xml");
		myScore = myTrack.getStat("score", new Integer(0));
		myPlayfield.addGroup(myTrack.getOverlayGroup("only"));

		myClock = new GameClock();
		try {
			myClock.start();
		} catch (GameClockException e) {
			e.printStackTrace();
		}
	}

	

	/**
	 * Returns the width of the Game
	 * @return int GameWidth
	 */
	public static int getGameWidth() {
		return GAME_WIDTH;
	}

	/**
	 * Returns the height of the Game
	 * @return int GameHeight
	 */
	public static int getGameHeight() {
		return GAME_HEIGHT;
	}

	/**
	 * Populate the BlockGroup with new BlockSprites
	 */
	private void createNewBlocks(){
		Random myRandom = new Random();

		double randomBlockOccurance = myRandom.nextDouble();
		Point randomLocation = new Point(myRandom.nextInt(GAME_WIDTH), GAME_HEIGHT);

		//make the correct type of block
		if (randomBlockOccurance < myBlockFrequency){
			BlockSprite block;

			//TODO: make a block factory to abstract this ugliness away
			if (myBlockCounter == myBlockCounterIncrement){
				block = new NormalBlock(Resources.getImage("platformGray"), randomLocation, 0, myBlockVelocity);
			} 
			
			else if(myBlockCounter == 2*myBlockCounterIncrement){
				block = new NormalBlock(Resources.getImage("platformRed"), randomLocation, 0, myBlockVelocity*fastBlockSpeedMultiplier);
			} 
			
			else if(myBlockCounter == 3*myBlockCounterIncrement){
				block = new NormalBlock(Resources.getImage("platformLightBlueWide"), randomLocation, 0, myBlockVelocity);
			} 
			
			else if(myBlockCounter == 4*myBlockCounterIncrement){
				block = new BreakingBlock(Resources.getImage("platformBreak"), randomLocation, 0, myBlockVelocity);
			} 
			
			else if(myBlockCounter == 5*myBlockCounterIncrement){
				block = new SpringBlock(Resources.getImage("platformSpringDouble"), randomLocation);				
			} 
			
			else if(myBlockCounter == 6*myBlockCounterIncrement){
				block = new JetpackBlock(Resources.getImage("jetpack"), randomLocation, 0, myBlockVelocity);
				myBlockCounter = 0;
			} 
			
			else {
				block = new NormalBlock(Resources.getImage("platformGreen"), randomLocation, 0, myBlockVelocity);
			}

			myBlockCounter++;
			myBlocks.add(block);
		}
	}

	/**
	 * Updates game values
	 * @param elapsedTime long time elapsed from last update
	 */
	public void update(long elapsedTime) {

		if((myClock.getTime()-jetpackStartTime)>totalJetpackTime & jetpackStartTime!=0){
			setJetpackOn(false);
		}
		createNewBlocks();
		checkForKeyPress();
		myPlayfield.update(elapsedTime);
		//myGameStateManager.update(elapsedTime);
		
		myScore.setStat((int) myClock.getTime());
		
		myBlockFrequency += BLOCK_FREQUENCY_INCREASE_RATE;
		myMaxBlockXVelocity += BLOCK_XVELOCITY_INCREASE_RATE;
		myBlockVelocity -= BLOCK_VELOCITY_INCREASE_RATE;
	}

	/**
	 * Ends game by clearing playfield and displaying final score message
	 * @param g Graphics2D on which to render messages
	 */
	private void endGame(Graphics2D g) {

		//stop clock if game is over
		if (myClock.isRunning()){
			try {
				myClock.pause();
			} catch (GameClockException e) {
				e.printStackTrace();
			}
		}
		myPlayfield.removeGroup(myPlayers);
		myPlayfield.removeGroup(myBlocks);
		myPlayfield.removeGroup(myOverlay);

		Point middle = new Point(GAME_WIDTH / 2, GAME_HEIGHT / 2);
		Point fontSize = new Point (GAME_WIDTH / 3, GAME_HEIGHT / 20);

		myPlayfield.addGroup(myTrack.getOverlayGroup("game over"));
	}

	/**
	 * Updates score based on time survived
	 */
	private void updateScore(){
		myScore.getStat();
	}
	
	private Long getClockTime(){
		return(myClock.getTime());
	}

	/**
	 * Listen for key presses to update player's location
	 */
	private void checkForKeyPress(){
		double jumpHeight = -15.0;
		DoodleSprite player = (DoodleSprite) myPlayers.getActiveSprite(); 
		int collisionNumber = myNormalCollision.getCollisionSide();
		
		/**
		 * If the doodle is standing on a block & the user presses up on the D-Pad...
		 */
		if (collisionNumber == 8 & keyPressed(UP_KEY)){
			player.setVerticalSpeed(jumpHeight);
		}
		
		/**
		 * Allow the user to walk to the Doodle left and right...
		 * 
		 * TODO:Use the event manager
		 */
		if (keyDown(RIGHT_KEY)){
			player.moveDoodle("right");
		}
		if (keyDown(LEFT_KEY)){
			player.moveDoodle("left");
		}
		if (keyDown(CHEAT_KEY)){
			player.setY(GAME_HEIGHT - player.getHeight());
		}

	}
	
	public static void setJetpackOn(boolean jetpackOn) {
		DropThis.jetpackOn = jetpackOn;
		if(jetpackOn == true){
			DropThis.jetpackStartTime = DropThis.myClock.getTime();
		}
	}

	public static boolean isJetpackOn() {
		return jetpackOn;
	}
	

	
	/**
	 * Render playfield sprites to the screen
	 * @param g Graphics2D on which to render images 
	 */
	public void render(Graphics2D g) {
		myPlayfield.render(g);
		//myGameStateManager.render(g);

		
		
		DoodleSprite myPlayer = (DoodleSprite) myPlayers.getActiveSprite();
		if (myPlayer.getVerticalSpeed() < 0 && myPlayer.getY() < 0){
			endGame(g);
		}
		else{
			updateScore();
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

