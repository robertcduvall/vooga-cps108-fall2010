package vooga.games.doodlejump.collisions;

import java.applet.AudioClip;
import java.net.URL;

import vooga.engine.core.Game;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.BasicCollisionGroup;
import vooga.games.doodlejump.DoodleSprite;

/**
 * The DoodleToFinishLineCollision class extends BasicCollisionGroup and
 * defines what happens when Doodle collides with the Finish Line
 * 
 * @author Adam Cue, Marcus Molchany, Nick Straub
 * 
 */
public class DoodleToFinishLineCollision extends BasicCollisionGroup {

	public DoodleToFinishLineCollision(Game game) {
		pixelPerfectCollision = true;
	}

	@Override
	public void collided(Sprite doodle, Sprite finishLine) {
		((DoodleSprite) doodle).setLevelComplete();
	}
}