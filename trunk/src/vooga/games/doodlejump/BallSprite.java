package vooga.games.doodlejump;

import com.golden.gamedev.object.Sprite;

import vooga.engine.core.BetterSprite;
import vooga.engine.resource.Resources;

/**
 * The BallSprite class extends GameEntitySprite and defines how the balls that
 * Doodle shoots should function
 * 
 * @author Adam Cue, Marcus Molchany, Nick Straub
 * 
 */
public class BallSprite extends BetterSprite {
	public BallSprite(){
	}

	public BallSprite(String name, Sprite s) {
		super(name, s);
	}

	@Override
	public void update(long elapsedTime) {
		super.update(elapsedTime);
		if (getY() < Resources.getDouble("minScreenY"))
			setActive(false);
	}
}
