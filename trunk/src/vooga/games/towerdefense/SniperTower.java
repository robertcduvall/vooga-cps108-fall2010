package vooga.games.towerdefense;

import java.awt.image.BufferedImage;

import vooga.engine.resource.Resources;

public class SniperTower extends Tower{
	
	public final static BufferedImage IMAGE = Resources.getImage("sniperTower");
	public final static BufferedImage PREVIEW = Resources.getImage("sniperTower");
	public final static long SHOT_DELAY = 2000;
	public final static double RANGE = 300;
	public final static double COST = 15000;
	
	public SniperTower(double x, double y, TowerDefense game){
		super(IMAGE, x, y, RANGE, SHOT_DELAY, game);
	}

}
