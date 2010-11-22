package vooga.games.towerdefense.actors.towers;

import java.awt.image.BufferedImage;

import vooga.games.towerdefense.actors.enemies.Enemy;
import vooga.games.towerdefense.events.ShootEvent;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;


/**
 * This is the main class of the game.  It creates the different states of the games, and loads all the sprites used in the game.
 * 
 * 
 * @author Derek Zhou, Daniel Koverman, Justin Goldsmith
 *
 */
public abstract class ShootingTower extends Tower{
	

	private Enemy target;
	private SpriteGroup potentialTargets;
	private long timeSinceShot;
	private SpriteGroup shotGroup;
	private long shotDelay;
	private double range;
	private double shotSpeed;
	private ShootEvent shootEvent;

	public ShootingTower(BufferedImage image, double x, double y, BufferedImage previewImage, int cost, double range,  double shotSpeed, long shotDelay, ShootEvent shootEvent){
		super(image, x, y, previewImage, cost);
		this.range = range;
		this.shotSpeed = shotSpeed;
		this.shotDelay = shotDelay;
		this.shootEvent = shootEvent;
	}
	
	@Override
	public void update(long elapsedTime) {
			timeSinceShot += elapsedTime;
			if(canShoot()){
				shootEvent.addTower(this);
			}
	}
	
	public boolean canShoot()
	{
		return timeSinceShot>shotDelay;
	}
	
	public ShootEvent getShootEvent()
	{
		return shootEvent;
	}
	
	
	public boolean isValidTarget(Sprite target){
		return target!=null && target.isActive() && isInRange(target);
	}
	
	private Sprite findTarget(){
		for(Sprite sprite: potentialTargets.getSprites()){
			if(isValidTarget(sprite)){
				return sprite;
			}
		}
		return null;		
	}
	
	public boolean checkTargetValid(Sprite spriteToCheck)
	{
		return spriteToCheck!=null && isInRange(spriteToCheck);
	}
	
	private boolean isInRange(Sprite other){
		return findDistance(other) <= range;
	}
	
	private double findDistance(Sprite other){
		return Math.sqrt((getX()+getWidth()/2-other.getX())*(getX()+getWidth()/2-other.getX()) + (getY()+getHeight()/2-other.getY())*(this.getY()+getHeight()/2-other.getY()));
	}
	
	public double getRange(){
		return range;
	}
	
	public Enemy getTarget(){
		return target;
	}
	
	public void resetShot(){
		timeSinceShot = 0;
	}
	
	public void setTarget(Enemy newTarget){
		target = newTarget;
	}
}
