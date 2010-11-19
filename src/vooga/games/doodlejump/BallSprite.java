package vooga.games.doodlejump;

import com.golden.gamedev.object.Sprite;

import vooga.engine.core.BetterSprite;;

/**
 * The BallSprite class extends GameEntitySprite and defines how the balls that
 * Doodle shoots should function
 * 
 * @author Adam Cue, Marcus Molchany, Nick Straub
 * 
 */
public class BallSprite extends BetterSprite {

	public BallSprite(String name, String stateName, Sprite s) {
		super(name, s);
	}

	@Override
	public void update(long elapsedTime) {
		super.update(elapsedTime);
		if (getY() < 0)
			setActive(false);
	}
}
