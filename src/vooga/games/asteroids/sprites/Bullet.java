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
	
	//TODO: LevelParser already constructs this sprite and adds it to a playfield
	//However, all initial values are set to 0 (since the game doesn't start with bullets on screen)
	//Perhaps make set methods for each of these variables as opposed to a constructor.
	public Bullet(double x, double y, double trajectory){
		super(Resources.getImage("greenBulletImage"), x , y);
		setMovement(SPEED, trajectory);
	}
	
	
	
	
}
