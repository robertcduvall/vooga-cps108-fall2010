package vooga.games.doodlejump;

import java.awt.image.BufferedImage;

import com.golden.gamedev.object.Sprite;

public class JumpingMonster extends Sprite{
	private int jumpTimer;
	
	public JumpingMonster(BufferedImage image, double x, double y){
		super(image, x, y);
		jumpTimer = 20;
		setVerticalSpeed(-0.2);
	}
	
	@Override
	public void update(long elapsedTime){
		super.update(elapsedTime);
		jumpTimer--;
		if(jumpTimer < 0){
			setVerticalSpeed(getVerticalSpeed() * -1);
			jumpTimer = 20;
		}
	}
}
