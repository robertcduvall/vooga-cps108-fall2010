package vooga.games.grandius;

import vooga.engine.core.BetterSprite;
import vooga.engine.event.IEventHandler;
import vooga.engine.overlay.Stat;
import vooga.engine.resource.Resources;

@SuppressWarnings("serial")
public class Player extends BetterSprite implements IEventHandler {
	
	// TODO: Good practice here? Use Missile/BlackHole classes?
	private boolean missileActive;
	private boolean blackHoleActive;
	private boolean skipLevel; //TODO should this go in this class?
	private boolean isInvincible;
	
	public Player() {
		this("PlayerName");
	}
	
	public Player(String label){
		super(label, new BetterSprite(
				Resources.getImage("playerImage"),
				Resources.getInt("playerInitialX"),
				Resources.getInt("playerInitialY")));
		missileActive = false;
		blackHoleActive = false;
		skipLevel = false;
		isInvincible = false;
	}
	
	public void moveLeft() {
		moveX(-Resources.getDouble("playerSpeed"));
	}
	
	public void moveRight() {
		moveX(Resources.getDouble("playerSpeed"));
	}
	
	public void moveUp() {
		moveY(-Resources.getDouble("playerSpeed"));
	}
	
	public void moveDown() {
		moveY(Resources.getDouble("playerSpeed"));
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
		int playerLives = ((Integer)getStat("livesStat").getStat());
		if (playerLives > 0 && !isInvincible) {
			updateStat(((Stat<Integer>)getStat("livesStat")), (-1));
			this.setLocation(Resources.getInt("playerInitialX"), Resources.getInt("playerInitialY"));
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
		updateStat(((Stat<Integer>)getStat("scoreStat")), points);
	}

	/**
	 * Adds the given cash amounts to the Stat cash.
	 */
	public void updateCash(int cash) {
		updateStat(((Stat<Integer>)getStat("cashStat")), cash);
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
