package engine.player.control;

import java.awt.event.KeyEvent;
import engine.core.Game;
import engine.player.control.IPlayerController;

/**
 * This class provides mappings to the UP, LEFT, RIGHT, and DOWN keys 
 * for moving a player in the BreakerPong game. This controller is used for 
 * one of the players. 
 * 
 * @author Drew Sternesky, Marcus Molchany, Jimmy Mu
 *
 */

public class KeyboardController implements IPlayerController {

	//used to access the KeyDown methods that Golden-T has built-in
	private Game myGame;
	
	public KeyboardController(Game game)
	{
		myGame = game;
	}
	
	//change X velocity
	public double deltaVelocityX() 
	{
		return 0;
	}

	//change Y velocity
	public double deltaVelocityY() 
	{
		return 0;
	}

	//returns amount to change the X position by.
	public double deltaX() 
	{
//		if(leftPressActivated())
//			return -5;
//		if(rightPressActivated())
//			return 5;

		return 0;
	}

	//returns amount to change the Y position by.
	public double deltaY() 
	{
		if(upPressActivated())
			return -5;
		if(downPressActivated())
			return 5;
		return 0;
	}
	
	//uses myGame instance variable to check if certain keys are pressed
	private boolean downPressActivated() {
		return myGame.keyDown(KeyEvent.VK_DOWN);
	}
	private boolean upPressActivated() {
		return myGame.keyDown(KeyEvent.VK_UP);
	}
	private boolean rightPressActivated() {
		return myGame.keyDown(KeyEvent.VK_RIGHT);
	}
	private boolean leftPressActivated() {
		return myGame.keyDown(KeyEvent.VK_LEFT);
	}
	
}
