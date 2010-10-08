package vooga.games.grandius.enemy.boss;

import java.awt.image.BufferedImage;

import com.golden.gamedev.object.AnimatedSprite;
import com.golden.gamedev.object.Sprite;

/**
 * A BossPart is a component of a GradiusBoss that must be destroyed to ultimately
 * kill the boss.
 * @author jtk11
 *
 */
@SuppressWarnings("serial")
public class BossPart extends Sprite {
	private int myHealth;
	private int myShields;
	private int[] myBreakpoints; //0=green done, 1=yellow done
	private BufferedImage[] myImages; //0=shielded, 1=green, 2=yellow, 3=red
	
	
	public BossPart(BufferedImage[] images, int[] breakpoints, double x, double y, int health, int shields) {
		super(images[0], x, y);
		this.myImages = images;
		this.myBreakpoints = breakpoints;
		this.myHealth = health;
		this.myShields = shields;
	}
	
	public boolean deplete(int depleteAmount) {
		if (myShields <= 0) {
			this.myHealth -= depleteAmount;
			if (myHealth <= 0) {
				return true;
				
			} else if (myHealth >= myBreakpoints[0]) {
				this.setImage(myImages[1]);
			} else if (myHealth >= myBreakpoints[1]) {
				this.setImage(myImages[2]);
			} else {
				this.setImage(myImages[3]);
			} 
		} else {
			this.myShields -= depleteAmount;
		}
		return false;
		
	}
	
}
