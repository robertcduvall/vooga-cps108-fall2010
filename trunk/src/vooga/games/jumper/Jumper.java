

package vooga.games.jumper;


// JFC
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

//VOOGA
//import vooga.engine.core.Sprite;
import vooga.engine.player.control.PlayerSprite;
import vooga.engine.resource.ResourceHandler;
import vooga.engine.state.GameState;
import vooga.engine.state.GameStateManager;
import vooga.engine.core.*;
import vooga.engine.*;

// GTGE
import com.golden.gamedev.Game;
import com.golden.gamedev.GameLoader;
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

        private final static int GAME_WIDTH = 600;
        private final static int GAME_HEIGHT = 480;
        private final double BLOCK_FREQUENCY = 0.01;
        
        private String jumperDirectory;
               
        private PlayField myPlayfield;
        
        private SpriteGroup myBlocks = new SpriteGroup("blocks");
        
       /****************************************************************************/
 /**************************** GAME SKELETON *********************************/
 /****************************************************************************/

    public void initResources() {
    	
    	ResourceHandler.setGame(this);
    	try{
    		ResourceHandler.loadFile("vooga/games/jumper/resources/resourcelist.txt");
    	} catch (IOException e){
    		e.printStackTrace();
    	}
    	
    	myPlayfield = new PlayField();
    	
    	Sprite player1 = new MovingSprite(ResourceHandler.getImage("crop"), new Point(getWidth() / 2, 100), new Point(0, 4));
    	
    	SpriteGroup myPlayers = new SpriteGroup("good guys");
    	
    	myPlayers.add(player1);
    	
    	myPlayfield.addGroup(myPlayers);
    	myPlayfield.addGroup(myBlocks);
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
    	double randomXLocation = myRandom.nextDouble();
    	double randomXVelocity = myRandom.nextDouble();
    	double randomYVelocity = myRandom.nextDouble();
    	
    	if (randomBlockOccurance < BLOCK_FREQUENCY){
        	myBlocks.add(new MovingSprite(ResourceHandler.getImage("crop"), new Point ((int) ( getWidth() * randomXLocation), GAME_HEIGHT - 100), new Point((int) ((randomXVelocity * 5) - 5), (int)(randomYVelocity - 0.5)*5)));
    	}
    }

    public void update(long elapsedTime) {
        myPlayfield.update(elapsedTime);
        createNewBlocks();
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
