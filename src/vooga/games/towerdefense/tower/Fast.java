package vooga.games.towerdefense.tower;

import java.awt.image.BufferedImage;

import vooga.engine.event.EventManager;
import vooga.engine.resource.ResourcesBeta;

/**
 * This tower shoots faster, costs more, and has a smaller range.
 * 
 * @author Derek Zhou, Daniel Koverman, Justin Goldsmith
 *
 */

public class Fast extends ShootingTower{

	private static final long serialVersionUID = -3736751382675453712L;
	public final static BufferedImage IMAGE = ResourcesBeta.getImage("fastTower");
	public final static BufferedImage PREVIEW_IMAGE = ResourcesBeta.getImage("fastTowerPreview");
	public final static long SHOT_DELAY = 150;
	public final static double RANGE = 50;
	public final static int COST = 250;
	public final static double SHOT_SPEED = .6;
	
	public Fast(double x, double y, EventManager eventManager) {
		super(IMAGE, x, y, PREVIEW_IMAGE, COST, eventManager, RANGE, SHOT_SPEED, SHOT_DELAY);
	}
	
	public Tower clone(){
		return new Fast(getX(), getY(), getEventManager());
	}
}
