package vooga.games.grandius.enemy.boss;

import java.awt.image.BufferedImage;
import java.util.List;

import com.golden.gamedev.object.Sprite;

import vooga.games.grandius.enemy.common.Enemy;

/**
 * A GrandiusBoss is the final enemy in a Grandius Level. It is composed of many
 * BossParts, which must be individually destroyed before the boss can be 
 * killed.
 * @author jtk11
 *
 */
@SuppressWarnings("serial")
public abstract class GrandiusBoss extends Enemy {
	
	protected int health;
	protected int[] breakpoints; //0=green done, 1=yellow done
	protected BufferedImage[] images; //0=shielded_3, 1=shielded_2, 2=shielded_1, 3=green, 4=yellow, 5=red
	private List<Sprite> partsList;
	private boolean vulnerable;
	
	public GrandiusBoss(BufferedImage[] images, int[] breakpoints, double x, double y, int health, 
				       List<Sprite> parts) {
		super(images, x, y);
		this.breakpoints = breakpoints;
		this.health = health;
		this.partsList = parts;
	}
	
	public boolean isVulnerable() {
		return this.vulnerable;
	}
	
	public void setVulnerable(boolean vulnerable) {
		this.vulnerable = vulnerable;
	}
	
	public void setParts(List<Sprite> parts) {
		this.partsList = parts;
	}
	
	public void addPart(BossPart part) {
		this.partsList.add(part);
	}
	
	public void removePart(BossPart part) {
		this.partsList.remove(part);
	}
	
	@Override
	public void update(long elapsedTime) {

		this.updateMovement(elapsedTime);
	}
	
	
}
