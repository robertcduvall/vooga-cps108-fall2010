package vooga.games.jumper;


// JFC
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Dimension;
import java.awt.image.BufferedImage;

//VOOGA
//import vooga.engine.core.Sprite;
import vooga.engine.player.control.PlayerSprite;
import vooga.engine.state.GameState;

// GTGE
import com.golden.gamedev.Game;
import com.golden.gamedev.GameLoader;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;


/**
 * This is an example game we are working on to demonstrate the VOOGA game engine.
 * Skeleton for this class was taken from GTGE Tutorial 5
 * More details to come...
 * @author BrianSimel
 */

public class Jumper extends Game {

	private final static int GAME_WIDTH = 200;
	private final static int GAME_HEIGHT = 480;
	
	
 /****************************************************************************/
 /**************************** GAME SKELETON *********************************/
 /****************************************************************************/

    public void initResources() {
    	GameState playGameState = new PlayGameState();
    	
    	//find a new image
    	BufferedImage image1 = getImage("resources/transBall.gif");    	
    
    	//maybe make this a PlayerGroup instead of a SpriteGroup
    	//also, eventually need to use VOOGA Sprite class
    	Sprite protagonist = new Sprite(image1, 100, 100);

    	SpriteGroup protagonists = new SpriteGroup("good_guys");
    	
    	protagonists.add(protagonist);
    }

    public void update(long elapsedTime) {
    }

    public void render(Graphics2D g) {
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
