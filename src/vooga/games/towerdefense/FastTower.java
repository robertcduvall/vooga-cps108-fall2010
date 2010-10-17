package vooga.games.towerdefense;

import java.awt.image.BufferedImage;

import vooga.engine.resource.Resources;
import vooga.engine.resource.ResourcesBeta;

public class FastTower extends Tower{
	
	/**
	 * An expensive tower with a very short range and an
	 * extremely high rate of fire.
	 * 
	  * @author Derek Zhou, Daniel Koverman, Justin Goldsmith
	 */
	private static final long serialVersionUID = -3736751382675453712L;
	public final static BufferedImage IMAGE = ResourcesBeta.getImage("fastTower");
	public final static BufferedImage PREVIEW = ResourcesBeta.getImage("fastTowerPreview");
	public final static long SHOT_DELAY = 150;
	public final static double RANGE = 50;
	public final static int COST = 250;
	public final static double SHOT_SPEED = .6;
	
	public FastTower(double x, double y, TowerDefense game){
		super(IMAGE, x, y, RANGE, SHOT_DELAY, SHOT_SPEED, game);
	}

}