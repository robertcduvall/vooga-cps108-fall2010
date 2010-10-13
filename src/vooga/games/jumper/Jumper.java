
package vooga.games.jumper;


// JFC 
import java.awt.Graphics2D;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

//VOOGA
import vooga.engine.overlay.OverlayStat;
import vooga.engine.overlay.Stat;
import vooga.engine.resource.GameClock;
import vooga.engine.resource.GameClockException;
import vooga.engine.resource.ResourceHandler;
import vooga.engine.state.GameState;
import vooga.engine.state.GameStateManager;

// GTGE
import com.golden.gamedev.GameLoader;
import com.golden.gamedev.object.Background;
import com.golden.gamedev.object.GameFont;
import com.golden.gamedev.object.PlayField;
import com.golden.gamedev.object.Sprite;
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

public class Jumper extends vooga.engine.core.Game {

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

	private PlayField myPlayfield;

	private SpriteGroup myBlocks = new SpriteGroup("blocks");
	private SpriteGroup myPlayers = new SpriteGroup("players");
	
	private int blockTypeNormal     = 1;
	private int blockTypeSpring     = 2;
	private int blockTypeNotBroken  = 3;
	private int blockTypeJetpack    = 4;
	
	private GameState normalGameState;
	private GameState jetpackGameState;
	private GameStateManager myGameStateManager;
	private final int normalGameStateType = 1;
	private final int jetpackGameStateType = 2;
	
	private static boolean jetpackOn = false;
	private static long jetpackStartTime = 0;
	private long totalJetpackTime = 3000;

	



	private DoodleToBlockCollision myNormalCollision;

	private Background myBackground;

	private GameFont myFont;

	private static GameClock myClock;

	private Stat<Long> myScore;

	private SpriteGroup myOverlay;

	private int myBlockCounter = 0;
	private int myBlockCounterIncrement = 4;

