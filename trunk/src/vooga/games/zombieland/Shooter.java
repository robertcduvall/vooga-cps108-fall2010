package vooga.games.zombieland;

import com.golden.gamedev.object.*;
import com.golden.gamedev.Game.*;

import vooga.engine.player.control.KeyboardControl;
import vooga.engine.player.control.PlayerSprite;

public class Shooter extends PlayerSprite{
	
	private AnimatedSprite currentSprite;
	private int speed;
	
	public Shooter(String name, String stateName, AnimatedSprite s,int playerHealth, int playerRank) {
		super(name, stateName, s, playerHealth, playerRank);
		currentSprite = s;
		speed = -1;
	}
	public void goLeft()
	{
		setToCurrentSprite("Left");
		moveX(speed);
		animateSprite();
	}
	
	public void goRight()
	{
		setToCurrentSprite("Right");
		moveX(Math.abs(speed));
		animateSprite();
	}
	
	public void goUp()
	{
		setToCurrentSprite("Up");
		moveY(speed);
		animateSprite();
	}
	
	public void goDown()
	{
		setToCurrentSprite("Down");
		moveY(Math.abs(speed));
		animateSprite();
	}



	private void animateSprite() {
		
		
	    // set animation speed 100 milliseconds for each frame
		currentSprite.getAnimationTimer().setDelay(100);

	    // set animation frame starting from the first image to the third image
		currentSprite.setAnimationFrame(0, 5);
		currentSprite.setAnimate(true);
		currentSprite.setLoopAnim(true);
//	    // animate the sprite, and perform continous animation
//	    sprite.setAnimate(true);
//	    sprite.setLoopAnim(true);
	}
	
}
