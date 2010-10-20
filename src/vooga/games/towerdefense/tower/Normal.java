package vooga.games.towerdefense.tower;

import java.awt.image.BufferedImage;

import vooga.engine.event.EventManager;
import vooga.engine.resource.ResourcesBeta;

/**
 * This is a standard tower. All attributes are balanced.
 * 
 * @author Derek Zhou, Daniel Koverman, Justin Goldsmith
 *
 */

public class Normal extends ShootingTower{
	
	public final static BufferedImage IMAGE = ResourcesBeta.getImage("normalTower");
	public final static BufferedImage PREVIEW_IMAGE = ResourcesBeta.getImage("normalTowerPreview");
	public final static int COST = 40;
	public final static long SHOT_DELAY = 1000;
	public final static double RANGE = 150;
	public final static double SHOT_SPEED = 1;
	
	public Normal(double x, double y) {
		super(IMAGE, x, y, PREVIEW_IMAGE, COST, RANGE, SHOT_SPEED, SHOT_DELAY);
	}
	
	public Tower clone(){
		return new Normal(getX(), getY());
	}

}
