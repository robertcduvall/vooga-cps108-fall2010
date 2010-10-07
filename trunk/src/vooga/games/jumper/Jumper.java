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
import vooga.engine.state.GameStateManager;
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

public class Jumper extends Game {

	private final static int GAME_WIDTH = 600;
	private final static int GAME_HEIGHT = 480;
	private String jumperDirectory;
		
	private GameStateManager myManager;
 /****************************************************************************/
 /**************************** GAME SKELETON *********************************/
 /****************************************************************************/

    public void initResources() {
    	myManager = new GameStateManager();
    	addDirectory(jumperDirectory);
    	
    	GameState playGameState = new PlayGameState();
    	
    	myManager.addGameState(playGameState);
    	
    }

    public static int getGameWidth() {
    	return GAME_WIDTH;
    }

    public static int getGameHeight() {
    	return GAME_HEIGHT;
    }

	public void update(long elapsedTime) {
    	myManager.update(elapsedTime);
    }

    public void render(Graphics2D g) {
    	myManager.render(g);
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
