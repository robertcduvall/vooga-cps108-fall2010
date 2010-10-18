package vooga.games.towerdefense.tower;

import java.awt.image.BufferedImage;

import vooga.engine.resource.ResourcesBeta;
import vooga.games.towerdefense.Enemy;
import vooga.games.towerdefense.TowerShot;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;

public abstract class ShootingTower extends Tower{
	

	private Enemy target;
	private SpriteGroup potentialTargets;
	private long timeSinceShot;
	private SpriteGroup shotGroup;
	private long shotDelay;
	private double range;
	private double shotSpeed;

	public ShootingTower(BufferedImage image, double x, double y, BufferedImage previewImage, int cost, double range,  double shotSpeed, long shotDelay){
		super(image, x, y, previewImage, cost);
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
			target = (Enemy) findTarget();
		}
		if(target!=null){
			target.gotHit();
			timeSinceShot=0;
			TowerShot shot = new TowerShot(ResourcesBeta.getImage("towerShot"),getX(), getY(), target.getX(), target.getY(), shotSpeed);
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
