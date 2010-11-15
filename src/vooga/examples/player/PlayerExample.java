package vooga.examples.player;

import vooga.engine.core.Sprite;
import vooga.engine.overlay.Stat;

/**
 * PlayerSpriteExample is an example of how vooga.engine.core.Sprite can be
 * extended to create a more unique sprite for a specific game.
 * 
 * @author Jimmy Mu, Marcus Molchany, Drew Sternesky
 * 
 */
@SuppressWarnings("serial")
public class PlayerExample extends Sprite {

	private static final String EXAMPLE_STAT_STRING = "Example";

	private static final int EXAMPLE_STAT_INT = 10;

	private Stat<Integer> myExampleStat;

	public PlayerExample(String label, Sprite s) {
		super(label, s);
		initializeStats();
	}

	/**
	 * initializeStats initializes all of the stats for the PlayerExample
	 */
	private void initializeStats() {
		initializeExampleStat();
	}

	private void initializeExampleStat() {
		myExampleStat = new Stat<Integer>(EXAMPLE_STAT_INT);
		setStat(EXAMPLE_STAT_STRING, myExampleStat);
	}
}
