package vooga.games.grandius;

import vooga.engine.core.BetterSprite;
import vooga.engine.event.IEventHandler;
import vooga.engine.overlay.Stat;
import vooga.engine.resource.Resources;

public class Player extends BetterSprite implements IEventHandler {

	private static final double PLAYER_INITIAL_X = Resources.getInt("PlayerInitialX");
	private static final double PLAYER_INITIAL_Y = Resources.getInt("PlayerInitialY");
	private Stat<Integer> statLives;
	private Stat<Integer> statScore;
	private Stat<Integer> statCash;
	
	// TODO: Good practice here? Use Missile/BlackHole classes?
	private boolean missileActive;
	private boolean blackHoleActive;
	
	private boolean skipLevel; //TODO should this go in this class?
	private boolean isInvincible;
	
	public Player(String label, BetterSprite sprite){
		super(label,sprite);
		statLives = new Stat<Integer>( Resources.getInt("InitialPlayerLives"));
		statScore = new Stat<Integer>(Resources.getInt("InitialZero"));//TODO get rid of this "InitialZero" variable? 
		statCash = new Stat<Integer>(Resources.getInt("InitialZero"));
		missileActive = false;
		blackHoleActive = false;
		skipLevel = false;
		isInvincible = false;
	}
	
	public Stat<Integer> getLives() {
		return this.statLives;
	}
	
	public Stat<Integer> getScore() {
		return this.statScore;
	}
	
	public Stat<Integer> getCash() {
		return this.statCash;
	}
	
	@Override
	public boolean isTriggered() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void actionPerformed() {
		// TODO Auto-generated method stub

	}
	
	/**
	 * Decrements the number of lives the player has. Called when Player
	 * collides with enemy or an enemy projectile.
	 */
	public void updatePlayerLives() {
		int playerLives = (statLives.getStat()).intValue();
		if (playerLives > 0 && !isInvincible) {
			updateStat(statLives, (-1));
			this.setLocation(PLAYER_INITIAL_X, PLAYER_INITIAL_Y);
		}
	}
	
	/**
	 * Method for adding a value (including negative ones) to any Stat<Integer>,
	 * primarily for the lives, cash, and score counters.
	 */
	public void updateStat(Stat<Integer> stat, int addedValue) {
		int myCurrentStat = stat.getStat();
		// Can't go below 0
		if (myCurrentStat + addedValue > 0)
			stat.setStat(new Integer(stat.getStat().intValue() + addedValue));
		else
			stat.setStat(new Integer(0));
	}
	
	/**
	 * Adds the given points to the Stat score.
	 */
	public void updateScore(int points) {
		updateStat(statScore, points);
	}

	/**
	 * Adds the given cash amounts to the Stat cash.
	 */
	public void updateCash(int cash) {
		updateStat(statCash, cash);
	}

	public void setInvincible() {
		this.isInvincible = true;
	}
	
	public void skipLevel() {
		this.skipLevel = true;
	}
	
	public void setMissileActive() {
		this.missileActive = true;
	}
	
	public void setBlackHoleActive() {
		this.blackHoleActive = true;
	}
}
