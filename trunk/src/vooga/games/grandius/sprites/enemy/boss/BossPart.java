package vooga.games.grandius.sprites.enemy.boss;

import java.awt.image.BufferedImage;

import vooga.engine.overlay.Stat;
import vooga.games.grandius.sprites.enemy.common.Enemy;

/**
 * A BossPart is a component of a GradiusBoss that must be destroyed to ultimately
 * kill the boss.
 * @author jtk11
 */
@SuppressWarnings("serial")
public abstract class BossPart extends Enemy {
	//protected int health;
	//protected int shields;
	protected int[] breakpoints; //0=green done, 1=yellow done
	protected BufferedImage[] images; //0=shielded, 1=green, 2=yellow, 3=red
//	private final int SCORE_VALUE = 200;
//	private final int CASH_VALUE = 10;
	private static final String HEALTH = "Health";
	private static final String SHIELDS = "Shields";
	
	public BossPart(BufferedImage[] images, int[] breakpoints, double x, double y, int health, int shields) {
		super(new BufferedImage[]{images[0]}, x, y);
		this.breakpoints = breakpoints;
		this.images = images;
		setHealth(health);
		setShields(shields);
		//this.health = health;
		//this.shields = shields;
//		myScore = SCORE_VALUE;
//		myCash = CASH_VALUE;
	}

	protected void setHealth(int health) {
		Stat<Integer> healthValue = new Stat<Integer>(health);
		setStat(HEALTH, healthValue);
	}

	protected void setShields(int shields) {
		Stat<Integer> shieldValue = new Stat<Integer>(shields);
		setStat(SHIELDS, shieldValue);
	}



//	@Override
//	public int getScore() {
//		// TODO Auto-generated method stub
//		return 250;
//	}
	
	public int getHealth(){
		return (Integer) this.getStat(HEALTH).getStat();
	}
	
	public int getShields(){
		return (Integer) this.getStat(SHIELDS).getStat();
	}
	
	public BufferedImage[] getImages() {
		return this.images;
	}
	
}
