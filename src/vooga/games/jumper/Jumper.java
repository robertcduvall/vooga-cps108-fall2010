

package vooga.games.jumper;


// JFC
import java.awt.Graphics2D;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Random;

//VOOGA
import vooga.engine.resource.ResourceHandler;

// GTGE
import com.golden.gamedev.GameLoader;
import com.golden.gamedev.object.Background;
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

        private static final int MAX_BLOCK_Y_VELOCITY = 2;
		private static final int MAX_BLOCK_X_VELOCITY = 6;
		private final static int GAME_WIDTH = 600;
        private final static int GAME_HEIGHT = 800;
        private final double BLOCK_FREQUENCY = 0.1;
        private Point DOODLE_START = new Point (GAME_WIDTH / 2, 100);
        
        private PlayField myPlayfield;
    
        private SpriteGroup myBlocks = new SpriteGroup("blocks");
        
        private DoodleSprite myPlayer;
        
        private DoodleToBlockCollision myCollision;
        
       /****************************************************************************/
 /**************************** GAME SKELETON *********************************/
 /****************************************************************************/

    public void initResources() {
    	
    	//setting up resource handler
    	ResourceHandler.setGame(this);
    	try{
    		ResourceHandler.loadFile("vooga/games/jumper/resources/resourcelist.txt");
    	} catch (IOException e){
    		e.printStackTrace();
    	}
    	
    	
    	myPlayer = new DoodleSprite(ResourceHandler.getImage("crop"), DOODLE_START);

    	myPlayer.setSpeed(0, 0);
    	
    	myPlayfield = new PlayField();
    	
    	myPlayfield.add(myPlayer);
    	myPlayfield.addGroup(myBlocks);
    	
    	//myCollision = new DoodleToBlockCollision(myPlayer, myBlocks);
    }

    public static int getGameWidth() {
        return GAME_WIDTH;
    }

    public static int getGameHeight() {
        return GAME_HEIGHT;
    }

	//TODO:refactor the hell out of this method
    public void createNewBlocks(){
    	Random myRandom = new Random();
    	double randomBlockOccurance = myRandom.nextDouble();
    	int randomXLocation = myRandom.nextInt(GAME_WIDTH);
    	int randomXVelocity = myRandom.nextInt(MAX_BLOCK_X_VELOCITY) - (MAX_BLOCK_X_VELOCITY / 2);
    	int randomYVelocity = myRandom.nextInt(MAX_BLOCK_Y_VELOCITY) - MAX_BLOCK_Y_VELOCITY;
    	
    	if (randomBlockOccurance < BLOCK_FREQUENCY){
        	Sprite block = new BlockSprite(ResourceHandler.getImage("platformGreen"), new Point(randomXLocation, 800));
    		block.setSpeed(randomXVelocity, randomYVelocity);
        	myBlocks.add(block);
    	}
    }

    public void update(long elapsedTime) {
        createNewBlocks();
        checkForKeyPress();
    	myPlayfield.update(elapsedTime);
    }
    
    public void checkForKeyPress(){
    	if (keyDown(KeyEvent.VK_RIGHT)){
    		myPlayer.goRight();
    	}
    	if (keyDown(KeyEvent.VK_LEFT)){
    		myPlayer.goLeft();
    	}
    }
    

    public void render(Graphics2D g) {
        myPlayfield.render(g);
    }


 /****************************************************************************/
 /***************************** START-POINT **********************************/
 /****************************************************************************/

    public static void main(String[] args) {
        GameLoader game = new GameLoader();
        game.setup(new Jumper(), new Dimension(GAME_WIDTH,GAME_HEIGHT), false);
        game.start();
    }
   
}
