package vooga.games.doodlejump.monsters;

import java.awt.image.BufferedImage;

import vooga.engine.core.BetterSprite;
import vooga.engine.resource.Resources;

import com.golden.gamedev.object.Sprite;

public class JumpingMonster extends BetterSprite {
	private int jumpTimer;
	
	public JumpingMonster(){
		this(Resources.getImage("greenJumpingMonster"));
	}

	public JumpingMonster(BufferedImage image) {
		super(image);
		jumpTimer = 20;
		setVerticalSpeed(-0.2);
	}

	@Override
	public void update(long elapsedTime) {
		super.update(elapsedTime);
		jumpTimer--;
		if (jumpTimer < 0) {
			setVerticalSpeed(getVerticalSpeed() * -1);
			jumpTimer = 20;
		}
	}
}
