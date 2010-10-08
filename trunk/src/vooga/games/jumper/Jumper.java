
package vooga.games.jumper;


// JFC 

import java.awt.Graphics2D;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Random;

//VOOGA
import vooga.engine.overlay.OverlayStat;
import vooga.engine.overlay.OverlayString;
import vooga.engine.overlay.Stat;
import vooga.engine.resource.GameClock;
import vooga.engine.resource.GameClockException;
import vooga.engine.resource.ResourceHandler;

// GTGE
import com.golden.gamedev.GameLoader;
import com.golden.gamedev.object.Background;
import com.golden.gamedev.object.CollisionManager;
import com.golden.gamedev.object.GameFont;
import com.golden.gamedev.object.PlayField;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;


/**
 * This is an example game we are working on to demonstrate the VOOGA game engine.
 * Skeleton for this class was taken from GTGE Tutorial 5
 * More details to come...
 * @author BrianSimel
 */

public class Jumper extends vooga.engine.core.Game {

    private final static int GAME_WIDTH = 500;
    private final static int GAME_HEIGHT = 800;
    private double BLOCK_FREQUENCY_INCREASE_RATE = 0.000001;
    private double BLOCK_XVELOCITY_INCREASE_RATE = 0.001;
    private double BLOCK_YVELOCITY_INCREASE_RATE = 0.001;
    private double blockYVelocity = -2.0;


    private Point DOODLE_START = new Point (GAME_WIDTH / 2, -500);

    private double myMaxBlockXVelocity = 6;    
    private double myMaxBlockYVelocity = 2;
    private double myBlockFrequency = 0.05;
    
    private PlayField myPlayfield;

    private SpriteGroup myBlocks = new SpriteGroup("blocks");
    private SpriteGroup myPlayers = new SpriteGroup("players");
    
    private DoodleToBlockCollision myCollision;
    
    private Background myBackground;
    
    private GameFont myFont;
    
    private GameClock myClock;
    
    private Stat<Long> myScore;

    private SpriteGroup myOverlay;
    
    private int myBlockCounter = 0;

    
    /**
     *  Initialize all of the game instance variable
     */
    public void initResources() {

    	this.showLogo();
    	this.hideCursor();
    	
        //setting up resource handler
        ResourceHandler.setGame(this);
        try{
            ResourceHandler.loadFile("vooga/games/jumper/resources/resourcelist.txt");
        } catch (IOException e){
            e.printStackTrace();
        }

        
        DoodleSprite player1 = new DoodleSprite(ResourceHandler.getImage("crop"), DOODLE_START);
        createNewBlocks();
        
        myPlayers.add(player1);

        myPlayfield = new PlayField();
        myBackground = new com.golden.gamedev.object.background.ImageBackground(ResourceHandler.getImage("backgroundImage"), GAME_WIDTH, GAME_HEIGHT);
        myPlayfield.setBackground(myBackground);


        myPlayfield.addGroup(myPlayers);
        myPlayfield.addGroup(myBlocks);

        myCollision = new DoodleToBlockCollision();
        myPlayfield.addCollisionGroup(myPlayers, myBlocks, myCollision);
        
        myFont = fontManager.getFont(ResourceHandler.getImages("font", 20, 3),
                " !            .,0123" + "456789:   -? ABCDEFG"
                        + "HIJKLMNOPQRSTUVWXYZ ");

        myOverlay = new SpriteGroup("overlay");
        myScore = new Stat<Long>((long) 0);
        OverlayStat os = new OverlayStat("SCORE : ", myScore);
        os.setFont(myFont);
        myOverlay.add(os);
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
     * @return in GameHeight
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
        int randomXLocation = myRandom.nextInt(GAME_WIDTH);
        double randomXVelocity = myRandom.nextDouble() * (myMaxBlockXVelocity) - (myMaxBlockXVelocity / 2);

        
        if (randomBlockOccurance < myBlockFrequency){
            Sprite block;
        	if (myBlockCounter == 4){
                block = new BlockSprite(ResourceHandler.getImage("platformGray"), new Point(randomXLocation, GAME_HEIGHT));
                block.setSpeed(randomXVelocity, blockYVelocity);
                myBlockCounter = 6;

            } else if(myBlockCounter == 9){
            	block = new BlockSprite(ResourceHandler.getImage("platformRed"), new Point(randomXLocation, GAME_HEIGHT));
                block.setSpeed(0, blockYVelocity*2);
                myBlockCounter = 11;
            	
            } else if(myBlockCounter == 12){
            	block = new BlockSprite(ResourceHandler.getImage("platformLightBlueWide"), new Point(randomXLocation, GAME_HEIGHT));
                block.setSpeed(0, blockYVelocity*2);
                myBlockCounter = 0;
            	
            }
        	else {
                block = new BlockSprite(ResourceHandler.getImage("platformGreen"), new Point(randomXLocation, GAME_HEIGHT));
                block.setSpeed(0, blockYVelocity);
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
        createNewBlocks();
        checkForKeyPress();
        myPlayfield.update(elapsedTime);
        
        myBlockFrequency += BLOCK_FREQUENCY_INCREASE_RATE;
        myMaxBlockXVelocity += BLOCK_XVELOCITY_INCREASE_RATE;
        blockYVelocity -= BLOCK_YVELOCITY_INCREASE_RATE;
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
           
            int fontWidth = GAME_WIDTH / 3;
            int fontHeight = GAME_HEIGHT / 20;            
            Point middle = new Point(GAME_WIDTH / 2, GAME_HEIGHT / 2);
            
            myFont.drawString(g, "GAME OVER!!!", myFont.CENTER, middle.x - (fontWidth / 2), middle.y - (fontHeight/2),  fontWidth);
            myFont.drawString(g, "FINAL SCORE: " + myScore.getStat(), myFont.CENTER, middle.x - (fontWidth / 2), middle.y + (fontHeight / 2), fontWidth);
    }

    /**
     * Updates score based on time survived
     */
    public void updateScore(){
        myScore.setStat(myClock.getTime());
    }
    
    /**
     * Listen for key presses to update player's location
     */
    public void checkForKeyPress(){
        DoodleSprite player = (DoodleSprite) myPlayers.getActiveSprite();       

        if (keyDown(KeyEvent.VK_RIGHT)){
            player.goRight();
        }
        if (keyDown(KeyEvent.VK_LEFT)){
            player.goLeft();
        }

    }
    /**
     * Render playfield sprites to the screen
     * @param g Graphics2D on which to render images 
     */
    
    public void render(Graphics2D g) {
        myPlayfield.render(g);

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