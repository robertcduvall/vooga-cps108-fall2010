package vooga.games.jumper;

import java.awt.image.BufferedImage;

import com.golden.gamedev.object.PlayField;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;

import vooga.engine.player.control.PlayerSprite;
import vooga.engine.resource.Resources;
import vooga.engine.state.GameState;

/**
 * Main Gameplay Game State
 * More details to come...
 * @author BrianSimel
 */


public class PlayGameState extends GameState{

	
	public PlayGameState(){
		super();
	}
	
	@Override
	public void initialize() {
//		myPlayfield = new PlayField();
    	//TODO: add background
    	
    	//find a new image
		//Resources.loadImage("ball", "resources/transBall.gif");
    	//BufferedImage image1 = getImage("resources/transBall.gif");    	
    
		//TODO: assign image and starting location for playersprite
    	PlayerSprite player1 = new PlayerSprite("player1", "alive", new Sprite());
    	
    	SpriteGroup protagonists = new SpriteGroup("good_guys");
    	
    	protagonists.add(player1);
    	
    	SpriteGroup blocks = new SpriteGroup("blocks");
    	
	}

}
