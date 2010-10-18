package vooga.games.towerdefense.tower;

import java.awt.image.BufferedImage;

import vooga.engine.resource.Resources;

public class Sniper extends ShootingTower{

	public final static BufferedImage IMAGE = Resources.getImage("sniperTower");
	public final static BufferedImage PREVIEW_IMAGE = Resources.getImage("sniperTowerPreview");
	public final static long SHOT_DELAY = 2000;
	public final static double RANGE = 300;
	public final static int COST = 80;
	public final static double SHOT_SPEED = 2;
	
	public Sniper(double x, double y) {
		super(IMAGE, x, y, PREVIEW_IMAGE, COST, RANGE, SHOT_SPEED, SHOT_DELAY);
	}
}
