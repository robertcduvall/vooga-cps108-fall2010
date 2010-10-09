package vooga.games.towerdefense;

import java.awt.image.BufferedImage;
import java.util.Set;

import vooga.engine.resource.Resources;

import com.golden.gamedev.object.CollisionManager;
import com.golden.gamedev.object.PlayField;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;

/**
 * Represents a Tower object placed by the player on the map. Towers 
 * have the capability to have different base statistics such as range 
 * and firing rate and can also be extended to fire bullets differently.
 * @author Derek Zhou, Daniel Koverman, Justin Goldsmith
 *
 */
public abstract class Tower extends Sprite{

	private long shotDelay;
	private long timeSinceShot;
	private boolean armed;
	private Enemy target;
	private double range;
	private SpriteGroup potentialTargets;
	private SpriteGroup shotGroup;
	private double shotSpeed;
	
	
	public Tower(BufferedImage image, double x, double y, double range, long shotDelay, double shotSpeed, TowerDefense game){
		super(image, x, y);
		this.shotDelay = shotDelay;
		this.range = range;
		this.armed=true;
		this.potentialTargets = game.getEnemyGroup();
		this.shotGroup = game.getTowerShotGroup();
		this.shotSpeed = shotSpeed;
	}
	
	public void update(long elapsedTime){
		if(armed){
			timeSinceShot += elapsedTime;
			if(timeSinceShot>shotDelay){
				shoot();
			}
		}
	}
	
	private void shoot(){
		if(!isValidTarget(target)){
			target = (Enemy) findTarget();
		}
		if(target!=null){
			target.gotHit();
			timeSinceShot=0;
			TowerShot shot = new TowerShot(Resources.getImage("towerShot"),getX(), getY(), target.getX(), target.getY(), shotSpeed);
			shotGroup.add(shot);
		}
		
	}
	
	private boolean isValidTarget(Sprite target){
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

}

