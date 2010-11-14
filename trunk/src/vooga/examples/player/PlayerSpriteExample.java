package vooga.examples.player;

import vooga.engine.core.Sprite;
import vooga.engine.overlay.Stat;

/**
 * PlayerSpriteExample gives a
 * 
 * @author Jimmy Mu, Marcus Molchany, Drew Sternesky
 *
 */
@SuppressWarnings("serial")
public class PlayerSpriteExample extends Sprite {

	private static final String HEALTH_STRING = "Health";
	private static final String LIVES_STRING = "Lives";
	private static final String EXISTS_STRING = "Exists";
	private static final String SCORE_STRING = "Score";

	private static final int INITIAL_HEALTH = 100;
	private static final int INITIAL_LIVES = 5;
	private static final boolean INITIAL_EXISTS = true;
	private static final int INITIAL_SCORE = 0;

	private Stat<Integer> myHealthStat;
	private Stat<Integer> myLivesStat;
	private Stat<Boolean> myExistsStat;
	private Stat<Integer> myScoreStat;

	public PlayerSpriteExample(String name, Sprite s) {
		super(name, s);
		initializeStats();
	}

	private void initializeStats() {
		initializeHealthStat();
		initializeLivesStat();
		initializeExistsStat();
		initializeScoreStat();
	}

	private void initializeHealthStat() {
		myHealthStat = new Stat<Integer>(INITIAL_HEALTH);
		setStat(EXISTS_STRING, myExistsStat);
	}
	
	private void initializeLivesStat() {
		myLivesStat = new Stat<Integer>(INITIAL_LIVES);
		setStat(LIVES_STRING, myLivesStat);
	}
	
	private void initializeExistsStat() {
		myExistsStat = new Stat<Boolean>(INITIAL_EXISTS);
		setStat(HEALTH_STRING, myHealthStat);
	}
	
	private void initializeScoreStat() {
		myScoreStat = new Stat<Integer>(INITIAL_SCORE);
		setStat(SCORE_STRING, myScoreStat);
	}
}
