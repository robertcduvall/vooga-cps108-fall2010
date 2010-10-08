package vooga.games.marioclone;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import vooga.engine.player.control.*;
import vooga.games.doodlejump.BallSprite;

import com.golden.gamedev.object.Sprite;

/*
 * @author Andrew Brown
 */

public class MarioSprite extends PlayerSprite{
	
	double gravity = 0.0001;
	
	public MarioSprite(String name, String stateName, Sprite s){
		super(name,stateName,s);
	}
	
	public void moveRight(){
		if (isOnScreen()) {
			setX(getX() + 5);
		} else {
			setX(0);
		}
	}
	
	public void moveLeft(){
		if (isOnScreen()) {
			setX(getX() - 5);
		} else {
			setX(500);
		}
	}
	
	public void jump(){
		if (isOnScreen()) {
			//setVerticalSpeed(-0.5);
			setY(getY() - 5);
		} else {
			setY(0);
		}
	}
	
	public void crouch(){
		if (isOnScreen()) {
			//setVerticalSpeed(-0.5);
			setY(getY() + 5);
		} else {
			setY(0);
		}
	}
	
	@Override
	public void update(long elapsedTime) {
		super.update(elapsedTime);
		/*
		double yVelocity = getVerticalSpeed();
		double newYVelocity = yVelocity + gravity*elapsedTime;
		setVerticalSpeed(newYVelocity);
		*/
		
	}

}
