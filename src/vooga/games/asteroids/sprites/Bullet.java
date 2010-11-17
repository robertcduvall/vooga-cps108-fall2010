package vooga.games.asteroids.sprites;

import vooga.engine.core.Sprite;
import vooga.engine.overlay.Stat;
import vooga.engine.resource.Resources;

public class Bullet extends Sprite {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1370680108514026753L;
	public final static double SPEED = .1;	
	
	public Bullet(double x, double y, double trajectory){
		super(Resources.getImage("greenBulletImage"), x , y);
		setMovement(SPEED, trajectory);
	}
	
	
	
	
}
