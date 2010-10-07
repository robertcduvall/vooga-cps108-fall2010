package vooga.games.doodlejump;

import java.applet.AudioClip;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

import com.golden.gamedev.object.*;
import com.golden.gamedev.object.collision.BasicCollisionGroup;

public class DoodleToBrownPlatformCollision extends BasicCollisionGroup {

	public DoodleToBrownPlatformCollision() {
		pixelPerfectCollision = true;
	}

	public void collided(Sprite doodle, Sprite brown_platform) {
		if(doodle.getVerticalSpeed() > 0 && doodle.getY() + doodle.getHeight() < brown_platform.getY() && !((AnimatedSprite) brown_platform).isAnimate()){
			System.out.println("hit");
			((AnimatedSprite) brown_platform).setAnimationFrame(0, 3);
			((AnimatedSprite) brown_platform).setLoopAnim(false);
			((AnimatedSprite) brown_platform).setAnimate(true);
			((AnimatedSprite) brown_platform).setAnimationFrame(3, 3);
			brown_platform.setVerticalSpeed(0.4);
		}
	}
}