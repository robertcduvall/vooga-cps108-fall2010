package vooga.games.marioclone;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.AdvanceCollisionGroup;
import com.golden.gamedev.object.collision.CollisionShape;

public abstract class BetterCollisionGroup extends AdvanceCollisionGroup {
	
	public BetterCollisionGroup() {
		pixelPerfectCollision = true;
	}

	@Override
	public boolean isCollide(Sprite s1, Sprite s2, CollisionShape shape1, CollisionShape shape2) {
		boolean collided = super.isCollide(s1,s2,shape1,shape2);
		if(s1.getHorizontalSpeed() < 0  && s1.getX() >= s2.getX())
			collisionSide = LEFT_RIGHT_COLLISION;
		else if(s1.getVerticalSpeed() < 0  && s1.getY() >= s2.getY())
			collisionSide = TOP_BOTTOM_COLLISION;
		return collided;
	}
	
	public int getCollisionSide(Sprite s1, Sprite s2) {
		int side = super.getCollisionSide();
		if(s1.getHorizontalSpeed() < 0  && s1.getX() >= s2.getX())
			side = LEFT_RIGHT_COLLISION;
		else if(s1.getVerticalSpeed() < 0  && s1.getY() >= s2.getY())
			side = TOP_BOTTOM_COLLISION;
		return side;
	}
	
	public void revertPosition1(Sprite s1, Sprite s2) {
		switch(getCollisionSide()) {
		case(LEFT_RIGHT_COLLISION):
			s1.setX(s2.getX()+s2.getWidth());
			break;
		case(RIGHT_LEFT_COLLISION):
			s1.setX(s2.getX()-s1.getWidth()+1);
			break;
		case(TOP_BOTTOM_COLLISION):
			s1.setY(s2.getY()+s2.getHeight());
			break;
		case(BOTTOM_TOP_COLLISION):
			s1.setY(s2.getY()-s1.getHeight()+1);
			break;
		}	
	}

}
