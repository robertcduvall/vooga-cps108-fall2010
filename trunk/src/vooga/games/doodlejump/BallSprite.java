package vooga.games.doodlejump;

import com.golden.gamedev.object.Sprite;

import vooga.engine.player.control.GameEntitySprite;

/**
 * The BallSprite class extends GameEntitySprite and defines how the balls that
 * Doodle shoots should function
 * 
 * @author Adam Cue, Marcus Molchany, Nick Straub
 * 
 */
public class BallSprite extends GameEntitySprite {

	public BallSprite(String name, String stateName, Sprite s) {
		super(name, stateName, s);
	}

	@Override
	public void update(long elapsedTime) {
		super.update(elapsedTime);
	}
}
