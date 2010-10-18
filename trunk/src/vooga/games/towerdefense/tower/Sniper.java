package vooga.games.towerdefense.tower;

import java.awt.image.BufferedImage;

import vooga.engine.event.EventManager;
import vooga.engine.resource.Resources;
import vooga.engine.resource.ResourcesBeta;

public class Sniper extends ShootingTower{

	public final static BufferedImage IMAGE = ResourcesBeta.getImage("sniperTower");
	public final static BufferedImage PREVIEW_IMAGE = ResourcesBeta.getImage("sniperTowerPreview");
	public final static long SHOT_DELAY = 2000;
	public final static double RANGE = 300;
	public final static int COST = 80;
	public final static double SHOT_SPEED = 2;
	
	public Sniper(double x, double y, EventManager eventManager) {
		super(IMAGE, x, y, PREVIEW_IMAGE, COST, eventManager, RANGE, SHOT_SPEED, SHOT_DELAY);
	}
}
