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
import com.golden.gamedev.object.PlayField;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;


/**
 * This is an example game we are working on to demonstrate the VOOGA game engine.
 * Skeleton for this class was taken from GTGE Tutorial 5
 * More details to come...
 * @author BrianSimel
 */

public class Jumper extends Game {

	private final static int GAME_WIDTH = 600;
	private final static int GAME_HEIGHT = 480;
	
	private PlayField myPlayfield;
	
 /****************************************************************************/
 /**************************** GAME SKELETON *********************************/
 /****************************************************************************/

    public void initResources() {
    	
    	myPlayfield = new PlayField();
    	//TODO: add background
    	
    	//find a new image
    	BufferedImage image1 = getImage("resources/transBall.gif");    	
    
    	PlayerSprite player1 = new PlayerSprite("player1", "alive", new Sprite(image1, GAME_WIDTH / 2, GAME_HEIGHT / 2));
    	
    	SpriteGroup protagonists = new SpriteGroup("good_guys");
    	myPlayfield.addGroup(protagonists);
    	
    	protagonists.add(player1);
    	
    	SpriteGroup blocks = new SpriteGroup("blocks");
    	myPlayfield.addGroup(blocks);
    }

    public void update(long elapsedTime) {
    	myPlayfield.update(elapsedTime);
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
