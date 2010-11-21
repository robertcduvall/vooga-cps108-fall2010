package vooga.games.grandius.sprites.enemy.boss;

import java.awt.image.BufferedImage;
import java.util.List;


import vooga.engine.core.BetterSprite;
import vooga.engine.overlay.Stat;
import vooga.games.grandius.sprites.enemy.common.Enemy;

/**
 * A GrandiusBoss is the final enemy in a Grandius Level. It is composed of many
 * BossParts, which must be individually destroyed before the boss can be 
 * killed.
 * @author jtk11
 *
 */
@SuppressWarnings("serial")
public abstract class GrandiusBoss extends Enemy {
	
	//protected int health;
	protected int[] breakpoints; //0=green done, 1=yellow done
	protected BufferedImage[] images; //0=shielded_3, 1=shielded_2, 2=shielded_1, 3=green, 4=yellow, 5=red
	private List<BetterSprite> partsList;
	private boolean vulnerable;
	private static final String HEALTH = "Health";
	
	public GrandiusBoss(BufferedImage[] images, int[] breakpoints, double x, double y, int health, 
				       List<BetterSprite> parts) {
		super(images, x, y);
		this.breakpoints = breakpoints;
		this.partsList = parts;
		setHealth(health);
		//this.health = health;
	}
	
	protected void setHealth(int health) {
		Stat<Integer> healthValue = new Stat<Integer>(health);
		setStat(HEALTH, healthValue);
	}

	public int getHealth(){
		return (Integer) this.getStat(HEALTH).getStat();
	}

	public boolean isVulnerable() {
		return this.vulnerable;
	}
	
	public void setVulnerable(boolean vulnerable) {
		this.vulnerable = vulnerable;
	}
	
	public void setParts(List<BetterSprite> parts) {
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
