package vooga.games.marioclone;

import java.awt.image.BufferedImage;

import com.golden.gamedev.object.Sprite;

import vooga.engine.player.control.PlayerSprite;

@SuppressWarnings("serial")
public class Enemy extends CharacterSprite {

	double speed = .25;
	
	public Enemy(String name, String stateName, BufferedImage left,
			BufferedImage right) {
		super(name, stateName, left, right);
		setHorizontalSpeed(speed*Math.signum(Math.random()-.5));
		setHealth(100);
	}
	
	public void bounce() {
		setHorizontalSpeed(-getHorizontalSpeed());
	}
	
	@Override
	public void update(long elapsedTime) {
		if(getHealth() <= 0)
			setActive(false);
		
		super.update(elapsedTime);
		
		if(!isOnScreen()) bounce();
		if(getHorizontalSpeed()<0) setNewImage(leftImage);
		else if(getHorizontalSpeed()>0) setNewImage(rightImage);
		
	}


}
