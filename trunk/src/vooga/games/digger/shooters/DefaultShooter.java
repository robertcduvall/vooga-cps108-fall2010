package vooga.games.digger.shooters;

import vooga.engine.core.BetterSprite;

public class DefaultShooter implements Shooter {
	
	private final long DELAY = 500;
	
	private long timeSinceLastShot;
	private BetterSprite sprite;

	@Override
	public void setSprite(BetterSprite sprite) {
		this.sprite=sprite;
		
	}

	@Override
	public void shoot(long elapsedTime) {
		timeSinceLastShot += elapsedTime;
		if(timeSinceLastShot>DELAY){
			sprite.setActive(false);
			timeSinceLastShot=0;
		} 
			
	}
	
	

}
