package vooga.games.grandius.weapons;

import vooga.engine.core.BetterSprite;
import vooga.engine.resource.Resources;
import vooga.games.grandius.enemy.common.Zipster;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;
import com.golden.gamedev.object.Timer;

@SuppressWarnings("serial")
public class BlackHole extends BetterSprite {
	
	private Timer speedTimer;
	private double firingVelocity;
	private double playerCompensationSpeed;
	private static final int MAX_SWALLOWED_ENEMIES = 5;
	private static final int BLACK_HOLE_SPEED_REDUCTION_TIME = 1;
	private static final double BLACK_HOLE_INITIAL_SPEED = 0.2;
	private int swallowedEnemies;
	
	public BlackHole(double x, double y) {
		super(Resources.getImage("blackHoleImage"),x,y);
		this.setHorizontalSpeed(Resources.getDouble("projectileSpeed"));
		//this.setHorizontalSpeed(BLACK_HOLE_INITIAL_SPEED);
		speedTimer = new Timer(BLACK_HOLE_SPEED_REDUCTION_TIME);
		swallowedEnemies = 0;
		firingVelocity = BLACK_HOLE_INITIAL_SPEED;
		playerCompensationSpeed = 0;
	}
	
	@Override
	public void update(long elapsedTime) {
		if (speedTimer.action(elapsedTime)) {
			if (firingVelocity >= 0)
				firingVelocity -= firingVelocity/50;
			else 
				firingVelocity = 0;
		}
		//TODO: probably not good practice with playerCompensationSpeed here
		this.setHorizontalSpeed(playerCompensationSpeed + firingVelocity);
		this.updateMovement(elapsedTime);
	}
	
	public void swallowEnemy() {
		swallowedEnemies++;
	}
	
	public int getSwallowedEnemies() {
		return swallowedEnemies;
	}
	
	public int hitsRemaining(){
		return (MAX_SWALLOWED_ENEMIES - swallowedEnemies);
	}
	
	public void suckEnemies(SpriteGroup possibleEnemies) {
		//TODO: Other possible enemies?
		for (Sprite s: possibleEnemies.getSprites()) {
			if (s == null)
				continue;
			double dX = this.getX() - s.getX();
			double dY = this.getY() - s.getY();
			((Zipster)s).setProximateToBlackHole(false);
			if (Math.abs(dX) < 200 && Math.abs(dY) < 200) {
				//if (this.isActive()) {
				((Zipster)(s)).spinZipster();
				s.setHorizontalSpeed(0.0005*dX);
				s.setVerticalSpeed(0.0005*dY);
				//}
			}
		}
	}
	
	public void setPlayerCompensationSpeed(double speed) {
		playerCompensationSpeed = speed;
	}
}
