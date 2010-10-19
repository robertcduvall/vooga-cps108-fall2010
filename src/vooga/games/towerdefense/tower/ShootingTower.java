package vooga.games.towerdefense.tower;

import java.awt.image.BufferedImage;

import vooga.engine.event.EventManager;
import vooga.engine.resource.ResourcesBeta;
import vooga.games.towerdefense.Enemy;
import vooga.games.towerdefense.TowerShot;
import vooga.games.towerdefense.events.NeedsTargetsEvent;

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

	public ShootingTower(BufferedImage image, double x, double y, BufferedImage previewImage, int cost, EventManager eventManager, double range,  double shotSpeed, long shotDelay){
		super(image, x, y, previewImage, cost, eventManager);
		this.range = range;
		this.shotSpeed = shotSpeed;
		this.shotDelay = shotDelay;
	}
	
	@Override
	public void update(long elapsedTime) {
			timeSinceShot += elapsedTime;
			if(timeSinceShot>shotDelay){
				shoot();
			}
		
	}
	
	private void shoot(){
		if(!isValidTarget(target)){
			getEventManager().fireEvent("NeedsTargetsEvent", new NeedsTargetsEvent(this, "NeedsTargetsEvent", this));
		}
		if(target!=null){
			target.gotHit();
			timeSinceShot=0;
			TowerShot shot = new TowerShot(ResourcesBeta.getImage("towerShot"),getX(), getY(), target.getX(), target.getY(), shotSpeed);
			//shotGroup.add(shot);
		}
		
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
	
	private boolean isInRange(Sprite other){
		return findDistance(other) <= range;
	}
	
	private double findDistance(Sprite other){
		return Math.sqrt((getX()+getWidth()/2-other.getX())*(getX()+getWidth()/2-other.getX()) + (getY()+getHeight()/2-other.getY())*(this.getY()+getHeight()/2-other.getY()));
	}
	
	public double getRange(){
		return range;
	}
	
	public void setTarget(Enemy newTarget){
		target = newTarget;
	}

}
