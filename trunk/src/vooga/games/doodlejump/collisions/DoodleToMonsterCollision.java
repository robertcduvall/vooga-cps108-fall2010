package vooga.games.doodlejump.collisions;

import java.applet.AudioClip;
import java.net.URL;

import vooga.engine.core.Game;
import vooga.games.doodlejump.*;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.BasicCollisionGroup;

/**
 * The DoodleToMonsterCollision class extends BasicCollisionGroup and defines
 * what happens when Doodle collides with a Monster
 * 
 * @author Adam Cue, Marcus Molchany, Nick Straub
 * 
 */
public class DoodleToMonsterCollision extends BasicCollisionGroup {

	public DoodleToMonsterCollision(Game game) {
		pixelPerfectCollision = true;
	}

	@Override
	public void collided(Sprite doodle, Sprite monster) {
		if(!((DoodleSprite) doodle).getDied()){
			if (doodle.getVerticalSpeed() < 0) {
				((DoodleSprite) doodle).setDied(true);
				((DoodleSprite) doodle).setVerticalSpeed(((DoodleSprite) doodle).getVerticalSpeed() * -1);
				//monster.setActive(false);
				URL spring_url = getClass().getResource("../resources/sounds/buzzer_sound.wav");
				AudioClip clip = java.applet.Applet.newAudioClip(spring_url);
				clip.play();
			} else {
				doodle.setVerticalSpeed(-0.5);
				monster.setActive(false);
			}
		}
	}
}