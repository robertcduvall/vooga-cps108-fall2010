package vooga.games.towerdefense;

import java.awt.image.BufferedImage;
import java.util.Set;

import com.golden.gamedev.object.CollisionManager;
import com.golden.gamedev.object.PlayField;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;

public class Tower extends Sprite{

	private long shotDelay;
	private long timeSinceShot;
	private boolean armed;
	private Enemy target;
	private double range;
	private SpriteGroup potentialTargets;
	
	public Tower(BufferedImage image, double x, double y, double range){
		this(image, x, y, range, 0);
	}
	
	public Tower(BufferedImage image, double x, double y, double range, long shotDelay){
		super(image, x, y);
		this.shotDelay = shotDelay;
		this.range = range;
		this.armed=true;
	}
	
	public void setTargetGroup(SpriteGroup potentialTargets){
		this.potentialTargets = potentialTargets;
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
		}
		
	}
	
	private boolean isValidTarget(Sprite target){
		return target!=null && target.isActive() && isInRange(target);
	}
	
	private Sprite findTarget(){		
		for(Sprite sprite: potentialTargets.getSprites()){
			if(sprite!=null && isInRange(sprite)){
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

