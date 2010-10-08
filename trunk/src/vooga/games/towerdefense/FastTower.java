package vooga.games.towerdefense;

import java.awt.image.BufferedImage;

import vooga.engine.resource.Resources;

public class FastTower extends Tower{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3736751382675453712L;
	public final static BufferedImage IMAGE = Resources.getImage("fastTower");
	public final static BufferedImage PREVIEW = Resources.getImage("fastTower");
	public final static long SHOT_DELAY = 100;
	public final static double RANGE = 50;
	public final static double COST = 10000;
	public final static double SHOT_SPEED = .6;
	
	public FastTower(double x, double y, TowerDefense game){
		super(IMAGE, x, y, RANGE, SHOT_DELAY, SHOT_SPEED, game);
	}

}