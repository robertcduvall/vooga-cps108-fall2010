package vooga.games.zombies.items;

import vooga.games.zombies.Shooter;

import com.golden.gamedev.object.Sprite;
/**
 * Bonus Health Item.
 * @author Jimmy Mu, Aaron Choi, Yang Su
 *
 */
@SuppressWarnings("serial")
public class HealthItem extends Item {
	private int bonusHealth;

	public HealthItem(Shooter shooter, Sprite s, int amount, double x, double y) {
		super(shooter, s, x, y);
		bonusHealth = amount;
	}

	public void act() {
		getPlayer().updateHealth(bonusHealth);
	}

}
