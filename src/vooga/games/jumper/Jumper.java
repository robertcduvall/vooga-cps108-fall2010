package vooga.games.jumper;


// JFC
import java.awt.Graphics2D;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.io.IOException;

//VOOGA
//import vooga.engine.core.Sprite;
import vooga.engine.player.control.KeyboardControl;
import vooga.engine.player.control.PlayerSprite;
import vooga.engine.resource.ResourceHandler;
import vooga.engine.state.GameState;
import vooga.engine.state.GameStateManager;
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
	private final static int GAME_HEIGHT = 600;
	private String jumperDirectory;
		
	PlayField myPlayfield;
	
	KeyboardControl myKeyControl;
	
 /****************************************************************************/
 /**************************** GAME SKELETON *********************************/
 /****************************************************************************/

    @Override
	public void initResources() {
    	ResourceHandler.setGame(this);
    	try {
			ResourceHandler.loadFile("vooga/games/jumper/resources/resourcelist.txt");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		myPlayfield = new PlayField();
		
		DoodleSprite player1 = new DoodleSprite("player1", "alive", new Sprite(ResourceHandler.getImage("crop"), 400,400));		
    	SpriteGroup protagonists = new SpriteGroup("good_guys");
    	protagonists.add(player1);
    	myPlayfield.addGroup(protagonists);

    	myKeyControl = new KeyboardControl(player1, this);
    	myKeyControl.addInput(KeyEvent.VK_LEFT, "goLeft", player1.getClass().toString() /*"vooga.games.jumper.DoodleSprite"*/);
    	
    	SpriteGroup blocks = new SpriteGroup("blocks");    	
    	myPlayfield.addGroup(blocks);
    	//addDirectory(jumperDirectory);
    	
    	
    }
    
	@Override
	public void update(long elapsedTime) {
		myPlayfield.update(elapsedTime);
    }

    @Override
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
