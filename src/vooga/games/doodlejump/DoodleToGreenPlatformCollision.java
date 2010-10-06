package vooga.games.doodlejump;

import java.applet.AudioClip;
import java.io.File;
import java.net.URL;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.BasicCollisionGroup;

public class DoodleToGreenPlatformCollision extends BasicCollisionGroup {

	public DoodleToGreenPlatformCollision() {
		pixelPerfectCollision = true;
	}

	public void collided(Sprite doodle, Sprite green_paddle) {
		
		/*
		 * Borrowed Code from http://forums.sun.com/thread.jspa?threadID=5320753
		 */
		if(doodle.getVerticalSpeed() > 0 && doodle.getY() + doodle.getHeight() - 15 < green_paddle.getY()){
			URL jump_url = getClass().getResource("sounds/jump_sound.wav");
			doodle.setVerticalSpeed(-0.5);
	        AudioClip clip = java.applet.Applet.newAudioClip(jump_url);
	        clip.play();
		}
	}
}
