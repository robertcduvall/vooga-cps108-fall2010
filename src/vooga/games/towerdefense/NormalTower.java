package vooga.games.towerdefense;

import java.awt.image.BufferedImage;

import vooga.engine.resource.Resources;

public class NormalTower extends Tower{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3736751382675453712L;
	public final static BufferedImage IMAGE = Resources.getImage("normalTower");
	public final static BufferedImage PREVIEW = Resources.getImage("normalTowerPreview");
	public final static long SHOT_DELAY = 1000;
	public final static double RANGE = 150;
	public final static int COST = 40;
	public final static double SHOT_SPEED = 1;
	
	public NormalTower(double x, double y, TowerDefense game){
		super(IMAGE, x, y, RANGE, SHOT_DELAY, SHOT_SPEED, game);
	}

}