package vooga.games.doodlejump;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.BasicCollisionGroup;

public class BallToMonsterCollision extends BasicCollisionGroup{
	public BallToMonsterCollision(){
		pixelPerfectCollision = true;
	}
	
	public void collided(Sprite ball, Sprite monster) {
		System.out.println("hit");
		ball.setActive(false);
		monster.setActive(false);
	}
}
