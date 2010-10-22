package vooga.games.grandius.enemy.boss;

import java.awt.image.BufferedImage;

import vooga.games.grandius.enemy.common.Enemy;

/**
 * A BossPart is a component of a GradiusBoss that must be destroyed to ultimately
 * kill the boss.
 * @author jtk11
 */
@SuppressWarnings("serial")
public abstract class BossPart extends Enemy {
	protected int health;
	protected int shields;
	protected int[] breakpoints; //0=green done, 1=yellow done
	protected BufferedImage[] images; //0=shielded, 1=green, 2=yellow, 3=red
//	private final int SCORE_VALUE = 200;
//	private final int CASH_VALUE = 10;
	
	public BossPart(BufferedImage[] images, int[] breakpoints, double x, double y, int health, int shields) {
		super(new BufferedImage[]{images[0]}, x, y);
		this.images = images;
		this.breakpoints = breakpoints;
		this.health = health;
		this.shields = shields;
//		myScore = SCORE_VALUE;
//		myCash = CASH_VALUE;
	}

	@Override
	public int getScore() {
		// TODO Auto-generated method stub
		return 250;
	}
	
	public BufferedImage[] getImages() {
		return this.images;
	}
	
}
