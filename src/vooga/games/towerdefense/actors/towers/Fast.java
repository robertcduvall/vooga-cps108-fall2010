package vooga.games.towerdefense.actors.towers;

import java.awt.image.BufferedImage;

import vooga.engine.resource.Resources;
import vooga.games.towerdefense.events.ShootEvent;

/**
 * This tower shoots faster, costs more, and has a smaller range.
 * 
 * @author Derek Zhou, Daniel Koverman, Justin Goldsmith
 *
 */

public class Fast extends ShootingTower{

	private static final long serialVersionUID = -3736751382675453712L;
	public final static BufferedImage IMAGE = Resources.getImage("fastTower");
	public final static BufferedImage PREVIEW_IMAGE = Resources.getImage("fastTowerPreview");
	public final static long SHOT_DELAY = 150;
	public final static double RANGE = 50;
	public final static int COST = 250;
	public final static double SHOT_SPEED = .6;
	
	
	public Fast(double x, double y, ShootEvent shootEvent) {
		super(IMAGE, x, y, PREVIEW_IMAGE, COST, RANGE, SHOT_SPEED, SHOT_DELAY, shootEvent);
	}
	
	public Tower clone(){
		return new Fast(getX(), getY(), getShootEvent());
	}
}
