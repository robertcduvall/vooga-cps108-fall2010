package vooga.games.marioclone;

import java.awt.image.BufferedImage;

import com.golden.gamedev.object.Sprite;

import vooga.engine.player.control.PlayerSprite;


/**
 * @author David Herzka, Cameron McCallie, Andrew Brown
 * 
 * This class represents the main enemy 'mario' sprite.
 * 
 */

public class Enemy extends CharacterSprite {

	private static final double mySpeed = .25;
	private static final Integer myMaxHealth = 100;
	
	/**
	 * Constructs an enemy sprite out of the parameters listed below:
	 * 
	 * @param name
	 * @param stateName - ex. alive, dead, etc...
	 * @param left - represents the image used when the sprite turns/moves left
	 * @param right - represents the image used when the sprite turns/moves right
	 */
	
	public Enemy(String name, String stateName, BufferedImage left,
			BufferedImage right) {
		super(name, stateName, left, right);
		setHorizontalSpeed(mySpeed*Math.signum(Math.random()-.5));
	}
	
	/**
	 * Sends the sprite in the opposite direction with opposite velocity.
	 * The method is named 'bounce' because it occurs after a collision with the main sprite or wall.
	 * 
	 */
	
	public void bounce() {
		setHorizontalSpeed(-getHorizontalSpeed());
	}
	
	/**
	 * This method will remove the said enemy if its health reaches 0,
	 * as well as cause the enemies to bounce off the walls so they don't run off the screen.
	 * 
	 */
	public void update(long elapsedTime) {
		if((Integer) (getStat("Health").getStat()) <= 0)
			setActive(false);
		
		super.update(elapsedTime);
		
		if(!isOnScreen()) bounce();
		
		if(getHorizontalSpeed()<0) setNewImage(leftImage);
		else if(getHorizontalSpeed()>0) setNewImage(rightImage);
		
	}

	@Override
	public Integer getMaxHealth() {
		return myMaxHealth;
	}


}
