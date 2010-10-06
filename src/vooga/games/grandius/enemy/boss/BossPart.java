package vooga.games.grandius.enemy.boss;

import java.awt.image.BufferedImage;

import com.golden.gamedev.object.AnimatedSprite;

/**
 * A BossPart is a component of a GradiusBoss that must be destroyed to ultimately
 * kill the boss.
 * @author jtk11
 *
 */
@SuppressWarnings("serial")
public class BossPart extends AnimatedSprite {
	private int myHealth;
	private int myShields;
	private BufferedImage[] shieldedImages;
	
	
	public BossPart(BufferedImage[] images, double x, double y, int health,
					BufferedImage[] shieldedimages) {
		super(images, x, y);
		this.myHealth = 100;
		this.myShields = 100;
		this.shieldedImages = shieldedimages;
	}
	
	public void deplete(int depleteAmount) {
		if (myShields <= 0) {
			this.myHealth -= depleteAmount;
			if (myHealth <= 0) 
				this.setActive(false);
		} else {
			this.myShields -= depleteAmount;
		}
	}
	
}
