package vooga.games.cyberion.sprites.enemyship;

import java.awt.image.BufferedImage;
import java.util.Random;

import vooga.engine.core.BetterSprite;
import vooga.engine.event.EventPool;
import vooga.engine.resource.Resources;

/**
 * General enemy class for various enemy to inherit
 * 
 * @author Harris.He
 * 
 */

public class EnemyShip extends BetterSprite {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int life;
	private int armor;
	private int weaponPower;
	private Random rnd;
	private EventPool eventManager;
	private boolean shooting;

	public EnemyShip() {
		super(Resources.getImage("enemyShip"));
		setLife(1);
	}

	public EnemyShip(BufferedImage image, double x, double y, int life,
			EventPool eventManager) {

		super(image, x, y);
		setLife(life);
		rnd = new Random();
		this.eventManager = eventManager;
		shooting = false;
	}

	private EventPool eventPool = new EventPool();

	public void setEventManager(EventPool em) {
		eventPool = em;
	}

	public void update(long elapsedTime) {
		super.update(elapsedTime);

		if ((life <= 0) || (getY() > Resources.getInt("maxY")))
			this.setActive(false);
		// enemies fire randomly with a chance of 3/1000 for every update call
		// if (this != null && rnd.nextInt(1000) > 997) {
		// shoot();
		// }
	}

	private void shoot() {
		shooting = true;

	}

	public void setLife(int life) {
		this.life = life;
	}

	public int getLife() {
		return life;
	}

	public boolean isShooting() {
		return shooting;
	}

	public void setShooting(boolean b) {
		shooting = b;

	}

}