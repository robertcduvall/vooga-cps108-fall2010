package vooga.examples.player;

import vooga.engine.core.BetterSprite;
import vooga.engine.overlay.Stat;

/**
 * PlayerSpriteExample is an example of how vooga.engine.core.Sprite can be
 * extended to create a more unique sprite for a specific game.
 * 
 * @author Jimmy Mu, Marcus Molchany, Drew Sternesky
 * 
 */
@SuppressWarnings("serial")
public class PlayerExample extends BetterSprite {

	private static final String EXAMPLE_STAT_STRING = "Example";
	private static final int EXAMPLE_STAT_INT = 10;

	public PlayerExample(String label, com.golden.gamedev.object.Sprite sprite) {
		super(label, sprite);
		initializeStats();
	}

	/**
	 * initializeStats initializes all of the stats for the PlayerExample
	 */
	private void initializeStats() {
		Stat<Integer> myExampleStat = new Stat<Integer>(EXAMPLE_STAT_INT);
		setStat(EXAMPLE_STAT_STRING, myExampleStat);
	}

}
