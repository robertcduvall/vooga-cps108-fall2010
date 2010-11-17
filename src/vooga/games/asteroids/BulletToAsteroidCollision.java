package vooga.games.asteroids;


import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.AdvanceCollisionGroup;

public class BulletToAsteroidCollision extends AdvanceCollisionGroup{

	public BulletToAsteroidCollision(){
		pixelPerfectCollision = true;
	}
	
	@Override
	public void collided(Sprite bullet, Sprite asteroid) {
		bullet.setActive(false);
		asteroid.setActive(false);
		//TODO:play sound
		//TODO:add score
	}

}
