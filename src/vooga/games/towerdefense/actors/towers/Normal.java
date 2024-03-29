package vooga.games.towerdefense.actors.towers;

import java.awt.image.BufferedImage;

import vooga.engine.resource.Resources;
import vooga.games.towerdefense.events.ShootEvent;

/**
 * This is a standard tower. All attributes are balanced.
 * 
 * @author Derek Zhou, Daniel Koverman, Justin Goldsmith
 *
 */

public class Normal extends ShootingTower{
	
	public final static BufferedImage IMAGE = Resources.getImage("normalTower");
	public final static BufferedImage PREVIEW_IMAGE = Resources.getImage("normalTowerPreview");
	public final static int COST = 40;
	public final static long SHOT_DELAY = 1000;
	public final static double RANGE = 150;
	public final static double SHOT_SPEED = 1;
	
	public Normal(double x, double y, ShootEvent shootEvent) {
		super(IMAGE, x, y, PREVIEW_IMAGE, COST, RANGE, SHOT_SPEED, SHOT_DELAY, shootEvent);
	}
	
	public Tower clone(){
		return new Normal(getX(), getY(), getShootEvent());
	}

}
