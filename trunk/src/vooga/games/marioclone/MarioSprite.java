package vooga.games.marioclone;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import vooga.engine.player.control.*;
import com.golden.gamedev.object.Sprite;


public class MarioSprite extends PlayerSprite{
	
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

}
