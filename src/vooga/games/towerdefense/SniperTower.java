package vooga.games.towerdefense;

import java.awt.image.BufferedImage;

import vooga.engine.resource.Resources;

public class SniperTower extends Tower{
	
	public final static BufferedImage IMAGE = Resources.getImage("sniperTower");
	public final static BufferedImage PREVIEW = Resources.getImage("sniperTower");
	public final static long SHOT_DELAY = 2000;
	public final static double RANGE = 300;
	public final static int COST = 50;
	public final static double SHOT_SPEED = 2;
	
	public SniperTower(double x, double y, TowerDefense game){
		super(IMAGE, x, y, RANGE, SHOT_DELAY, SHOT_SPEED, game);
	}

}