	/**
	 *  Initialize all of the game instance variables
	 */
	public void initResources() {

		//this.showLogo();
		this.hideCursor();

		//setting up resource handler
		ResourceHandler.setGame(this);
		try{
			ResourceHandler.loadFile("vooga/games/jumper/resources/resourcelist.txt");
		} catch (IOException e){
			e.printStackTrace();
		}


		BufferedImage leftDoodle = ResourceHandler.getImage("leftDoodle");
		BufferedImage rightDoodle = ResourceHandler.getImage("rightDoodle");
		DoodleSprite player1 = new DoodleSprite(leftDoodle, DOODLE_START, leftDoodle, rightDoodle);
		myPlayers.add(player1);

		myPlayfield = new PlayField();
		myBackground = new ImageBackground(ResourceHandler.getImage("backgroundImage"), GAME_WIDTH, GAME_HEIGHT);
		myPlayfield.setBackground(myBackground);

		
		/*myGameStateManager = new GameStateManager();

		jetpackGameState = new JumperGameState(myPlayers, jetpackGameStateType);
		myGameStateManager.addGameState(jetpackGameState);
		//myGameStateManager.addGameState(normalGameState);
		myGameStateManager.activateOnly(jetpackGameState);
		*/

		myPlayfield.addGroup(myBlocks);
		myPlayfield.addGroup(myPlayers);

		myNormalCollision = new DoodleToBlockCollision();
		myPlayfield.addCollisionGroup(myPlayers, myBlocks, myNormalCollision);

		myFont = fontManager.getFont(ResourceHandler.getImages("font", 20, 3),
				" !            .,0123" + "456789:   -? ABCDEFG"
				+ "HIJKLMNOPQRSTUVWXYZ ");

		myOverlay = new SpriteGroup("overlay");
		myScore = new Stat<Long>((long) 0);
		OverlayStat score = new OverlayStat("SCORE : ", myScore);
		score.setFont(myFont);
		myOverlay.add(score);
		myPlayfield.addGroup(myOverlay);

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
	public void createNewBlocks(){
		Random myRandom = new Random();

		double randomBlockOccurance = myRandom.nextDouble();
		Point randomLocation = new Point(myRandom.nextInt(GAME_WIDTH), GAME_HEIGHT);
		/*
		 * randomXVelocity is currently not used by any blocks. (oct. 9 2:16pm -- devon)
		 */
		double randomXVelocity = myRandom.nextDouble() * (myMaxBlockXVelocity) - (myMaxBlockXVelocity / 2);

		//make the correct type of block
		if (randomBlockOccurance < myBlockFrequency){
			Sprite block;
			if (myBlockCounter == myBlockCounterIncrement){
				//Point velocity = new Point();  << what is this? --Devon
				block = new BlockSprite(ResourceHandler.getImage("platformGray"), randomLocation, myBlockVelocity, myBlockVelocity);
				block.setID(blockTypeNormal);
				myBlockCounter++;

			} else if(myBlockCounter == 2*myBlockCounterIncrement){
				block = new BlockSprite(ResourceHandler.getImage("platformRed"), randomLocation, 0, myBlockVelocity*fastBlockSpeedMultiplier);
				block.setID(blockTypeNormal);

				myBlockCounter++;

			} else if(myBlockCounter == 3*myBlockCounterIncrement){
				block = new BlockSprite(ResourceHandler.getImage("platformLightBlueWide"), randomLocation, 0, myBlockVelocity);
				block.setID(blockTypeNormal);
				myBlockCounter++;
			} else if(myBlockCounter == 4*myBlockCounterIncrement){
				block = new BlockSprite(ResourceHandler.getImage("platformBreak"), randomLocation, 0, myBlockVelocity);
				block.setID(blockTypeNotBroken);
				myBlockCounter++;
			} else if(myBlockCounter == 5*myBlockCounterIncrement){
				block = new BlockSprite(ResourceHandler.getImage("platformSpringDouble"), randomLocation, 0, myBlockVelocity);
				block.setID(blockTypeSpring);
				myBlockCounter++;
			} else if(myBlockCounter == 6*myBlockCounterIncrement){
				block = new BlockSprite(ResourceHandler.getImage("jetpack"), randomLocation, 0, myBlockVelocity);
				block.setID(blockTypeJetpack);
				myBlockCounter = 0;
			} else {
				block = new BlockSprite(ResourceHandler.getImage("platformGreen"), randomLocation, 0, myBlockVelocity);
				block.setID(blockTypeNormal);
				myBlockCounter++;
			}

			myBlocks.add(block);
		}
	}

	/**
	 * Updates game values
	 * @param elapsedTime long time elapsed from last update
	 */
	public void update(long elapsedTime) {
		//Sprite myPlayerTest = myPlayfield.getGroup(myPlayers);

		if((myClock.getTime()-jetpackStartTime)>totalJetpackTime & jetpackStartTime!=0){
			setJetpackOn(false);
		}
		createNewBlocks();
		checkForKeyPress();
		myPlayfield.update(elapsedTime);
		//myGameStateManager.update(elapsedTime);
		
		myBlockFrequency += BLOCK_FREQUENCY_INCREASE_RATE;
		myMaxBlockXVelocity += BLOCK_XVELOCITY_INCREASE_RATE;
		myBlockVelocity -= BLOCK_VELOCITY_INCREASE_RATE;
	}

	/**
	 * Ends game by clearing playfield and displaying final score message
	 * @param g Graphics2D on which to render messages
	 */
	public void endGame(Graphics2D g) {

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

		myFont.drawString(g, "GAME OVER!!!", myFont.CENTER, middle.x - (fontSize.x / 2), middle.y - (fontSize.y/2),  fontSize.x);
		myFont.drawString(g, "FINAL SCORE: " + myScore.getStat(), myFont.CENTER, middle.x - (fontSize.x / 2), middle.y + (fontSize.y / 2), fontSize.x);
	}

	/**
	 * Updates score based on time survived
	 */
	public void updateScore(){
		myScore.setStat(myClock.getTime());
	}
	
	public Long getClockTime(){
		return(myClock.getTime());
	}

	/**
	 * Listen for key presses to update player's location
	 */
	public void checkForKeyPress(){
		double jumpHeight = -15.0;
		DoodleSprite player = (DoodleSprite) myPlayers.getActiveSprite(); 
		int collisionNumber = myNormalCollision.getCollisionSide();
		
		/**
		 * If the doodle is standing on a block & the user presses up on the D-Pad...
		 */
		if (collisionNumber == 8 & keyPressed(KeyEvent.VK_UP)){
			player.setVerticalSpeed(jumpHeight);
		}
		
		/**
		 * Allow the user to walk to the Doodle left and right...
		 */
		if (keyDown(KeyEvent.VK_RIGHT)){
			player.goRight();
		}
		if (keyDown(KeyEvent.VK_LEFT)){
			player.goLeft();
		}
		if (keyDown(KeyEvent.VK_C)){
			player.setY(GAME_HEIGHT - player.getHeight());
		}

	}
	
	public static void setJetpackOn(boolean jetpackOn) {
		Jumper.jetpackOn = jetpackOn;
		if(jetpackOn == true){
			Jumper.jetpackStartTime = Jumper.myClock.getTime();
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
		GameLoader game = new GameLoader();
		Jumper jump = new Jumper();
		game.setup(jump, new Dimension(GAME_WIDTH,GAME_HEIGHT), false);
		game.start();
	}
}

