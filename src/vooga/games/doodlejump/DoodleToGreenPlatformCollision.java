package vooga.games.doodlejump;

import java.applet.AudioClip;
import java.net.URL;

import javax.media.j3d.Sound;

import com.golden.gamedev.engine.audio.WaveRenderer;
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
		
		URL jump_url = getClass().getResource("jump_sound.wav");
		doodle.setVerticalSpeed(-0.5);
		
        AudioClip clip = java.applet.Applet.newAudioClip(jump_url);
        clip.play();
	}
}
