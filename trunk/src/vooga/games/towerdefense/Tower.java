package vooga.games.towerdefense;

import java.awt.image.BufferedImage;
import java.util.Set;

import vooga.engine.resource.Resources;

import com.golden.gamedev.object.CollisionManager;
import com.golden.gamedev.object.PlayField;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;

public abstract class Tower extends Sprite{

	private long shotDelay;
	private long timeSinceShot;
	private boolean armed;
	private Enemy target;
	private double range;
	private SpriteGroup potentialTargets;
	private SpriteGroup shotGroup;
	
	
	public Tower(BufferedImage image, double x, double y, double range, long shotDelay, TowerDefense game){
		super(image, x, y);
		this.shotDelay = shotDelay;
		this.range = range;
		this.armed=true;
		this.potentialTargets = game.getEnemyGroup();
		this.shotGroup = game.getTowerShotGroup();
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
			TowerShot shot = new TowerShot(Resources.getImage("towerShot"),getX(), getY(), target.getX(), target.getY(), 1);
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
		return Math.sqrt((this.getX()-other.getX())*(this.getX()-other.getX()) + (this.getY()-other.getY())*(this.getY()-other.getY()));
	}

}

